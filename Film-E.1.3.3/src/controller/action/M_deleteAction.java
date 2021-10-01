package controller.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.movie.MovieDAO;
import model.movie.MovieVO;

public class M_deleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		MovieDAO mDAO = new MovieDAO();
		MovieVO mVO = new MovieVO();
		
		
		String saveDir = request.getSession().getServletContext().getRealPath("img");	// �̹��� ��� 
		
		mVO.setMpk(request.getParameter("mpk"));
		mVO.setFilename(mDAO.m_selectDB_one(mVO).getFilename()); // select_one�� ����ؼ� DB�� �ִ� filename�� �޾ƿ�
		
		try {
			if(mDAO.m_deleteDB(mVO)) { // ���� ����
				saveDir += "/" + mVO.getFilename();
				File file = new File(saveDir);
				if(file.exists()) {	// ���� �ִ�?
					file.delete();	// ���� ��
				}
				System.out.println("���� ���� ����");
				forward.setRedirect(true);
				forward.setPath("adminlist.do");//
			}
			else {
				System.out.println("���� ���� ����");
				
				response.setContentType("text/html; charset=UTF-8");
				out.println("<script>alert('���� ���� ����!');history.go(-1)</script>"); //�Խù� ���� ���� �� alert

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
