package controller.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.movie.MovieDAO;
import model.movie.MovieVO;

public class MupdateAction implements Action {

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
		//saveDir : 절대 경로 값을 뜻하며 업로드 된 파일이 저장될 폴더를 설정합니다. 경로 값이 틀리면 예외처리
		//maxSize : 16mb
		//encoding : UTF-8
		//new DefaultFileRenamePolicy() : 만약에 업로드 된 파일이 저장되는 폴더에 같은 이름의 파일이 존재한다면 파일명 뒤에 숫자가 1부터 붙습니다.


		mVO.setMpk(request.getParameter("mpk")); // selectone에 필요한 값
		String prefile = mDAO.m_selectDB_one(mVO).getFilename(); // // selectone으로 전에 있는 DB값 확인
		mVO.setFilename(multi.getFilesystemName("filename")); // Update시 넣을 파일을 받아오는 로직  
		//getFileNames("filename") : 업로드 된 파일들에 대한 이름을 Enumeration 객체에 String 형태로 담아 반환함
		
		mVO.setMdate(multi.getParameter("mdate"));
		mVO.setContent(multi.getParameter("content"));
		mVO.setMtype(multi.getParameter("mtype"));
		mVO.setTitle(multi.getParameter("title"));

		if(!prefile.substring(0, 4).equals("http")) {		// 처음 4글자가 http가 아니면 앞에 img/추가
			prefile = prefile.substring(4); // img/가 2번 붙어서 삭제를 함
		}

		File file = null;      // 두번 쓰기 귀찮아서 선언


		if(mVO.getFilename() == null) { // v에서 받아온 파일네임이 null일때
			mVO.setFilename(prefile);	// db에 있는 사진파일 사용
			if(mDAO.m_updateDB(mVO)) {	
				forward.setRedirect(true);
				forward.setPath("Adminlist.do");
			}else {
				response.setContentType("text/html; charset=UTF-8");      
				out.println("<script>alert('사진 수정!');history.go(-1)</script>"); // 사진 수정 실패 시 alert창
			}
		}
		else {
			if(mDAO.m_updateDB(mVO)) { // v에서 받은 사진 파일이 null이 아닐때
				// 수정 성공 시 
				saveDir += "/" + prefile; // 전에 있는 DB 값
				file = new File(saveDir); 
				file.delete(); // 파일 삭제 
				forward.setRedirect(true);
				forward.setPath("Adminlist.do");
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

		}
		return forward;





	}

}