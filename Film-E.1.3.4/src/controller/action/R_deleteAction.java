package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.page.PageDAO;
import common.page.PageVO;
import model.movie.MovieDAO;
import model.movie.MovieVO;
import model.review.ReviewDAO;
import model.review.ReviewVO;

public class R_deleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ActionForward forward = null; // output
		
		ReviewDAO rDAO = new ReviewDAO();
		ReviewVO rVO = new ReviewVO();
		
		MovieVO mVO = new MovieVO();
		MovieDAO mDAO = new MovieDAO();
		
		PageDAO pDAO = new PageDAO(); //
		PageVO pVO = new PageVO(); //
		
		int page = 1; //
		String ppage = request.getParameter("page");
		if(ppage != null) {
			page = Integer.parseInt(ppage);
		} //
		
		rVO.setRpk(Integer.parseInt(request.getParameter("rpk")));
		
		pVO.setCurPage(page);	//	현재 페이지	
		pVO.setPerPage(8);		//	페이지 게시물 수
		pVO.setPerPageSet(3);	//	페이지 수
		
		String mtype = request.getParameter("mtype");
		String search = request.getParameter("search");
		
		mVO = mDAO.m_selectDB_one(mVO);
		
		pVO = pDAO.paging(pVO,mVO, search, mtype, "review");
		
		/*request.setAttribute("paging", pVO); 
		request.setAttribute("page", page); 
		*/
		
		if (rDAO.r_deleteDB(rVO)) {
			forward = new ActionForward();
			forward.setPath("r_selectAll.do"); 
			forward.setRedirect(false);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('리뷰 삭제 실패!');history.go(-1)</script>");
		}
		
		return forward;
	}
}
