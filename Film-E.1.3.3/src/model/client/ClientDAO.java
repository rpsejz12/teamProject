package model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.common.JNDI;


public class ClientDAO {

	// 제약조건이 걸리는것을 막기 위해 fk를 사용하지 않고 그 대신 트랙잭션을 깔끔하게 처리

	String login = "select * from client where id= ? and pw = ?"; // 로그인
	String insert = "insert into client values (?,?,?)"; // 회원가입
	String delete = "delete from client where id = ?"; // 회원탈퇴
	String update = "update client set id = ?, pw = ?, email= ? where id = ?"; // 회원정보 수정
	String rUpdate = "update review set id = '(알수없음)'  where id = ?";  // 회원 탈퇴시 리뷰 ID (알수없음)
	String selectONE = "select * from client where id = ?"; // 회원탈퇴 오류 확인
	String rSelectONE = "select * from review where id= ?"; // vo의  ID가  리뷰 남아있는지 확인!
	String checkID = "select * from client where id = ?"; // 회원가입 시 아이디 중복확인, 회원정보 수정시 id로 조회


	Connection conn=null;
	PreparedStatement pstmt=null; 

	ClientVO data= null;

	public ClientVO login(ClientVO vo) { //로그인 인증
		conn =JNDI.connect();


		try {
			pstmt=conn.prepareStatement(login);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				data=new ClientVO();
				data.setId(rs.getString("id"));
				data.setPw(rs.getString("pw"));
				data.setEmail(rs.getString("email"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return data;
	}

	public boolean c_insertDB(ClientVO vo) { // 회원가입시 데이터 추가
		conn=JNDI.connect();

		boolean result=false;
		try {
			pstmt=conn.prepareStatement(insert);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getEmail());	
			pstmt.executeUpdate();
			result=true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return result;
	}

	public boolean c_deleteDB(ClientVO vo) throws SQLException{ // 회원탈퇴
		conn = JNDI.connect();
		
		boolean result = false;

		try {
			conn.setAutoCommit(false); // 자동으로 DB에 commit이 선언 될때까지 sql이 적용되는 것을 막아줌! 
			// 오토커밋 해제


			pstmt=conn.prepareStatement(delete); // 회원탈퇴
			pstmt.setString(1, vo.getId());
			pstmt.executeUpdate();
	
			pstmt=conn.prepareStatement(rUpdate); // 회원탈퇴시 리뷰 id = '알수없음'으로 변경
			pstmt.setString(1, vo.getId());
			pstmt.executeUpdate();

			conn.commit(); // 커밋
			conn.setAutoCommit(true);
		
			result= true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ClientDAO delete 트랜잭션 오류");
			conn.rollback();		//롤백
			conn.setAutoCommit(true);
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return result;

	}

	/*public boolean c_deleteDB(ClientVO vo) { // 회원탈퇴
		conn = JNDI.connect();

		boolean result = false;


		try {
			conn.setAutoCommit(false); // 자동으로 DB에 commit이 선언 될때까지 sql이 적용되는 것을 막아줌!
			ResultSet rs=null;


			pstmt=conn.prepareStatement(delete);
			pstmt.setString(1, vo.getId());
			pstmt.executeUpdate();


			pstmt=conn.prepareStatement(rUpdate);
			pstmt.setString(1, vo.getId());
			pstmt.executeUpdate();


			pstmt=conn.prepareStatement(selectONE);
			pstmt.setString(1, vo.getId());
			rs=pstmt.executeQuery();
			if(rs.next()) { // ID가 남아있다면 ...
				conn.rollback();
			}


			pstmt=conn.prepareStatement(rSelectONE);
			pstmt.setString(1, vo.getId());
			rs=pstmt.executeQuery();
			if(rs.next()) { // 리뷰에 ID가 vo의 ID인지 확인!
				conn.rollback();
			}

			rs.close();
			conn.commit();
			conn.setAutoCommit(true);

			result= true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return result;

	}*/

	public boolean c_updateDB(ClientVO vo) { // 회원 정보 수정
		conn= JNDI.connect();

		boolean result=false;


		try {
			pstmt.getConnection().prepareStatement(update);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getId());
			pstmt.executeUpdate();


			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return result;
	}
	
	public ClientVO c_selectDB_one(ClientVO vo) { // 회원정보 수정시 id로 조회
		conn =JNDI.connect();


		try {
			pstmt=conn.prepareStatement(checkID);
			pstmt.setString(1, vo.getId());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				data=new ClientVO();
				data.setId(rs.getString("id"));
				data.setPw(rs.getString("pw"));
				data.setEmail(rs.getString("email"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return data;
	}
	

	public boolean checkID(String id) { // 회원가입시 아이디 중복확인

		conn= JNDI.connect();
		

		boolean exist = false;
		try {
			pstmt = conn.prepareStatement(checkID);
			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				exist = true;
			}

			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return exist;
	}

}
