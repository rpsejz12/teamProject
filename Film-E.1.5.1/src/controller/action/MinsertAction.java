package controller.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.movie.MovieDAO;
import model.movie.MovieVO;

public class MinsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ActionForward forward = new ActionForward();
		MovieDAO mDAO = new MovieDAO();
		MovieVO mVO = new MovieVO();
		PrintWriter out = response.getWriter();

		
		String saveDir = request.getSession().getServletContext().getRealPath("img");// 해당 폴더에 이미지를 저장시킴
		System.out.println(saveDir);
		//System.out.println("saveDir"); 경로 확인 로깅
		int maxSize = 16*1024*1024; // 최대 16mb
		String encoding = "UTF-8";
		MultipartRequest multi;
		multi = new MultipartRequest(request, saveDir, maxSize, encoding, new DefaultFileRenamePolicy());
		 
		mVO.setFilename(multi.getFilesystemName("filename")); // v에서 넣어주는 파일 
		mVO.setContent(multi.getParameter("content"));	
		mVO.setMdate(multi.getParameter("mdate"));
		mVO.setMpk(multi.getParameter("mpk"));
		mVO.setMtype(multi.getParameter("mtype"));
		mVO.setTitle(multi.getParameter("title"));
		// System.out.println(mVO);
		
		if(mDAO.m_insertDB(mVO)) { 
			//삽입 성공 시
			forward.setRedirect(true);
			forward.setPath("Adminlist.do");
		}
		else {
			// 실패 시
			saveDir += "/" + mVO.getFilename(); // 등록에 실패하면 v에서 등록한 사진 파일과 db에 있는 사진파일이 겹칠 수 있기때문에 v에서 등록한 사진 파일을 지우기 위해서 saveDir에 저장
			File file = new File(saveDir);
			if(file.exists()) { // 파일이 존재하면 삭제
				file.delete();
			}
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('게시물 등록 실패');history.go(-1)</script>");// 게시물 등록 실패 시 alert
		}
		return forward;
	}

}
