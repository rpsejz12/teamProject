package model.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JNDI;

public class ReviewDAO {
	
	String rSelectAll = "select * from review order by rpk desc"; //√÷±Ÿ ∏Æ∫‰∫Œ≈Õ ¡∂»∏
	String insert = "insert into review (rpk, comment, id, mpk, date) values(nvl((select max(rpk) from review),0)+1, ?, ?, ?, sysdate)"; // ∏Æ∫‰ µÓ∑œ
	String delete= "delete from review where rpk = ?"; // ∏Æ∫‰ ªË¡¶

	Connection conn=null;
	PreparedStatement pstmt=null;
	
	public ArrayList<ReviewVO> r_selectDB_all()	{ // ∏Æ∫‰ ¡∂»∏
		
		conn=JNDI.connect();
		ArrayList<ReviewVO> datas= new ArrayList<ReviewVO>();
		
		try {
			// String sql = "select * from review order by rpk desc"; //√÷±Ÿ ∏Æ∫‰∫Œ≈Õ ¡∂»∏
			
			pstmt = conn.prepareStatement(rSelectAll);
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()) {
				ReviewVO data = new ReviewVO();
				data.setRpk(rs.getInt("rpk"));
				data.setComment(rs.getString("comment"));
				data.setId(rs.getString("id"));
				data.setMpk(rs.getString("mpk"));
				data.setDate(rs.getDate("date"));
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		
		return datas;
	}
	
	public boolean r_insertDB(ReviewVO vo) { // ∏Æ∫‰ µÓ∑œ
		
		conn =JNDI.connect();
		boolean result =false;
		
		// String sql =" insert into review (rpk, comment, id, mpk, date) values(nvl((select max(rpk) from review),0)+1, ?, ?, ?, sysdate)";
	
		try {
			pstmt=conn.prepareStatement(insert);
			pstmt.setString(1, vo.getComment());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getMpk());
			pstmt.executeUpdate();
			
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return result;
	}
	
	public boolean r_deleteDB(ReviewVO vo) { // ∏Æ∫‰ ªË¡¶
		conn =JNDI.connect();
		boolean result =false;
		
		// String sql="delete from review where rpk = ?";
		
		try {
			pstmt=conn.prepareStatement(delete);
			pstmt.setInt(1, vo.getRpk());
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
