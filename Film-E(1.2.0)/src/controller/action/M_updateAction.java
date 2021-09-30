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

public class M_updateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ActionForward forward = new ActionForward(); 
		
		MovieDAO mDAO = new MovieDAO();
		MovieVO mVO = new MovieVO();
		PrintWriter out = response.getWriter();
		String saveDir = request.getSession().getServletContext().getRealPath("img"); // 이미지 경로
		
		int maxSize = 16*1024*1024;
		String encoding = "UTF-8";
		
		MultipartRequest multi;
		multi = new MultipartRequest(request, saveDir, maxSize, encoding, new DefaultFileRenamePolicy());
		
		mVO.setMpk(request.getParameter("mpk")); // selectone에 필요한 값
		String prefile = mDAO.m_selectDB_one(mVO).getFilename(); // // selectone으로 전에 있는 DB값 확인
		mVO.setFilename(multi.getFilesystemName("filename")); // Update시 넣을 파일을 받아오는 로직
		mVO.setMdate(multi.getParameter("mdate"));
		mVO.setContent(multi.getParameter("content"));
		mVO.setMtype(multi.getParameter("mtype"));
		mVO.setTitle(multi.getParameter("title"));
		

		File file = null;		// 두번 쓰기 귀찮아서 선언
		
		if(mDAO.m_updateDB(mVO)) {
			// 수정 성공 시 
			saveDir += "/" + prefile; // 전에 있는 DB 값
			file = new File(saveDir); 
			file.delete(); // 파일 삭제 
			forward.setRedirect(false);
			forward.setPath("adminlist.do");
		}
		else {
			// 수정 실패시
			saveDir += "/" + mVO.getFilename(); // DB에 넣을라고 하는 값
			file = new File(saveDir);  
			if(file.exists()) { // 존재 하면 
				file.delete();  // 삭제
			}
			response.setContentType("text/html; charset=UTF-8");		
			out.println("<script>alert('사진 수정!');history.go(-1)</script>"); // 사진 수정 실패 시 alert창

		}
		
		return forward;
	}

}
