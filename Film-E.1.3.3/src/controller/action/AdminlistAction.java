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
      } // ���� �ߴ� mcnt�� �Ȱ��� ���� 
      
      ArrayList<MovieVO> datas = new ArrayList<MovieVO>(); // selectAll�Ǵ� arraylist
      
      String search = request.getParameter("search"); // SelectAll�� ���� �˻��� �ϱ����� search�� type�� �޾ƿ�
      String mtype = request.getParameter("mtype");      
      
      pVO.setCurPage(page);   //   ���� ������   
      pVO.setPerPage(8);      //   ������ �Խù� ��
      pVO.setPerPageSet(3);   //   ������ ��
      
  	  pVO = pDAO.paging(pVO,mtype,search,"movie");
      
      datas = mDAO.m_selectDB_all(mtype, search, pVO ); 
      request.setAttribute("datas", datas);  // v���� ����� ��
      request.setAttribute("paging", pVO);
      request.setAttribute("page", page);
      
      
      forward.setRedirect(false);
      forward.setPath("adminlist.jsp");
      return forward;
   }

}