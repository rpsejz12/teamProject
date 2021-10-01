package model.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.page.PageVO;
import model.common.JNDI;


public class MovieDAO {

	// 장르를 여러개로 할 경우, 테이블이 하나 더 필요(배열의 역할)


	String rselectAll = "SELECT * FROM MOVIE WHERE MPK = ? ORDER BY RDATE DESC";				//리뷰 리스트
	String rdelete = "DELETE FROM REVIEW WHERE MPK = ?";					//리뷰 삭제

	String selectAll = "SELECT * FROM(SELECT A.*, ROWNUM AS RNUM FROM(SELECT * FROM MOVIE ORDER BY TITLE ASC) A WHERE ROWNUM < ?) WHERE RNUM >=?";			//영화 전체 리스트
	String selectAllT = "SELECT * FROM(SELECT A.*, ROWNUM AS RNUM FROM(SELECT * FROM MOVIE WHERE MTYPE = ? ORDER BY TITLE ASC) A WHERE ROWNUM < ?) WHERE RNUM >=?";
	String selectAllSearch = "SELECT * FROM(SELECT A.*, ROWNUM AS RNUM FROM(SELECT * FROM MOVIE WHERE TITLE LIKE ? ORDER BY TITLE ASC) A WHERE ROWNUM < ?) WHERE RNUM >=?";
	String selectAllSearchT = "SELECT * FROM(SELECT A.*, ROWNUM AS RNUM FROM(SELECT * FROM MOVIE WHERE MTYPE = ? AND TITLE LIKE ? ORDER BY TITLE ASC) A WHERE ROWNUM < ?) WHERE RNUM >=?";
	String mpk = "SELECT MPK FROM MOVIE";									//영화 전체 리스트
	String selectOne = "SELECT * FROM MOVIE WHERE MPK = ?";					//영화 클릭
	String insert = "INSERT INTO MOVIE VALUES (?,?,?,?,?,?)";				//영화 등록
	String delete = "DELETE FROM MOVIE WHERE MPK = ?";						//영화 삭제
	String update = "UPDATE MOVIE SET TITLE  = ?, CONTENT = ?, MTYPE = ?, MDATE ?,  WHERE MPK = ?";				//영화 수정

	MovieVO data = null;
	ArrayList<MovieVO> datas = null;
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;

	boolean flag = false;

