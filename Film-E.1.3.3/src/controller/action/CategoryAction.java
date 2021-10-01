package controller.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.page.PageDAO;
import common.page.PageVO;
import model.movie.MovieDAO;

public class CategoryAction implements Action {

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
      }
      
      String mtype = request.getParameter("mtype");
      String search = request.getParameter("search");
      
      pVO.setCurPage(page);   //   현재 페이지   
      pVO.setPerPage(8);      //   페이지 게시물 수
      pVO.setPerPageSet(3);   //   페이지 수
      
  	  pVO = pDAO.paging(pVO,mtype,search,"movie");
      
      request.setAttribute("datas", mDAO.m_selectDB_all(mtype, search ,pVO));
      request.setAttribute("paging", pVO);
      request.setAttribute("page", page);
      request.setAttribute("mtype", mtype);
      request.setAttribute("search", search);
      
      forward.setRedirect(false);
      forward.setPath("categories.jsp");
      
      return forward;
   }

}