package model.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import common.page.PageVO;
import model.common.JNDI;


public class MovieDAO {
	String rdelete = "delete from review where mpk = ?";										//리뷰 삭제 트랜잭션

	String count = "select count(*) from movie";
	String selectRand = "select * from movie";

	String selectAllm = "select * from movie order by ratingavg desc limit ?, ?";			//영화 전체 리스트
	String selectAll = "select * from movie order by title asc limit ?, ?";			//영화 전체 리스트
	//SELECT * FROM MOVIE WHERE MTYPE = ? ORDER BY TITLE ASC LIMIT ?, ?
	String selectAllT = "select * from movie where mtype = ? order by title asc limit ?, ?";
	String selectAllSearch = "select * from movie where title like ? order by title asc limit ?, ?";
	String selectAllSearchT = "select * from movie where mtype = ? and title like ? order by title asc limit ?, ?";

	//select 설명
	//order by와 rownum이 순서가 필요하여 () 사용
	//rownum은 1부터 시작 하지 않으면 값을 받지 못함
	//그러므로 rownum > ? 이런식으로는 0이 아니면 값이 들어가지 못함
	//그래서 rownum < ? 받아온후 rnum에 저장후 rnum> ? 의 순서를 사용
	//(select * from movie order by title asc) A -> movie 테이블을 이름순으로 정렬한 리스트를 A로 임시 저장
	//(select A.* , rownum as rnum from () A where rownum < ?) -> rownum을 rnum에 임시 저장 A, rownum < ? 인 리스트 저장
	//select * from () where rnum >= ? 인 리스트 저장

	String mpk = "select mpk from movie";									//영화 전체 리스트
	String selectOne = "select * from movie where mpk = ?";					//영화 클릭
	String insert = "insert into movie(mpk, title, content, mtype,mdate,filename) values (?,?,?,?,date_format(?,'%Y-%m-%d'),?)";				//영화 등록
	String delete = "delete from movie where mpk = ?";						//영화 삭제
	String update = "update movie set title  = ?, content = ?, mtype = ?, mdate = date_format(?,'%Y-%m-%d'), filename = ?  where mpk = ?";				//영화 수정

	String isHttp = null;		//http가 아닐경우 img/를 붙일 String
	Boolean isMpk = false;		//장르가 mpkSet에 저장되었는지 확인

	HashMap<String, String> mpkMap = new HashMap<String, String>(){{
		   put("액션", "AC");
		   put("애니메이션", "AN");
		   put("멜로/로맨스", "RO");
		   put("드라마", "DR");
		   put("다큐멘터리", "DC");
	}};
	
	
	MovieVO data = null;
	ArrayList<MovieVO> datas = null;
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;

	boolean flag = false;

