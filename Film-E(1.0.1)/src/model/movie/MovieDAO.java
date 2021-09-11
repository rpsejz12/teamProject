package model.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.common.JNDI;


public class MovieDAO {

	// �帣�� �������� �� ���, ���̺��� �ϳ� �� �ʿ�(�迭�� ����)


	String rselectAll = "SELECT * FROM MOVIE WHERE MPK = ?";				//���� ����Ʈ
	String rdelete = "DELETE FROM REVIEW WHERE MPK = ?";					//���� ����

	String selectAll = "SELECT * FROM MOVIE"; 								//��ȭ ��ü ����Ʈ
	String selectOne = "SELECT * FROM MOVIE WHERE MPK = ?";					//��ȭ Ŭ��
	String search = "SELECT * FROM MOVIE WHERE TITLE LIKE ";				//��ȭ �˻�
	String insert = "INSERT INTO MOVIE()";									//��ȭ ���
	String insert1 = "insert into member(mname, mid, mpw) values(?,?,?)";	//��ȭ ���
	String delete = "DELETE FROM MOVIE WHERE MPK = ?";						//��ȭ ����
	String update = "UPDATE MOVIE SET TITLE  = ?, CONTENT = ?, MTYPE = ?, MDATE ?,  WHERE MPK = ?";				//��ȭ ����

	MovieVO data = null;
	ArrayList<MovieVO> datas = null;
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;

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
			pstmt=conn.prepareStatement(insert);
			pstmt.setString(1, vo.getMpk());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getMtype());
			pstmt.setString(5, vo.getMdate());

			if(vo.getMpk() != null) {			//��� ����
				pstmt.executeUpdate();
				System.out.println("MovieDAO ��ȭ ��� ����");
				return true;
			}
		}
		catch (SQLException e) {
			System.out.println("MovieDAO ��ȭ ��� ����");
			e.printStackTrace();
		}
		finally {
			JNDI.disconnect(pstmt, conn);
		}
		return false;							//��� ����

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

			if(vo.getMpk() != null) {			//���� ����
				pstmt.executeUpdate();
				System.out.println("MovieDAO ��ȭ ���� ����");
				return true;
			}
		}
		catch (SQLException e) {
			System.out.println("MovieDAO ��ȭ ���� ����");
			e.printStackTrace();
		}
		finally {
			JNDI.disconnect(pstmt, conn);
		}
		return false;								//���� ����

	}

	public Boolean m_deleteDB(MovieVO vo) { 				//��ȭ ����
		System.out.println("MovieDAO ��ȭ ���� VO :" + vo);
		conn = JNDI.connect();
		try {
			conn.setAutoCommit(false); 				//����Ŀ�� ����

			pstmt=conn.prepareStatement(delete);	//��ȭ ����
			pstmt.setString(1, vo.getMpk());
			pstmt.executeUpdate();

			pstmt=conn.prepareStatement(rdelete);	//���� ����
			pstmt.setString(1, vo.getMpk());
			pstmt.executeUpdate();

			pstmt=conn.prepareStatement(selectOne);	//��ȭ ���� üũ
			pstmt.setString(1, vo.getMpk());
			rs = pstmt.executeQuery();

			if(rs.next()) {
				System.out.println("MovieDAO ��ȭ ���� ����(��ȭ ����)");
				conn.rollback();		//�ѹ�
				return false;
			}

			pstmt=conn.prepareStatement(rselectAll);	//���� ���� üũ
			pstmt.setString(1, vo.getMpk());
			rs = pstmt.executeQuery();

			if(rs.next()) {
				System.out.println("MovieDAO ��ȭ ���� ����(���� ����)");
				conn.rollback();		//�ѹ�
				return false;
			}
			rs.close();

			System.out.println("MovieDAO ��ȭ ���� ����");
			conn.commit();				//Ŀ��
			conn.setAutoCommit(true);
			JNDI.disconnect(pstmt, conn);		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return true;

	}

}
