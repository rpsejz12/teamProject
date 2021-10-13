package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.page.PageDAO;
import common.page.PageVO;
import model.movie.MovieDAO;
import model.movie.MovieVO;

public class AdminlistAction implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException{
      ActionForward forward = new ActionForward();
      
      MovieDAO mDAO = new MovieDAO();
      PageDAO pDAO = new PageDAO();
      PageVO pVO = new PageVO();
      
      int page = 1;
      String ppage = request.getParameter("page");
      if(ppage != null) {
         page = Integer.parseInt(ppage);
      } // 저희가 했던 페이징 처리 mcnt랑 똑같은 원리 
      
      ArrayList<MovieVO> datas = new ArrayList<MovieVO>(); // m_selectDB_all을 담을 어레이리스트
      
      String search = request.getParameter("search"); // SelectAll과 동시 검색을 하기위해 search와 type을 받아옴
      String mtype = request.getParameter("mtype");       
      
      pVO.setCurPage(page);   //   현재 페이지   
      pVO.setPerPage(12);      //   페이지 게시물 수
      pVO.setPerPageSet(5);   //   페이지 수
      
  	  pVO = pDAO.paging(pVO,null,mtype,search,"movie");
      
      datas = mDAO.m_selectDB_all(mtype, search, pVO ); 
      request.setAttribute("datas", datas);  // 페이징 처리를 하기 위한 값 3가지
      request.setAttribute("paging", pVO);
      request.setAttribute("page", page);
      request.setAttribute("search", search);
      
      
      forward.setRedirect(false);
      forward.setPath("adminlist.jsp");
      return forward;
   }

}