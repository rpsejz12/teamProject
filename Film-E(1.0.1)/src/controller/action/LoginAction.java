package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.client.ClientDAO;
import model.client.ClientVO;

public class LoginAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = null;
				
		ClientDAO cDAO = new ClientDAO();
		ClientVO cVO = new ClientVO();
		cVO.setId(request.getParameter("id")); // 로그인 id
		cVO.setPw(request.getParameter("pw")); // 로그인 pw
		
		if (cDAO.login(cVO) != null) { // 로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("sessionID", cVO.getId());
			
			forward = new ActionForward();
			forward.setPath("main.do");
			forward.setRedirect(false);
		} else { // 로그인 실패
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인에 실패하였습니다. 아이디 혹은 비밀번호 확인 후 다시 입력하세요.');history.go(-1)</script>");
		}
		
		return forward;
	}
}
