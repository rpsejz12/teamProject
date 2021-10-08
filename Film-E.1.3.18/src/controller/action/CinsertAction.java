package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.client.ClientDAO;
import model.client.ClientVO;

public class CinsertAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ActionForward forward = null; // output
		
		ClientDAO cDAO = new ClientDAO();
		ClientVO cVO = new ClientVO();
		cVO.setId(request.getParameter("id"));
		cVO.setPw(request.getParameter("pw"));
		cVO.setEmail(request.getParameter("email"));
		
		
		if (cDAO.c_insertDB(cVO)) {
			System.out.println("들어왔다");
			forward = new ActionForward();
			forward.setPath("login.jsp");
			forward.setRedirect(true);
		} else {
			System.out.println("아닌데 여긴데");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원가입 실패!');history.go(-1)</script>");
		}
		
		return forward;
	}
}