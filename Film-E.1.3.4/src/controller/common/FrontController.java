package controller.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.ActionForward;
import controller.action.AdminAction;
import controller.action.AdminlistAction;
import controller.action.C_deleteAction;
import controller.action.C_insertAction;
import controller.action.C_seletOneAciton;
import controller.action.C_updateAction;
import controller.action.CategoryAction;
import controller.action.CheckIDAction;
import controller.action.LoginAction;
import controller.action.LogoutAction;
import controller.action.M_deleteAction;
import controller.action.M_insertAction;
import controller.action.M_updateAction;
import controller.action.MainAction;
import controller.action.R_deleteAction;
import controller.action.R_insertAction;
import controller.action.R_selectAllAction;

//"���" Ŭ���̾�Ʈ�� ��û�� ���� �������� ������ �ǰ�, �̰����� ��û�� ���� control.jsp�� �̵��ϵ��� ��

/*@WebServlet("*.do")*/ // �����ϰ� �� ��ĵ� ����
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	private void doAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		/* 1. Ŭ���̾�Ʈ�� "��û"�� �м��ϴ� �Լ� */
		String uri = request.getRequestURI(); // uri : /FrontController/main.do
		String cp = request.getContextPath(); // cp :/FrontController
		String action = uri.substring(cp.length()); // action : /main/do - ���� �׼ǰ��� ���� -> ���� ������ ����
		
		/* 2. control�� �����ϴ� ���� */
		ActionForward forward = null;
		
		// ȸ��, ���� ���� �׼� ó��
		
		if (action.equals("/main.do")) {
			forward = new MainAction().execute(request, response);
		} else if (action.equals("/login.do")) { // �α���
			forward = new LoginAction().execute(request, response); // ������������ �̵�
		} else if (action.equals("/logout.do")) { // �α׾ƿ�
			forward = new LogoutAction().execute(request, response); //  ������������ �̵�
		} else if (action.equals("/c_insert.do")) { // ȸ�� ���� 
			String pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
			String email = request.getParameter("email");
			if(Pattern.matches(pattern, email)) {
			    forward = new C_insertAction().execute(request, response); // �α��� �������� �̵�
			} else {
				PrintWriter out = response.getWriter();
			    out.println("<script>alert('�ùٸ� �̸��� ������ �ƴմϴ�.');history.go(-1)</script>");
			}
		} else if (action.equals("/checkID.do")) { // �ߺ� ���̵� üũ
			forward = new CheckIDAction().execute(request, response);
		} else if (action.equals("/mypage.do")) { // ȸ�� ���� ��ȸ
			forward = new C_seletOneAciton().execute(request, response); // ������������ �̵�
		} else if (action.equals("/c_delete.do")) { // ȸ�� Ż��
				forward = new C_deleteAction().execute(request, response);
		} else if (action.equals("/c_update.do")) { // ȸ�� ���� ����
			forward = new C_updateAction().execute(request, response); // ������������ �̵�
		} else if (action.equals("/r_selectAll.do")) { // ��� ���� ��ȸ
			forward = new R_selectAllAction().execute(request, response); // ������������ �̵�
		} else if (action.equals("/r_insert.do")) { // ���� ���
			forward = new R_insertAction().execute(request, response); // ������������ �̵�
		} else if (action.equals("/r_delete.do")) { // ���� ����
			forward = new R_deleteAction().execute(request, response); // ������������ �̵�
		} else if (action.equals("/admin.do")) {	// ������ �̵�
			forward = new AdminAction().execute(request, response); // �Խù� ���� ������ (����) �̵�
		} else if (action.equals("/adminlist.do")) { // �˻� �� selectAll ���
			forward = new AdminlistAction().execute(request, response); // ���� ������ �̵�
		} else if (action.equals("/categories.do")) { // �˻� �� SelectAll ��� 
			forward = new CategoryAction().execute(request, response);	// ī�װ� ������ �̵�
		} else if (action.equals("/m_delete.do")) {	 // �Խù� ����
			forward = new M_deleteAction().execute(request, response); // ����: ���� ������ ,����: �Խù� ���� ������(����) �̵� 
		} else if (action.equals("/m_insert.do")) {  // �Խù� ��� 
			forward = new M_insertAction().execute(request, response); // ����: ���� ������ ,����: �Խù� ���� ������(����) �̵�
		} else if (action.equals("/m_update.do")) {  // �Խù� ����
			forward = new M_updateAction().execute(request, response); // ����: ���� ������ ,����: �Խù� ���� ������(����) �̵�
		}
		else { // ���� �߸��� action���� ��� ��, null�� ��� ���� �������� ����
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/error/errer404.jsp");
		}
		
		/* 3. Ŭ���̾�Ʈ���� ��� ȭ�� ��� */
		if (forward != null) { // ���� forward == null�̸� ���� �׼�ó���� ����� ���� ���� ��!
			if (forward.isRedirect()) { // redirect ���
				response.sendRedirect(forward.getPath());
			} else { // forward ��� - ��û ����� �����Ͽ� ������ �̵�
				System.out.println("!!!!");
				// Dispatcher
				// : Ŭ���̾�Ʈ�κ��� ���ʿ� ���� ��û�� JSP/Servlet ������ ���ϴ� �ڿ����� ��û�� �ѱ�� ������ �����ϰų�,
				//   Ư�� �ڿ��� ó���� ��û�ϰ� ó�� ����� ������ ����� �����ϴ� Ŭ����
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath()); // ���� ���
				dispatcher.forward(request, response);
			}
		}
	}
}
