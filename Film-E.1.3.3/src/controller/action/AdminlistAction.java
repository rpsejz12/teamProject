package controller.action;

import java.io.IOException;
import java.sql.SQLException;
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
      MovieVO mVO = new MovieVO();
      
      PageDAO pDAO = new PageDAO();
      PageVO pVO = new PageVO();
      
      int page = 1;
      String ppage = request.getParameter("page");
      if(ppage != null) {
         page = Integer.parseInt(ppage);
      } // 저희가 했던 mcnt랑 똑같은 원리 
      
      ArrayList<MovieVO> datas = new ArrayList<MovieVO>(); // selectAll되는 arraylist
      
      String search = request.getParameter("search"); // SelectAll과 동시 검색을 하기위해 search와 type을 받아옴
      String mtype = request.getParameter("mtype");      
      
      pVO.setCurPage(page);   //   현재 페이지   
      pVO.setPerPage(8);      //   페이지 게시물 수
      pVO.setPerPageSet(3);   //   페이지 수
      
  	  pVO = pDAO.paging(pVO,mtype,search,"movie");
      
      datas = mDAO.m_selectDB_all(mtype, search, pVO ); 
      request.setAttribute("datas", datas);  // v에서 사용할 값
      request.setAttribute("paging", pVO);
      request.setAttribute("page", page);
      
      
      forward.setRedirect(false);
      forward.setPath("adminlist.jsp");
      return forward;
   }

}