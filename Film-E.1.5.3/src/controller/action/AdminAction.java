package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.movie.MovieDAO;
import model.movie.MovieVO;

public class AdminAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ActionForward forward = new ActionForward();
		
		MovieDAO mDAO = new MovieDAO();
		MovieVO mVO = new MovieVO();
		
		mVO.setMpk(request.getParameter("mpk")); // m_selectDB_one을 하기위해서 받아옴
		System.out.println(mVO+"mVO확인");
		
		
		request.setAttribute("datas", mDAO.m_selectDB_one(mVO)); // 어드민 리스트 페이지에서 게시물을 누르면 그 게시물db를 datas로 담아 v로 보내줌
		
		forward.setRedirect(false);
		forward.setPath("admin.jsp");
		
		return forward;
		// 게시물 관리 페이지로 이동
	}

}
