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
	

	String rSelectAll = "select rpk, cmt, id, mpk, rating, date_format(rdate,'%Y-%m-%d %H:%i') rdate from review where mpk =? order by rdate desc limit ?, ?"; 
//"select rpk, cmt, id, mpk, rating, to_char(rdate,'YYYY-MM-DD HH:MI') rdate from(select a.*, rownum as rnum from(select * from review where mpk =? order by rdate desc) a where rownum < ?) where rnum >=?"; // 선택한 영화에 해당되는 최근 리뷰부터 조회	
	String insert = "insert into review (cmt, id, mpk, rdate, rating) values(?, ?, ?, now(),?)";
//"insert into review (rpk, cmt, id, mpk, rdate, rating) values(nvl((select max(rpk) from review),0)+1, ?, ?, ?, sysdate,?)"; // 리뷰 등록
	String delete= "delete from review where rpk = ?"; // 리뷰 삭제
	String starAVG= "select avg(rating) from review where mpk = ?"; //별점 평균 계산
	String mUpdate= "update movie set ratingavg=? where mpk=?"; // 별점 평균을 해당 영화에 저장
	String rSelectOne = "select rpk, cmt, id, mpk, rating, date_format(rdate,'%Y-%m-%d %H:%i') rdate from review where id = ? and mpk = ?"; // 회원가입 시 아이디 중복확인, 회원정보 수정시 id로 조회

	Connection conn=null;
	PreparedStatement pstmt=null;
	
	double ratingavg=0; // 별점 평균 초기화
	
	public ArrayList<ReviewVO> r_selectDB_all(MovieVO vo,PageVO pvo)	{ // 리뷰 조회
		System.out.println("reviewDAO");
		conn=JNDI.connect();
		ArrayList<ReviewVO> datas= new ArrayList<ReviewVO>();
		
		try {
			
			pstmt = conn.prepareStatement(rSelectAll);
			pstmt.setString(1, vo.getMpk());
			pstmt.setInt(2, pvo.getStart());
			pstmt.setInt(3, pvo.getPerPage());
			ResultSet rs= pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewVO data = new ReviewVO();
				data.setRpk(rs.getInt("rpk"));
				data.setCmt(rs.getString("cmt"));
				data.setId(rs.getString("id"));
				data.setMpk(rs.getString("mpk"));
				data.setRdate(rs.getString("rdate"));
				data.setRating(rs.getInt("rating"));
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		System.out.println("ReviewDAO selectAll rVO" + datas);
		return datas;
	}
	
	public boolean r_insertDB(ReviewVO vo) throws SQLException{ // 리뷰 등록
		
		conn =JNDI.connect();
		boolean result =false;
		System.out.println("ReviewVO vo"+vo);
		
		
		try {
			conn.setAutoCommit(false);
			
			pstmt=conn.prepareStatement(insert); // 리뷰등록
			pstmt.setString(1, vo.getCmt());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getMpk());
			pstmt.setDouble(4, vo.getRating()); // 별점 추가
			pstmt.executeUpdate();
			
			pstmt=conn.prepareStatement(starAVG); // 별점 평균 
			pstmt.setString(1, vo.getMpk());
			
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				ratingavg=rs.getDouble(1); 
			}
			rs.close();
			ratingavg = Math.round(ratingavg*10)/10.0;
			pstmt=conn.prepareStatement(mUpdate); // 별점 평균을 해당 영화에 저장
			pstmt.setDouble(1, ratingavg);
			pstmt.setString(2, vo.getMpk());
			pstmt.executeUpdate();
			
			
			conn.commit();
			conn.setAutoCommit(true);
			
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ReviewDAO insert 트랜잭션 오류");
			conn.rollback();
			conn.setAutoCommit(true);
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return result;
	}
	
	public boolean r_deleteDB(ReviewVO vo) throws SQLException{ // 리뷰 삭제
		conn =JNDI.connect();
		boolean result =false;
	
		try {
			conn.setAutoCommit(false);
			
			pstmt=conn.prepareStatement(delete); // 리뷰삭제 
			pstmt.setInt(1, vo.getRpk());
			pstmt.executeUpdate();
			
			pstmt=conn.prepareStatement(starAVG); // 별점 평균 
			pstmt.setString(1, vo.getMpk());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				ratingavg=rs.getDouble(1);
			}
			rs.close();
			ratingavg = Math.round(ratingavg*10)/10.0;
			
			
			pstmt=conn.prepareStatement(mUpdate); // 별점 평균을 해당 영화에 저장
			pstmt.setDouble(1, ratingavg);
			pstmt.setString(2, vo.getMpk());
			pstmt.executeUpdate();
			
			conn.commit();
			conn.setAutoCommit(true);
			
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ReviewDAO delete 트랜잭션 오류");
			conn.rollback();
			conn.setAutoCommit(true);
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return result;
	}
	
	public ReviewVO r_selectDB_one(ReviewVO vo) { // 회원가입시 아이디 중복확인

		conn= JNDI.connect();
			ReviewVO data=new ReviewVO();
		try {
			pstmt = conn.prepareStatement(rSelectOne);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getMpk());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				data.setRpk(rs.getInt("rpk"));
				data.setCmt(rs.getString("cmt"));
				data.setId(rs.getString("id"));
				data.setMpk(rs.getString("mpk"));
				data.setRdate(rs.getString("rdate"));
				data.setRating(rs.getDouble("rating"));
			}

			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		return data;
	}
	
}
