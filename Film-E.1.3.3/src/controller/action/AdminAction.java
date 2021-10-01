package controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
		
		mVO.setMpk(request.getParameter("mpk"));
		
		
		
		request.setAttribute("datas", mDAO.m_selectDB_one(mVO));
		
		forward.setRedirect(false);
		forward.setPath("admin.jsp");
		
		return forward;
		// 게시물 관리 페이지로 이동
	}

}
