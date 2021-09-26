package model.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.common.JNDI;


public class MovieDAO {

	// �帣�� �������� �� ���, ���̺��� �ϳ� �� �ʿ�(�迭�� ����)


	String rselectAll = "SELECT * FROM MOVIE WHERE MPK = ? ORDER BY RDATE DESC";				//���� ����Ʈ
	String rdelete = "DELETE FROM REVIEW WHERE MPK = ?";					//���� ����

	String selectAll = "SELECT * FROM MOVIE ORDER BY TITLE ASC";			//��ȭ ��ü ����Ʈ
	String mpk = "SELECT MPK FROM MOVIE";			//��ȭ ��ü ����Ʈ
	String selectOne = "SELECT * FROM MOVIE WHERE MPK = ?";					//��ȭ Ŭ��
	String search = "SELECT * FROM MOVIE WHERE TITLE LIKE ORDER BY TITLE ASC";		//��ȭ �˻�
	String insert = "INSERT INTO MOVIE VALUES (?,?,?,?,?,?)";									//��ȭ ���
	String delete = "DELETE FROM MOVIE WHERE MPK = ?";						//��ȭ ����
	String update = "UPDATE MOVIE SET TITLE  = ?, CONTENT = ?, MTYPE = ?, MDATE ?,  WHERE MPK = ?";				//��ȭ ����

	MovieVO data = null;
	ArrayList<MovieVO> datas = null;
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;

	boolean flag = false;

	public ArrayList<MovieVO> m_selectDB_all(){			//��ȭ ��ü ����Ʈ
		datas = new ArrayList<MovieVO>();
		conn = JNDI.connect();
		try {
			pstmt=conn.prepareStatement(selectAll);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				data = new MovieVO();
				data.setMpk(rs.getString("mpk"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setMtype(rs.getString("mtype"));
				data.setMdate(rs.getString("mdate"));
				data.setFilename(rs.getString("filename"));
				datas.add(data);
			}
			rs.close();
			System.out.println("MovieDAO ��ȭ ��ü ����Ʈ :" + datas);
		}
		catch (SQLException e) {
			System.out.println("MovieDAO ��ȭ ��ü ����Ʈ ����");
			e.printStackTrace();
		}
		finally {
			JNDI.disconnect(pstmt, conn);
		}
		return datas;	
	}

	public MovieVO m_selectDB_one(MovieVO vo){			//��ȭ Ŭ��
		System.out.println("MovieDAO ��ȭ Ŭ�� VO :" + vo);
		conn = JNDI.connect();
		try {
			pstmt=conn.prepareStatement(selectOne);
			pstmt.setString(1, vo.getMpk());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				data = new MovieVO();
				data.setMpk(rs.getString("mpk"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setMtype(rs.getString("mtype"));
				data.setMdate(rs.getString("mdate"));
				data.setFilename(rs.getString("filename"));
			}
			rs.close();
			System.out.println("MovieDAO ��ȭ Ŭ�� :" + data);
		}
		catch (SQLException e) {
			System.out.println("MovieDAO ��ȭ Ŭ�� ����");
			e.printStackTrace();
		}
		finally {
			JNDI.disconnect(pstmt, conn);
		}
		return data;	
	}

	public ArrayList<MovieVO> search(MovieVO vo){				//��ȭ �˻�
		System.out.println("MovieDAO �˻� VO :" + vo);
		datas = new ArrayList<MovieVO>();
		search = search + "'%" + vo.getTitle() + "%'";
		conn = JNDI.connect();
		try {
			pstmt=conn.prepareStatement(search);			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				data = new MovieVO();
				data.setMpk(rs.getString("mpk"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setMtype(rs.getString("mtype"));
				data.setMdate(rs.getString("mdate"));
				data.setFilename(rs.getString("filename"));
				datas.add(data);
			}
			rs.close();
			System.out.println("MovieDAO �˻� :" + datas);
		}
		catch (SQLException e) {
			System.out.println("MovieDAO ��ȭ �˻� ����");
			e.printStackTrace();
		}
		finally {
			JNDI.disconnect(pstmt, conn);
		}
		return datas;			
	}


	public Boolean m_insertDB(MovieVO vo) { 			//��ȭ ���
		System.out.println("MovieDAO ��ȭ ��� VO :" + vo);
		conn = JNDI.connect();
		try {
			
			
			
			pstmt = conn.prepareStatement(mpk);
			rs = pstmt.executeQuery();
			String mpkStr = null;	//mpk
			int mpkInt = 0;			
			int cnt = 0;
			
			
			while(rs.next()) {
				mpkStr = rs.getString("mpk").substring(1);		//mpk type ������ �޺κи� ������
				mpkInt = Integer.parseInt(mpkStr);
				mpkInt++;
				cnt++;
			}
			
			if(cnt == 0) {				//rs �� null �ϰ��
				mpkInt = 1001;
			}
			
			
			mpkStr = vo.getMtype().charAt(0) + mpkInt + "";
			System.out.println("mpk :" + mpkStr);
		
			pstmt=conn.prepareStatement(insert);
			
			pstmt.setString(1, mpkStr);
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getMtype());
			pstmt.setString(5, vo.getMdate());
			pstmt.setString(6, vo.getFilename());
			if(pstmt.executeUpdate() != 0) {			//��� ����
				System.out.println("MovieDAO ��ȭ ��� ����");
				flag = true;
			}
		}
		catch (SQLException e) {
			System.out.println("MovieDAO ��ȭ ��� ����");
			e.printStackTrace();
		}
		finally {
			JNDI.disconnect(pstmt, conn);
		}
		return flag;							//��� ����

	}

	public Boolean m_updateDB(MovieVO vo) { 			//��ȭ ����
		System.out.println("MovieDAO ��ȭ ���� VO :" + vo);
		conn = JNDI.connect();
		try {
			pstmt=conn.prepareStatement(update);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getMtype());
			pstmt.setString(4, vo.getMdate());
			pstmt.setString(5, vo.getFilename());

			if(pstmt.executeUpdate() != 0) {			//���� ����
				System.out.println("MovieDAO ��ȭ ���� ����");
				flag = true;
			}
		}
		catch (SQLException e) {
			System.out.println("MovieDAO ��ȭ ���� ����");
			e.printStackTrace();
		}
		finally {
			JNDI.disconnect(pstmt, conn);
		}
		return flag;								//���� ����

	}

	public Boolean m_deleteDB(MovieVO vo) throws SQLException { 				//��ȭ ����
		System.out.println("MovieDAO ��ȭ ���� VO :" + vo);
		conn = JNDI.connect();
		try {
			conn.setAutoCommit(false); 				//����Ŀ�� ����

			pstmt=conn.prepareStatement(delete);	//��ȭ ����
			pstmt.setString(1, vo.getMpk());
			if(pstmt.executeUpdate() != 0) {
				System.out.println("MovieDAO movie delete ����");
			}


			pstmt=conn.prepareStatement(rdelete);	//���� ����
			pstmt.setString(1, vo.getMpk());

			if(pstmt.executeUpdate() != 0) {
				System.out.println("MovieDAO movie delete,rdelete ����");
			}



			conn.commit();				//Ŀ��
			flag = true;
			System.out.println("MovieDAO movie delete Ŀ�� ����");

		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MovieDAO delete Ʈ����� ����");
			conn.rollback();		//�ѹ�
			flag = false;
		}finally {
			conn.setAutoCommit(true);
			JNDI.disconnect(pstmt, conn);				
		}
		return flag;

	}

}