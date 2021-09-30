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
		
		ArrayList<MovieVO> datas = new ArrayList<MovieVO>(); // selectAll�Ǵ� arraylist
		
		String search = request.getParameter("search"); // SelectAll�� ���� �˻��� �ϱ����� search�� type�� �޾ƿ�
		String type = request.getParameter("type");		//
		
		datas = mDAO.m_selectDB_all(type, search); 
		request.setAttribute("datas", datas);  // v���� ����� ��
		
		forward.setRedirect(false);
		forward.setPath("adminList.jsp");
		return forward;
	}

}
