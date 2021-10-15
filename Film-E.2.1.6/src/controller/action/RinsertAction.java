package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.review.ReviewDAO;
import model.review.ReviewVO;

public class RinsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ActionForward forward = null; // output
		HttpSession session = request.getSession();

		ReviewDAO rDAO = new ReviewDAO();
		ReviewVO rVO = new ReviewVO();

		rVO.setCmt(request.getParameter("cmt"));
		rVO.setId((String)session.getAttribute("sessionID"));
		rVO.setMpk(request.getParameter("mpk"));

		if(request.getParameter("rating") != null) {
			
			rVO.setRating(Double.parseDouble(request.getParameter("rating")));
		}
		else {
			rVO.setRating(3.0);
		}

		try {
			if (rDAO.r_insertDB(rVO)) {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("RselectAll.do?mpk=" + request.getParameter("mpk"));
			} else {
				PrintWriter out = response.getWriter();
				response.setContentType("text/html; charset=UTF-8");
				out.println("<script>alert('리뷰 등록 실패!');history.go(-1)</script>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return forward;
	}
}
