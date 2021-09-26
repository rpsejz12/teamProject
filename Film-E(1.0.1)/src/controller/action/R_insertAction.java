package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.review.ReviewDAO;
import model.review.ReviewVO;
import oracle.net.aso.r;

public class R_insertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ActionForward forward = null; // output
		
		ReviewDAO rDAO = new ReviewDAO();
		ReviewVO rVO = new ReviewVO();
		rVO.setComment(request.getParameter("comment"));
		rVO.setId(request.getParameter("id"));
		rVO.setMpk(request.getParameter("mpk"));
		
		if (rDAO.r_insertDB(rVO)) {
			forward = new ActionForward();
			forward.setPath("r_seletAll.do");
			forward.setRedirect(false);
		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('리뷰 등록 실패!');history.go(-1)</script>");
		}
		
		return forward;
	}
}
