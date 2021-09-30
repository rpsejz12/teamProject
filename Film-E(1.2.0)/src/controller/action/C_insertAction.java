package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.client.ClientDAO;
import model.client.ClientVO;

public class C_insertAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ActionForward forward = null; // output
		
		ClientDAO cDAO = new ClientDAO();
		ClientVO cVO = new ClientVO();
		cVO.setId(request.getParameter("id"));
		cVO.setPw(request.getParameter("pw"));
		cVO.setEmail(request.getParameter("email"));
		
		PrintWriter out = response.getWriter();
		
		if (cDAO.c_insertDB(cVO)) {
			forward = new ActionForward();
			forward.setPath("login.jsp");
			forward.setRedirect(false);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('회원가입 실패!');history.go(-1)</script>");
		}
		
		return forward;
	}
}