	public ArrayList<MovieVO> m_selectDB_all(String mtype, String search, PageVO vo){			//영화 리스트
		datas = new ArrayList<MovieVO>();
		conn = JNDI.connect();
		try {
			
			if(mtype == null || mtype.equals("")) {				
				
				if(search == null || search.equals("")) {		//type 이 없고 검색이 없을시 전체 리스트 출력
					pstmt=conn.prepareStatement(selectAll);
					pstmt.setInt(1, vo.getEnd());
					pstmt.setInt(2, vo.getStart());
					System.out.println("전체 리스트");
				}
				else {											//type 이 없고 검색이 있을시 전체 리스트 에서 검색하여 출력
					pstmt=conn.prepareStatement(selectAllSearch);
					pstmt.setString(1, "%"+search+"%" );
					pstmt.setInt(2, vo.getEnd());
					pstmt.setInt(3, vo.getStart());
					System.out.println("전체 리스트 검색");
				}
			}
			
			else{												
				if(search == null || search.equals("")) {		//type이 있을때는 type 장르 리스트만 출력
					pstmt=conn.prepareStatement(selectAllT);
					pstmt.setString(1, mtype);
					pstmt.setInt(2, vo.getEnd());
					pstmt.setInt(3, vo.getStart());
					System.out.println(mtype+" 리스트");
				}
				else {											//type이 있고, 검색할시 type 장르 리스트만 검색하여 출력
					pstmt=conn.prepareStatement(selectAllT);
					pstmt.setString(1, mtype);
					pstmt.setString(2, "%"+search+"%" );
					pstmt.setInt(3, vo.getEnd());
					pstmt.setInt(4, vo.getStart());
					System.out.println(mtype+" 리스트 검색");
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
				data.setFilename(rs.getString("filename"));
				datas.add(data);
			}
			rs.close();
			System.out.println("MovieDAO 영화 리스트 :" + datas);
		}
		catch (SQLException e) {
			System.out.println("MovieDAO 영화 전체 리스트 오류");
			e.printStackTrace();
		}
		finally {
			JNDI.disconnect(pstmt, conn);
		}
		return datas;	
	}

	public MovieVO m_selectDB_one(MovieVO vo){			//영화 클릭
		System.out.println("MovieDAO 영화 클릭 VO :" + vo);
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
				data.setFilename(rs.getString("filename"));
			}
			rs.close();
			System.out.println("MovieDAO 영화 클릭 :" + data);
		}
		catch (SQLException e) {
			System.out.println("MovieDAO 영화 클릭 오류");
			e.printStackTrace();
		}
		finally {
			JNDI.disconnect(pstmt, conn);
		}
		return data;	
	}

	
	public Boolean m_insertDB(MovieVO vo) { 			//영화 등록
		System.out.println("MovieDAO 영화 등록 VO :" + vo);
		conn = JNDI.connect();
		try {
			
			
			
			pstmt = conn.prepareStatement(mpk);
			rs = pstmt.executeQuery();
			
			String mpkStr = null;	//mpk
			int mpkInt = 0;			
			int cnt = 0;
			
			
			while(rs.next()) {
				mpkStr = rs.getString("mpk").substring(1);		//mpk type 제거후 뒷부분만 가져옴
				mpkInt = Integer.parseInt(mpkStr);
				mpkInt++;
				cnt++;
			}
			
			if(cnt == 0) {				//rs 가 null 일경우
				mpkInt = 1001;
			}
			
			
			mpkStr = ""+vo.getMtype().charAt(0) + mpkInt;
	
			
			System.out.println("mpk :" + mpkStr);
		
			pstmt=conn.prepareStatement(insert);
			
			pstmt.setString(1, mpkStr);
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getMtype());
			pstmt.setString(5, vo.getMdate());
			pstmt.setString(6, vo.getFilename());
			if(pstmt.executeUpdate() != 0) {			//등록 성공
				System.out.println("MovieDAO 영화 등록 성공");
				flag = true;
			}
		}
		catch (SQLException e) {
			System.out.println("MovieDAO 영화 등록 오류");
			e.printStackTrace();
		}
		finally {
			JNDI.disconnect(pstmt, conn);
		}
		return flag;							//등록 실패

	}

	public Boolean m_updateDB(MovieVO vo) { 			//영화 수정
		System.out.println("MovieDAO 영화 수정 VO :" + vo);
		conn = JNDI.connect();
		try {
			pstmt=conn.prepareStatement(update);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getMtype());
			pstmt.setString(4, vo.getMdate());
			pstmt.setString(5, vo.getFilename());

			if(pstmt.executeUpdate() != 0) {			//수정 성공
				System.out.println("MovieDAO 영화 수정 성공");
				flag = true;
			}
		}
		catch (SQLException e) {
			System.out.println("MovieDAO 영화 수정 오류");
			e.printStackTrace();
		}
		finally {
			JNDI.disconnect(pstmt, conn);
		}
		return flag;								//수정 실패

	}

	public Boolean m_deleteDB(MovieVO vo) throws SQLException { 				//영화 삭제
		System.out.println("MovieDAO 영화 삭제 VO :" + vo);
		conn = JNDI.connect();
		try {
			conn.setAutoCommit(false); 				//오토커밋 해제

			pstmt=conn.prepareStatement(delete);	//영화 삭제
			pstmt.setString(1, vo.getMpk());
			if(pstmt.executeUpdate() != 0) {
				System.out.println("MovieDAO movie delete 성공");
			}


			pstmt=conn.prepareStatement(rdelete);	//리뷰 삭제
			pstmt.setString(1, vo.getMpk());

			if(pstmt.executeUpdate() != 0) {
				System.out.println("MovieDAO movie delete,rdelete 성공");
			}



			conn.commit();				//커밋
			flag = true;
			System.out.println("MovieDAO movie delete 커밋 성공");

		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MovieDAO delete 트랜잭션 오류");
			conn.rollback();		//롤백
		}finally {
			conn.setAutoCommit(true);
			JNDI.disconnect(pstmt, conn);				
		}
		return flag;

	}

}