	public ArrayList<MovieVO> m_selectDB_all_m(PageVO vo){
		datas = new ArrayList<MovieVO>();
		conn = JNDI.connect();
		try {
			//메인, 카테고리, 관리자리스트 페이지에서 사용
			//검색과 카테고리선택을 통합하여 구현	

			pstmt=conn.prepareStatement(selectAllm);
			pstmt.setInt(1, vo.getStart());
			pstmt.setInt(2, vo.getPerPage());
			System.out.println("메인 전체 리스트");


			rs=pstmt.executeQuery();
			while(rs.next()) {
				data = new MovieVO();
				data.setMpk(rs.getString("mpk"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setMtype(rs.getString("mtype"));
				data.setMdate(rs.getString("mdate"));
				data.setRatingavg(rs.getInt("ratingavg"));

				isHttp = rs.getString("filename");

				if(!isHttp.substring(0, 4).equals("http")) {		// 처음 4글자가 http가 아니면 앞에 img/추가
					isHttp = "img/"+isHttp;
				}

				data.setFilename(isHttp);
				datas.add(data);
			}
			rs.close();
		}
		catch (SQLException e) {
			System.out.println("MovieDAO 영화 전체 리스트 오류");
			e.printStackTrace();
		}
		finally {
			JNDI.disconnect(pstmt, conn);
		}
		//System.out.println("MovieDAO selectAll datas :" + datas);
		return datas;	
	}

	public ArrayList<MovieVO> m_selectDB_all(String mtype, String search, PageVO vo){			//영화 리스트
		datas = new ArrayList<MovieVO>();
		conn = JNDI.connect();
		try {
			//메인, 카테고리, 관리자리스트 페이지에서 사용
			//검색과 카테고리선택을 통합하여 구현
			if(mtype == null || mtype.equals("")) {				

				if(search == null || search.equals("")) {		//카테고리 x, 검색 x
					pstmt=conn.prepareStatement(selectAll);
					pstmt.setInt(1, vo.getStart());
					pstmt.setInt(2, vo.getPerPage());
					System.out.println("전체 리스트");
				}
				else {											//카테고리 x, 검색 o
					pstmt=conn.prepareStatement(selectAllSearch);
					pstmt.setString(1, "%"+search+"%" );
					pstmt.setInt(2, vo.getStart());
					pstmt.setInt(3, vo.getPerPage());
					System.out.println("전체 리스트 검색");
				}
			}

			else{												
				if(search == null || search.equals("")) {		//카테고리 o, 검색 x
					pstmt=conn.prepareStatement(selectAllT);
					pstmt.setString(1, mtype);
					pstmt.setInt(2, vo.getStart());
					pstmt.setInt(3, vo.getPerPage());
					System.out.println(mtype+" 리스트");
				}
				else {											//카테고리 o, 검색 o
					pstmt=conn.prepareStatement(selectAllSearchT);
					pstmt.setString(1, mtype);
					pstmt.setString(2, "%"+search+"%" );
					pstmt.setInt(3, vo.getStart());
					pstmt.setInt(4, vo.getPerPage());
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
				data.setRatingavg(rs.getInt("ratingavg"));

				isHttp = rs.getString("filename");

				if(!isHttp.substring(0, 4).equals("http")) {		// 처음 4글자가 http가 아니면 앞에 img/추가
					isHttp = "img/"+isHttp;
				}

				data.setFilename(isHttp);
				datas.add(data);
			}
			rs.close();
		}
		catch (SQLException e) {
			System.out.println("MovieDAO 영화 전체 리스트 오류");
			e.printStackTrace();
		}
		finally {
			JNDI.disconnect(pstmt, conn);
		}
		//System.out.println("MovieDAO selectAll datas :" + datas);
		return datas;	
	}

	public MovieVO m_selectDB_rand(){

		Random rand = new Random();
		int temp = 0;
		int cnt = 0;
		conn = JNDI.connect();
		data = new MovieVO();
		try {

			pstmt = conn.prepareStatement(count);
			ResultSet rs1 = pstmt.executeQuery();
			if(rs1.next()) {
				temp = rand.nextInt(rs1.getInt(1));			
			}
			pstmt = conn.prepareStatement(selectRand);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				if(temp == cnt) {
					data = new MovieVO();
					data.setMpk(rs.getString("mpk"));
					data.setTitle(rs.getString("title"));
					data.setContent(rs.getString("content"));
					data.setMtype(rs.getString("mtype"));
					data.setMdate(rs.getString("mdate"));	
					data.setRatingavg(rs.getDouble("ratingavg"));

					isHttp = rs.getString("filename");

					if(!isHttp.substring(0, 4).equals("http")) {		// 처음 4글자가 http가 아니면 앞에 img/추가
						isHttp = "img/"+isHttp;
					}

					data.setFilename(isHttp);

				}
				cnt++;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JNDI.disconnect(pstmt, conn);
		}


		return data;

	}


	public MovieVO m_selectDB_one(MovieVO vo){			//영화 클릭
		//System.out.println("MovieDAO 영화 클릭 VO :" + vo);
		data = new MovieVO();
		conn = JNDI.connect();
		try {
			pstmt=conn.prepareStatement(selectOne);
			pstmt.setString(1, vo.getMpk());
			rs=pstmt.executeQuery();
			while(rs.next()) {

				data.setMpk(rs.getString("mpk"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setMtype(rs.getString("mtype"));
				data.setMdate(rs.getString("mdate"));
				data.setRatingavg(rs.getDouble("ratingavg"));

				isHttp = rs.getString("filename");

				if(!isHttp.substring(0, 4).equals("http")) {		// 처음 4글자가 http가 아니면 앞에 img/추가
					isHttp = "img/"+isHttp;
				}

				data.setFilename(isHttp);
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
		System.out.println("MovieDAO selectOne data :" + data);
		return data;	
	}


	public Boolean m_insertDB(MovieVO vo) {          //영화 등록
		System.out.println("MovieDAO 영화 등록 VO :" + vo);
		conn = JNDI.connect();
		try {



			pstmt = conn.prepareStatement(mpk);
			rs = pstmt.executeQuery();

			String mpkStr = null;      
			String mpkType = null;  //  'AC'1001  mpk 앞에 들어갈 글자 

			int mpkInt = 0;         //   mpk를 증가시키기위해 int로 변환하고 저장할 변수
			int max = 0;
			boolean isNew = true;   //   증가시킬 mpk가 있는지 확인하는 변수

			while(rs.next()) {
				mpkStr = rs.getString("mpk").substring(2);      //mpk type 제거후 뒷부분만 가져옴
				mpkInt = Integer.parseInt(mpkStr);            //인트로 변환
				if(mpkInt > max){
					max = mpkInt;
				}
				isNew = false; 
			}

			if(isNew) {            		//rs 가 null 일경우
				mpkInt = 1001;         //mpk를 1001로 저장
			}

			if(mpkMap.containsKey(vo.getMtype())) {
				mpkType = mpkMap.get(vo.getMtype());
			}else {
				mpkType = "EX";
				vo.setMtype("ETC");
			}
			max++;         
			mpkStr = mpkType + max;                     // AC + 1001
			vo.setMpk(mpkStr);                           // mpkStr을 mpk로 set         

			pstmt=conn.prepareStatement(insert);

			pstmt.setString(1, vo.getMpk());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getMtype());
			pstmt.setString(5, vo.getMdate());
			pstmt.setString(6, vo.getFilename());
			System.out.println("MovieDAO insert vo :" + vo);
			if(pstmt.executeUpdate() != 0) {         //등록 성공
				System.out.println("MovieDAO insert 성공");
				flag = true;
			}
		}
		catch (SQLException e) {
			System.out.println("MovieDAO insert 오류");
			e.printStackTrace();
		}
		finally {
			JNDI.disconnect(pstmt, conn);
		}
		return flag;                     //등록 실패

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
			pstmt.setString(6, vo.getMpk());

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
			conn.rollback();		//sql오류 발생시 롤백
		}finally {
			conn.setAutoCommit(true);
			JNDI.disconnect(pstmt, conn);				
		}
		return flag;

	}

}
