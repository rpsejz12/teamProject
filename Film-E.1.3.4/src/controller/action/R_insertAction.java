package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.page.PageDAO;
import common.page.PageVO;
import model.movie.MovieDAO;
import model.movie.MovieVO;
import model.review.ReviewDAO;
import model.review.ReviewVO;

public class R_insertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ActionForward forward = null; // output
		HttpSession session = request.getSession();
		
		ReviewDAO rDAO = new ReviewDAO();
		ReviewVO rVO = new ReviewVO();
		
		MovieVO mVO = new MovieVO();
		MovieDAO mDAO = new MovieDAO();
		
		PageDAO pDAO = new PageDAO(); //
		PageVO pVO = new PageVO(); //
		
	/*	int page = 1; //
		String ppage = request.getParameter("page");
		if(ppage != null) {
			page = Integer.parseInt(ppage);
		} //
		*/
		
		rVO.setCmt(request.getParameter("cmt"));
		rVO.setId((String)session.getAttribute("sessionID"));
		rVO.setMpk(request.getParameter("mpk"));
		
/*		System.out.println("rVO :"+ rVO);

		pVO.setCurPage(page);	//	���� ������	
		pVO.setPerPage(8);		//	������ �Խù� ��
		pVO.setPerPageSet(3);	//	������ ��
		
		String mtype = request.getParameter("mtype");
		String search = request.getParameter("search");
		
		pVO = pDAO.paging(pVO,mVO ,mtype, search,"review");
		
		request.setAttribute("paging", pVO); //
		request.setAttribute("page", page); //
		*/
		if (rDAO.r_insertDB(rVO)) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("r_selectAll.do");
		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('���� ��� ����!');history.go(-1)</script>");
		}
		
		return forward;
	}
}
