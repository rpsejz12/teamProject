package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.client.ClientDAO;

public class CheckIDAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = null; // output
		
		String id = request.getParameter("id");
		PrintWriter out = response.getWriter();
		
		ClientDAO cDAO = new ClientDAO();

		if (id == null || id == "") { // 입력값이 없는 경우
			out.println("null");
		} else {
			out.println(cDAO.checkID(request.getParameter("id")));
		}
		return forward;
	}
}
