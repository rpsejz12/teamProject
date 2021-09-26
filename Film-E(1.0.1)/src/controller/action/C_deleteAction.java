package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.client.ClientDAO;
import model.client.ClientVO;

public class C_deleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		ActionForward forward = null; // output
		
		ClientDAO cDAO = new ClientDAO();
		ClientVO cVO = new ClientVO();
//		cVO.setId(request.getParameter("id"));
		
		HttpSession session = request.getSession();
		cVO.setId((String)session.getAttribute("sessionID"));
		
		if (cDAO.c_deleteDB(cVO)) {
			forward = new ActionForward();
			forward.setPath("main.do");
			forward.setRedirect(false);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('È¸¿ø Å»Åð ½ÇÆÐ!');history.go(-1)</script>");
		}
		
		return forward;
	}
}