package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.review.ReviewDAO;
import model.review.ReviewVO;

public class R_deleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ActionForward forward = null; // output
		
		ReviewDAO rDAO = new ReviewDAO();
		ReviewVO rVO = new ReviewVO();
		rVO.setRpk(Integer.parseInt(request.getParameter("rpk")));
		if (rDAO.r_deleteDB(rVO)) {
			forward = new ActionForward();
			forward.setPath("r_seletAll.do");
			forward.setRedirect(false);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('리뷰 삭제 실패!');history.go(-1)</script>");
		}
		
		return forward;
	}
}
