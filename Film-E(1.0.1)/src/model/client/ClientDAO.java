package model.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.common.JNDI;


public class ClientDAO {
	
	String login = "select * from client where id= ? and pw = ?"; // 로그인
	String insert = "insert into client values (?,?,?)"; // 회원가입
	String delete = "delete from client where id = ?"; // 회원탈퇴
	String update = "update client set id = ?, pw = ?, email= ? where id = ?"; // 회원정보 수정
	
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	
	ClientVO data= null;
	
	public ClientVO login(ClientVO invo) { //로그인 인증
		conn =JNDI.connect();
		
		// String sql= "select * from client where id= ? and pw = ?";
		
		try {
			pstmt=conn.prepareStatement(login);
			pstmt.setString(1, invo.getId());
			pstmt.setString(2, invo.getPw());
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
		
		// String sql= "insert into client values (?,?,?)";
		
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
	
	public boolean c_deleteDB(ClientVO vo) { // 회원탈퇴
		conn = JNDI.connect();
		
		boolean result = false;
		
		// String sql ="delete from client where id = ?";
		
		try {
			pstmt=conn.prepareStatement(delete);
			pstmt.setString(1, vo.getId());
			pstmt.executeQuery();
			
			result= true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return result;
		
	}
	
	public boolean c_updateDB(ClientVO vo) { // 회원 정보 수정
		conn= JNDI.connect();
		
		boolean result=false;
		
		// String sql ="update client set id = ?, pw = ?, email= ? where id = ?";
		
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
	
}
