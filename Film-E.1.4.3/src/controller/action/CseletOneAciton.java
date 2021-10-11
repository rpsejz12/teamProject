package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.client.ClientDAO;
import model.client.ClientVO;

public class CseletOneAciton implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = null;
		
		ClientDAO cDAO = new ClientDAO();
		ClientVO cVO = new ClientVO();
		
		HttpSession session = request.getSession();
		cVO.setId((String)session.getAttribute("sessionID"));
		
		if (cDAO.c_selectDB_one(cVO) != null) {
			request.setAttribute("data", cDAO.c_selectDB_one(cVO));
			forward = new ActionForward();
			forward.setPath("mypage.jsp");
			forward.setRedirect(false);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('접근 실패!');history.go(-1)</script>");
		}
		return forward;
	}
}