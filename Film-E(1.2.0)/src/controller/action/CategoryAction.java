package controller.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.movie.MovieDAO;

public class CategoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ActionForward forward = new ActionForward();
		
		
		MovieDAO mDAO = new MovieDAO();
		
		String type = request.getParameter("mtype");
		String search = request.getParameter("search");
		
		request.setAttribute("datas", mDAO.m_selectDB_all(type, search));
		
		
		forward.setRedirect(false);
		forward.setPath("categories.jsp");
		
		return forward;
	}

}
