package model.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.page.PageVO;
import model.common.JNDI;


public class MovieDAO {

	// �帣�� �������� �� ���, ���̺��� �ϳ� �� �ʿ�(�迭�� ����)


	String isHttp = null;
	String rselectAll = "SELECT * FROM MOVIE WHERE MPK = ? ORDER BY RDATE DESC";				//���� ����Ʈ
	String rdelete = "DELETE FROM REVIEW WHERE MPK = ?";					//���� ����

	String selectAll = "SELECT * FROM(SELECT A.*, ROWNUM AS RNUM FROM(SELECT * FROM MOVIE ORDER BY TITLE ASC) A WHERE ROWNUM < ?) WHERE RNUM >=?";			//��ȭ ��ü ����Ʈ
	String selectAllT = "SELECT * FROM(SELECT A.*, ROWNUM AS RNUM FROM(SELECT * FROM MOVIE WHERE MTYPE = ? ORDER BY TITLE ASC) A WHERE ROWNUM < ?) WHERE RNUM >=?";
	String selectAllSearch = "SELECT * FROM(SELECT A.*, ROWNUM AS RNUM FROM(SELECT * FROM MOVIE WHERE TITLE LIKE ? ORDER BY TITLE ASC) A WHERE ROWNUM < ?) WHERE RNUM >=?";
	String selectAllSearchT = "SELECT * FROM(SELECT A.*, ROWNUM AS RNUM FROM(SELECT * FROM MOVIE WHERE MTYPE = ? AND TITLE LIKE ? ORDER BY TITLE ASC) A WHERE ROWNUM < ?) WHERE RNUM >=?";
	String mpk = "SELECT MPK FROM MOVIE";									//��ȭ ��ü ����Ʈ
	String selectOne = "SELECT * FROM MOVIE WHERE MPK = ?";					//��ȭ Ŭ��
	String insert = "INSERT INTO MOVIE VALUES (?,?,?,?,?,?)";				//��ȭ ���
	String delete = "DELETE FROM MOVIE WHERE MPK = ?";						//��ȭ ����
	String update = "UPDATE MOVIE SET TITLE  = ?, CONTENT = ?, MTYPE = ?, MDATE ?,  WHERE MPK = ?";				//��ȭ ����

	MovieVO data = null;
	ArrayList<MovieVO> datas = null;
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;

	boolean flag = false;

	public ArrayList<MovieVO> m_selectDB_all(String mtype, String search, PageVO vo){			//��ȭ ����Ʈ
		datas = new ArrayList<MovieVO>();
		conn = JNDI.connect();
		try {

			if(mtype == null || mtype.equals("")) {				

				if(search == null || search.equals("")) {		//type �� ���� �˻��� ������ ��ü ����Ʈ ���
					pstmt=conn.prepareStatement(selectAll);
					pstmt.setInt(1, vo.getEnd());
					pstmt.setInt(2, vo.getStart());
					System.out.println("��ü ����Ʈ");
				}
				else {											//type �� ���� �˻��� ������ ��ü ����Ʈ ���� �˻��Ͽ� ���
					pstmt=conn.prepareStatement(selectAllSearch);
					pstmt.setString(1, "%"+search+"%" );
					pstmt.setInt(2, vo.getEnd());
					pstmt.setInt(3, vo.getStart());
					System.out.println("��ü ����Ʈ �˻�");
				}
			}

			else{												
				if(search == null || search.equals("")) {		//type�� �������� type �帣 ����Ʈ�� ���
					pstmt=conn.prepareStatement(selectAllT);
					pstmt.setString(1, mtype);
					pstmt.setInt(2, vo.getEnd());
					pstmt.setInt(3, vo.getStart());
					System.out.println(mtype+" ����Ʈ");
				}
				else {											//type�� �ְ�, �˻��ҽ� type �帣 ����Ʈ�� �˻��Ͽ� ���
					pstmt=conn.prepareStatement(selectAllT);
					pstmt.setString(1, mtype);
					pstmt.setString(2, "%"+search+"%" );
					pstmt.setInt(3, vo.getEnd());
					pstmt.setInt(4, vo.getStart());
					System.out.println(mtype+" ����Ʈ �˻�");
				}

			}

			rs=pstmt.executeQuery();
			while(rs.next()) {
				data = new MovieVO();
				data.setMpk(rs.getString("mpk"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setMtype(rs.getString("mtype"));
				data.setMdate(rs.getString("mdate"));
				
				isHttp = rs.getString("filename");
				
				if(!isHttp.substring(0, 4).equals("http")) {		// ó�� 4���ڰ� http�� �ƴϸ� �տ� img/�߰�
					isHttp = "img/"+isHttp;
				}
					
				data.setFilename(isHttp);
				datas.add(data);
			}
			rs.close();
			System.out.println("MovieDAO ��ȭ ����Ʈ :" + datas);
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
				
				isHttp = rs.getString("filename");
				
				if(!isHttp.substring(0, 4).equals("http")) {		// ó�� 4���ڰ� http�� �ƴϸ� �տ� img/�߰�
					isHttp = "img/"+isHttp;
				}
				
				data.setFilename(rs.getString(isHttp));
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


	public Boolean m_insertDB(MovieVO vo) { 			//��ȭ ���
		System.out.println("MovieDAO ��ȭ ��� VO :" + vo);
		conn = JNDI.connect();
		try {



			pstmt = conn.prepareStatement(mpk);
			rs = pstmt.executeQuery();

			String mpkStr = null;	//	mpk
			String mpkType = null;	//  'A'1001 �տ� �� ���� 
			int mpkInt = 0;			
			int cnt = 0;

			while(rs.next()) {
				mpkStr = rs.getString("mpk").substring(2);		//mpk type ������ �޺κи� ������
				mpkInt = Integer.parseInt(mpkStr);
				mpkInt++;
				cnt++;
			}

			if(cnt == 0) {				//rs �� null �ϰ��
				mpkInt = 1001;
			}

			if(vo.getMtype().equals("�׼�")) {
				mpkType = "AC";
			}
			else if(vo.getMtype().equals("�ִϸ��̼�")) {
				mpkType = "AN";
			}
			else if(vo.getMtype().equals("���/�θེ")) {
				mpkType = "RO";
			}
			else if(vo.getMtype().equals("���")) {
				mpkType = "DR";
			}
			else if(vo.getMtype().equals("��ť���͸�")) {
				mpkType = "DC";
			}
			else {	//��Ÿ �帣
				mpkType = "EX";
			}
			
			mpkStr = mpkType + mpkInt;


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
		}finally {
			conn.setAutoCommit(true);
			JNDI.disconnect(pstmt, conn);				
		}
		return flag;

	}

}
