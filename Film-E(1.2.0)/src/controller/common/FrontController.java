package controller.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.ActionForward;
import controller.action.AdminAction;
import controller.action.AdminlistAction;
import controller.action.C_deleteAction;
import controller.action.C_insertAction;
import controller.action.C_seletOneAciton;
import controller.action.C_updateAction;
import controller.action.CategoryAction;
import controller.action.CheckIDAction;
import controller.action.LoginAction;
import controller.action.LogoutAction;
import controller.action.M_deleteAction;
import controller.action.M_insertAction;
import controller.action.M_updateAction;
import controller.action.MainAction;
import controller.action.R_deleteAction;
import controller.action.R_insertAction;
import controller.action.R_selectAllAction;

//"모든" 클라이언트의 요청이 현재 서블릿으로 들어오게 되고, 이곳에서 요청에 따라 control.jsp로 이동하도록 함

/*@WebServlet("*.do")*/ // 간단하게 이 방식도 가능
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	private void doAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		/* 1. 클라이언트의 "요청"을 분석하는 함수 */
		String uri = request.getRequestURI(); // uri : /FrontController/main.do
		String cp = request.getContextPath(); // cp :/FrontController
		String action = uri.substring(cp.length()); // action : /main/do - 실제 액션값만 추출 -> 유지 보수성 증가
		
		/* 2. control을 매핑하는 역할 */
		ActionForward forward = null;
		
		if (action.equals("/main.do")) {
			forward = new MainAction().execute(request, response);
		} else if (action.equals("/login.do")) { // 로그인
			forward = new LoginAction().execute(request, response); // 메인페이지로 이동
		} else if (action.equals("/logout.do")) { // 로그아웃
			forward = new LogoutAction().execute(request, response); //  메인페이지로 이동
		} else if (action.equals("/c_insert.do")) { // 회원 가입 
			forward = new C_insertAction().execute(request, response); // 로그인 페이지로 이동
		} else if (action.equals("/checkID.do")) { // 중복 아이디 체크
			forward = new CheckIDAction().execute(request, response);
		} else if (action.equals("/mypage.do")) { // 회원 정보 조회
			forward = new C_seletOneAciton().execute(request, response); // 마이페이지로 이동
		} else if (action.equals("/c_delete.do")) { // 회원 탈퇴
			forward = new C_deleteAction().execute(request, response);
		} else if (action.equals("/c_update.do")) { // 회원 정보 변경
			forward = new C_updateAction().execute(request, response); // 마이페이지로 이동
		} else if (action.equals("/review.do")) { // 모든 리뷰 조회
			forward = new R_selectAllAction().execute(request, response); // 리뷰페이지로 이동
		} else if (action.equals("/r_insert.do")) { // 리뷰 등록
			forward = new R_insertAction().execute(request, response); // 리뷰페이지로 이동
		} else if (action.equals("/r_delete.do")) { // 리뷰 삭제
			forward = new R_deleteAction().execute(request, response); // 리뷰페이지로 이동
		} else if (action.equals("/admin.do")) {	// 페이지 이동
			forward = new AdminAction().execute(request, response); // 게시물 관리 페이지 (어드민) 이동
		} else if (action.equals("/adminlist.do")) { // 검색 및 selectAll 기능
			forward = new AdminlistAction().execute(request, response); // 어드민 페이지 이동
		} else if (action.equals("/categories.do")) { // 검색 및 SelectAll 기능 
			forward = new CategoryAction().execute(request, response);	// 카테고리 페이지 이동
		} else if (action.equals("/m_delete.do")) {	 // 게시물 삭제
			forward = new M_deleteAction().execute(request, response); // 성공: 어드민 페이지 ,실패: 게시물 관리 페이지(어드민) 이동 
		} else if (action.equals("/m_insert.do")) {  // 게시물 등록 
			forward = new M_insertAction().execute(request, response); // 성공: 어드민 페이지 ,실패: 게시물 관리 페이지(어드민) 이동
		} else if (action.equals("/m_update.do")) {  // 게시물 수정
			forward = new M_updateAction().execute(request, response); // 성공: 어드민 페이지 ,실패: 게시물 관리 페이지(어드민) 이동
		}
		else { // 만약 잘못된 action값인 경우 즉, null인 경우 에러 페이지로 연결
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/error/errer404.jsp");
		}
		
		/* 3. 클라이언트에게 결과 화면 출력 */
		if (forward != null) { // 만약 forward == null이면 위의 액션처리가 제대로 되지 않은 것!
			if (forward.isRedirect()) { // redirect 방식
				response.sendRedirect(forward.getPath());
			} else { // forward 방식 - 요청 헤더를 유지하여 페이지 이동
				System.out.println("!!!!");
				// Dispatcher
				// : 클라이언트로부터 최초에 들어온 요청을 JSP/Servlet 내에서 원하는 자원으로 요청을 넘기는 역할을 수행하거나,
				//   특정 자원에 처리를 요청하고 처리 결과를 얻어오는 기능을 수행하는 클래스
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath()); // 최종 경로
				dispatcher.forward(request, response);
			}
		}
	}
}
