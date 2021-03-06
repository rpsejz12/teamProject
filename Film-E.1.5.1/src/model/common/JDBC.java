package model.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC {
	// JDBC : DB와 연동하기 위한 드라이버 로드하는 과정

	// Connection 객체 생성 (open)
	public static Connection getConnection() {
		Connection conn = null;
		
		String DName_oracle = "com.mysql.cj.jdbc.Driver";  // JDBC의 드라이버 클래스명
		
		String url = "jdbc:mysql://localhost:3306/kimdb";
		String user="root";
		String password="7672";
		
		try {
			Class.forName(DName_oracle);
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	
	// 생성된 Connection 객체를 close
	public static void close(Connection conn, PreparedStatement pstmt) {
		
		try {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
