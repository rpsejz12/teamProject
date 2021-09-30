package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.movie.MovieDAO;

public class MainAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ActionForward forward = new ActionForward(); // output
		
		MovieDAO mDAO = new MovieDAO();
		
		String type = request.getParameter("type");
		String search = request.getParameter("search");
		
		request.setAttribute("datas", mDAO.m_selectDB_all(type, search));
		forward.setRedirect(false);
		forward.setPath("main.jsp");
		
		
		return forward;
		// 메인페이지, SelectAll과 검색을 동시에 함
	}
}