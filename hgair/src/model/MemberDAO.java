package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
	static String sql_INSERT="insert into member values (?,?,?,?)"; // 회원가입시 마일리지 0
	static String sql_SELCET="select * from member where id=?";
	static String sql_SAVE = "update member set m_point = m_point + ? where id = ?";
	static String sql_USE = "update member set m_point = m_point - ? where id = ?";

	private Connection conn=null;
	private PreparedStatement pstmt=null;

	//회원 가입
	public MemberVO singUp(MemberVO vo) {
		conn=JDBC.getConnection();
		try {
			pstmt=conn.prepareStatement(sql_INSERT);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setInt(4, vo.getM_point());
			pstmt.executeUpdate();
			//System.out.println("회원가입 !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	// selectOne
	public MemberVO selectOne(MemberVO vo) {
		conn=JDBC.getConnection();
		MemberVO data = null;
		try {
			pstmt=conn.prepareStatement(sql_SELCET);
			pstmt.setString(1, vo.getId());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data=new MemberVO();
				data.setId(rs.getString("id"));
				data.setName(rs.getString("name"));
				data.setM_point(rs.getInt("m_point"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBC.close(conn, pstmt);
		}
		return data;

	}

	// 마일리지 사용 적립

	public void mSave(TicketVO vo) {
		conn=JDBC.getConnection();
		try {
			pstmt=conn.prepareStatement(sql_SAVE);
			pstmt.setInt(1, vo.getPayment()/10);
			pstmt.setString(2, vo.getId());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}

	}
	
	public void mUse(TicketVO vo){
		conn=JDBC.getConnection();
		try {
			// 마일리지 사용
			pstmt=conn.prepareStatement(sql_USE);
			pstmt.setInt(1, vo.getUse_point());
			pstmt.setString(2, vo.getId());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC.close(conn, pstmt);
		}
	}



}
