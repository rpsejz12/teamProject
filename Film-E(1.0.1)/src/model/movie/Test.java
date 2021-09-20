package model.movie;

public class Test {
	public static void main(String[] args) {
		
		MovieDAO dao = new MovieDAO();
		MovieVO vo = new MovieVO();
		
		
		
		vo.setContent("내용내용내용");
		vo.setFilename("테스트");
		vo.setMtype("ACTION");
		vo.setTitle("제목1");
		vo.setMdate("sysdate");
	
		dao.m_insertDB(vo);
		
		
		
	}
}
