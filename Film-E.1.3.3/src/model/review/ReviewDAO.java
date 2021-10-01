package model.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.page.PageVO;
import model.common.JNDI;
import model.movie.MovieVO;

public class ReviewDAO {
	

	String rSelectAll = "select * from(select a.*, rownum as rnum from(select * from review where mpk =? order by rpk desc) a where rownum < ?) where rnum >=?"; // 선택한 영화에 해당되는 최근 리뷰부터 조회	
	String insert = "insert into review (rpk, cmt, id, mpk, date) values(nvl((select max(rpk) from review),0)+1, ?, ?, ?, sysdate)"; // 리뷰 등록
	String delete= "delete from review where rpk = ?"; // 리뷰 삭제

	Connection conn=null;
	PreparedStatement pstmt=null;
	
	public ArrayList<ReviewVO> r_selectDB_all(MovieVO vo,PageVO pvo)	{ // 리뷰 조회
		
		conn=JNDI.connect();
		ArrayList<ReviewVO> datas= new ArrayList<ReviewVO>();
		
		try {
			
			pstmt = conn.prepareStatement(rSelectAll);
			pstmt.setString(1, vo.getMpk());
			pstmt.setInt(2, pvo.getEnd());
			pstmt.setInt(3, pvo.getStart());
			ResultSet rs= pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				ReviewVO data = new ReviewVO();
				data.setRpk(rs.getInt("rpk"));
				data.setCmt(rs.getString("cmt"));
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
	
	public boolean r_insertDB(ReviewVO vo) { // 리뷰 등록
		
		conn =JNDI.connect();
		boolean result =false;
		
		// String sql =" insert into review (rpk, comment, id, mpk, date) values(nvl((select max(rpk) from review),0)+1, ?, ?, ?, sysdate)";
	
		try {
			pstmt=conn.prepareStatement(insert);
			pstmt.setString(1, vo.getCmt());
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
	
	public boolean r_deleteDB(ReviewVO vo) { // 리뷰 삭제
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
