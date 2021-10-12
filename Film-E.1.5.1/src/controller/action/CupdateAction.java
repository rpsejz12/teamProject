package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.client.ClientDAO;
import model.client.ClientVO;

public class CupdateAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ActionForward forward = null; // output
		
		ClientDAO cDAO = new ClientDAO();
		ClientVO cVO = new ClientVO();
		
		cVO.setId(request.getParameter("id"));
		cVO.setPw(request.getParameter("pw"));
		cVO.setEmail(request.getParameter("email"));
		
		if (cDAO.c_updateDB(cVO)) {
			forward = new ActionForward();
			forward.setPath("CselectOne.do");
			forward.setRedirect(true);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원 정보 변경에 실패하였습니다. 다시 시도해 주세요.');history.go(-1)</script>");
		}
		return forward;
	}
}