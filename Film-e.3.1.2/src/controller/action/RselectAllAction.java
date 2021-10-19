package controller.action;

import java.io.IOException;
import java.util.ArrayList;

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

public class RselectAllAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		
		ReviewDAO rDAO = new ReviewDAO();
		MovieVO mVO = new MovieVO();
		MovieVO rmVO = new MovieVO();
		MovieDAO mDAO = new MovieDAO();
		ReviewVO rVO = new ReviewVO();
		
		PageDAO pDAO = new PageDAO(); 
		PageVO pVO = new PageVO();
		
		HttpSession session = request.getSession();
		
		int page = 1; 
		String ppage = request.getParameter("page");
		if(ppage != null) {
			page = Integer.parseInt(ppage);
		} 
		
		mVO.setMpk(request.getParameter("mpk"));
		rVO.setId((String)session.getAttribute("sessionID"));
		rVO.setMpk(request.getParameter("mpk"));
		
		pVO.setCurPage(page);	//	현재 페이지	
		pVO.setPerPage(8);		//	페이지 게시물 수
		pVO.setPerPageSet(3);	//	페이지 수
		
		
		String mtype = request.getParameter("mtype");
		String search = request.getParameter("search");
		
		mVO = mDAO.m_selectDB_one(mVO);
		rmVO = mDAO.m_selectDB_rand();
		rVO = rDAO.r_selectDB_one(rVO);
		
		while(true) {
			rmVO = mDAO.m_selectDB_rand();
			if(!rmVO.getMpk().equals(mVO.getMpk())) {
				break;
			}
		}
		
		
		pVO = pDAO.paging(pVO,mVO ,mtype, search,"review");
		
		ArrayList<ReviewVO> datas = rDAO.r_selectDB_all(mVO, pVO);
		System.out.println(mDAO.m_selectDB_one(mVO));
		
		request.setAttribute("mdata", mVO);
		request.setAttribute("mrand", rmVO);
		request.setAttribute("paging", pVO); 
		request.setAttribute("page", page); 
		request.setAttribute("datas", datas);
		request.setAttribute("data", rVO);
		
		forward.setRedirect(false);
		forward.setPath("review.jsp");
		
		return forward;
	}
}
