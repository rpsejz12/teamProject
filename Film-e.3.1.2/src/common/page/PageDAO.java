package common.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.common.JNDI;
import model.movie.MovieVO;

public class PageDAO {


	//전체 리스트의 총 개수를 가져오는 sql
	String selectAllR = "select count(*) from review where mpk = ?";			//리뷰 전체 리스트
	String selectAll = "select count(*) from movie";							//영화 전체 리스트
	String selectAllT = "select count(*) from movie where mtype = ?";			//영화 장르 선택 리스트
	String selectAllSearch = "select count(*) from movie where title like ?";	//영화 검색시 전체 리스트
	String selectAllSearchT = "select count(*) from movie where mtype = ? and title like ?";	//영화 장르 선택&검색 리스트 

	PageVO data = null;


	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;

	public PageVO paging(PageVO vo, MovieVO mvo, String mtype, String search, String table) {		
		System.out.println("pageDAO vo 실행전:" + vo);	
		conn = JNDI.connect();
		try {	//총 게시물 수 count

			if(table.equals("movie")) {
				if(mtype == null || mtype.equals("")) {				

					if(search == null || search.equals("")) {		//type 이 없고 검색이 없을시 전체 리스트의 개수
						pstmt=conn.prepareStatement(selectAll);
						System.out.println("pageDAO 전체 리스트 카운트");
					}
					else {											//type 이 없고 검색이 있을시 검색한 리스트의 개수
						pstmt=conn.prepareStatement(selectAllSearch);
						pstmt.setString(1, "%"+search+"%" );
						System.out.println("pageDAO 전체 리스트 검색 카운트");
					}
				}

				else{												
					if(search == null || search.equals("")) {		//type이 있을때 type 장르 리스트의 개수
						pstmt=conn.prepareStatement(selectAllT);
						pstmt.setString(1, mtype);
						System.out.println("pageDAO "+mtype+" 리스트 카운트");
					}
					else {											//type이 있고, 검색할시 type 장르중 검색한 리스트의 개수
						pstmt=conn.prepareStatement(selectAllSearchT);
						pstmt.setString(1, mtype);
						pstmt.setString(2, "%"+search+"%" );
						System.out.println("pageDAO "+mtype+" 리스트 검색 카운트");
					}
				}
			}
			
			else if(table.equals("review")){						//리뷰일때 movie의 리뷰 리스트 개수
				pstmt=conn.prepareStatement(selectAllR);
				pstmt.setString(1, mvo.getMpk());
				System.out.println("pageDAO 리뷰 전체 리스트 카운트");
				
			}



			rs = pstmt.executeQuery();

			if(rs.next()) {
				vo.setTotal(rs.getInt(1));		//sql로 받아온 총 개수
			}		
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}

		
		/*
	 	startPage

	 	(curPage - 1) / perPageSet * perPageSet + 1
	 	->curPage-1이 없을시 경계값에서 오류가 생김 ex) perPageSet이 5일때 5페이지를 클릭시 6~10페이지를 보여줌
	 	->나누기를 하고 다시 곱한 이유는 나머지값을 버리기 위해
	 	

	  	(1-1)/5*5 + 1 = 1
	 	(2-1)/5*5 + 1 = 1
	 	(3-1)/5*5 + 1 = 1
	 	(4-1)/5*5 + 1 = 1
	 	(5-1)/5*5 + 1 = 1

	 	(6-1)/5*5 + 1 = 2
	 	(7-1)/5*5 + 1 = 2
	 	(8-1)/5*5 + 1 = 2
	 	(9-1)/5*5 + 1 = 2
	 	(10-1)/5*5 + 1 = 2


		endPage = startPage + 5-1
		->c:forEach의 begin end를 사용할시 start 6 end 10 = 6 7 8 9 10


		===============================
		게시물
		perPage가 5일때,	1 페이지 이면	1~5를 보여줘야됨
		(curPage-1)*perPage+1
		start
		(1-1)*5 + 1 = 1
		(2-1)*5 + 1 = 6
		(3-1)*5 + 1 = 11
		
		end = start + 5
		==============================

		라스트 페이지
		카운트로 가져온 값을 perPage으로 나눔

		(count-1) / perPage + 1

		(50-1) / 5 +1 = 10

		(51-1) / 5 +1= 10
		(52-1) / 5 +1= 10
		(53-1) / 5 +1= 10
		(54-1) / 5 +1= 10
		(55-1) / 5 +1= 10

		(56-1) / 5 +1= 11
		===============================
		 */




		vo.setLastPage((vo.getTotal()-1)/vo.getPerPage()+1);	//마지막 페이지 설정	
		vo.setStart((vo.getCurPage()-1)*vo.getPerPage());		//페이지에 보여줄 게시물 시작
		vo.setEnd(vo.getStart()+vo.getPerPage());				//페이지에 보여줄 게시물 끝		



		vo.setStartPage((vo.getCurPage()-1)/vo.getPerPageSet()*vo.getPerPageSet()+1);	//목차 시작
		if(vo.getStartPage() < 1) {		//시작페이지가 1보다 작을경우 1로 설정
			vo.setStartPage(1);
		}

		vo.setEndPage(vo.getStartPage()+vo.getPerPageSet()-1);							//목차 끝
		if(vo.getEndPage() > vo.getLastPage()) {	//끝페이지가 마지막페이지보다 클경우 마지막페이지로 설정
			vo.setEndPage(vo.getLastPage());
		}

		System.out.println("pageDAO 설정후 vo :" + vo);

		return vo;

	}






}
