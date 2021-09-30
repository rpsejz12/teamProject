package controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.movie.MovieDAO;
import model.movie.MovieVO;

public class AdminlistAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ActionForward forward = new ActionForward();
		
		MovieDAO mDAO = new MovieDAO();
		MovieVO mVO = new MovieVO();
		
		ArrayList<MovieVO> datas = new ArrayList<MovieVO>(); // selectAll되는 arraylist
		
		String search = request.getParameter("search"); // SelectAll과 동시 검색을 하기위해 search와 type을 받아옴
		String type = request.getParameter("type");		//
		
		datas = mDAO.m_selectDB_all(type, search); 
		request.setAttribute("datas", datas);  // v에서 사용할 값
		
		forward.setRedirect(false);
		forward.setPath("adminList.jsp");
		return forward;
	}

}
