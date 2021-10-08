package controller.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.movie.MovieDAO;
import model.movie.MovieVO;

public class MdeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		MovieDAO mDAO = new MovieDAO();
		MovieVO mVO = new MovieVO();
		
		
		String saveDir = request.getSession().getServletContext().getRealPath("img");	// 이미지 경로 
		
		mVO.setMpk(request.getParameter("mpk"));
		mVO.setFilename(mDAO.m_selectDB_one(mVO).getFilename()); // 삭제가 실패했을때  select_one을 사용해서 DB에 있던 filename을 받아옴
		
		try { // m_deleteDB가 트랜잭션을 사용해서 controller에서 try catch문 사용
			if(mDAO.m_deleteDB(mVO)) { // 파일 삭제 메소드
				saveDir += "/" + mVO.getFilename();
				File file = new File(saveDir);
				if(file.exists()) {	// 파일이 존재하면 삭제
					file.delete();	
				}
				//System.out.println("파일 삭제 성공");
				forward.setRedirect(true);
				forward.setPath("Adminlist.do");
			}
			else {
				//System.out.println("파일 삭제 실패");
				response.setContentType("text/html; charset=UTF-8");
				out.println("<script>alert('사진 삭제 실패!');history.go(-1)</script>"); //게시물 삭제 실패 시 alert

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
