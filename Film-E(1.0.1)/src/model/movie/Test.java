package model.movie;

public class Test {
	public static void main(String[] args) {
		
		MovieDAO dao = new MovieDAO();
		MovieVO vo = new MovieVO();
		
		
		
		vo.setContent("���볻�볻��");
		vo.setFilename("�׽�Ʈ");
		vo.setMtype("ACTION");
		vo.setTitle("����1");
		vo.setMdate("sysdate");
	
		dao.m_insertDB(vo);
		
		
		
	}
}
