package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.review.ReviewDAO;
import model.review.ReviewVO;

public class R_selectAllAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		
		ReviewDAO rDAO = new ReviewDAO();
		
		ArrayList<ReviewVO> datas = rDAO.r_selectDB_all();
		
		request.setAttribute("datas", datas);
		
		forward.setPath("review.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
