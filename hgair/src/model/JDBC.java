package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC {
	public static Connection getConnection() {
		Connection conn=null;
		
		String DName="oracle.jdbc.driver.OracleDriver";

		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="kim";
		String password="1234";
		
		try {
			Class.forName(DName);
			conn=DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;		
	}
	
	public static void close(Connection conn,PreparedStatement pstmt) {
		try {
			if(pstmt !=null)pstmt.close();
			if(conn !=null)conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
}