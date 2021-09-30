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

public class M_insertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ActionForward forward = new ActionForward();
		MovieDAO mDAO = new MovieDAO();
		MovieVO mVO = new MovieVO();
		PrintWriter out = response.getWriter();

		
		String saveDir = request.getSession().getServletContext().getRealPath("img");//
		
		int maxSize = 16*1024*1024; // �ִ� 16mb
		String encoding = "UTF-8";
		MultipartRequest multi;
		multi = new MultipartRequest(request, saveDir, maxSize, encoding, new DefaultFileRenamePolicy());
		 
		mVO.setFilename(multi.getFilesystemName("filename")); // v���� �־��ִ� ���� 
		mVO.setContent(multi.getParameter("content"));
		mVO.setMdate(multi.getParameter("mdate"));
		//mVO.setMpk(multi.getParameter("mpk"));
		mVO.setMtype(multi.getParameter("mtype"));
		mVO.setTitle(multi.getParameter("title"));
		
		if(mDAO.m_insertDB(mVO)) { 
			//���� ���� ��
			forward.setRedirect(false);
			forward.setPath("adminlist.do");
		}
		else {
			// ���� ��
			saveDir += "/" + mVO.getFilename(); // �־��ٶ�� �ϴ� DB���� ���� 
			File file = new File(saveDir);
			if(file.exists()) { 
				file.delete();
			}
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('�Խù� ��� ����');history.go(-1)</script>");// �Խù� ��� ���� �� alert
		}
		return forward;
	}

}
