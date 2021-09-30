package common.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.common.JNDI;

public class PageDAO {
	
	
	//total 을 sql count로 구해야됨
	//일단 jsp에 보내줘야 하는 정보 == sql count로 총 게시물수를 구해서 총 페이지수를 구해야됨
	//그리고 페이지수가 ?개 이상이 되면 < > 를 이용하여 ?개 단위로 페이 한번에 이동 가능해야됨
	//처음으로, 맨끝으로 가 있으면 좋을듯?
	//메인 페이지에서 get 방식을 통해 우리에게 현재 페이지를 줄거다
	//인벤 페이지에서 확인해보니 현재 페이지 정보만 받아옴
	
	
	String count = "SELECT COUNT(*) FROM TEST";		// 총 게시물 수
	
	PageVO data = null;
	
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	/*
	int curPage;	//현재 페이지 컨트롤러에서 받아옴
	int perPage;	//현재 페이지 컨트롤러에서 받아옴 (페이지당 들어갈 게시물 수)
	int perPageSet; //현재 페이지 컨트롤러에서 받아옴 (페이징 몇페이지씩 인지)
	
	int total;		//count sql 사용하여 set
	int startPage;	//curPage를 이용하여 계산후 set
	int endPage;	//curPage를 이용하여 계산후 set
	int lastPage;	//count sql 사용하여 set
	int start;		//curPage를 이용하여 계산후 set
	int end;		//curPage를 이용하여 계산후 set
*/	
	
	
	public PageVO paging(PageVO vo) {		
		System.out.println("pageDAO :" + vo);	
		conn = JNDI.connect();
		try {	//총 게시물 수 count
			pstmt=conn.prepareStatement(count);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setTotal(rs.getInt(1));		//총게시물수 vo에 set
			}		
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		
		System.out.println("pageDAO count :" + vo.getTotal());
		

	/*	<이전> 1 2 3 4 5 <다음>	
	 	
	 	startPage
	 	
	 	(curPage - 1) / perPageSet * perPageSet + 1
	 	
	 	
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
	 	
	 	(11-1)/5*5 + 1 = 3
	 	
	 	
		endPage = startPage + 5-1
		foreach begin end를 사용할시 start 6 end 10 = 6 7 8 9 10
		
		
		===============================
		게시물
		1 페이지 이면
		1~5를 보여줘야됨
		
		1 일때 start = 1, end = 6
		2 일때 start = 6, end = 11
		
		
		
		
		start = (1-1)*5 + 1
		end = start + 5
		
		start = (2-1)*5 + 1 
		
		==============================
		
		라스트 페이지
		카운트로 가져온 값을 perPageSet으로 나눔
		
		(count) / perPageSet + 1
		
		50 -1 / 8
		
		46
		47
		48
		49 / 5 = 9
		(50-1) / 5 +1 = 10
		
		49 / 5 
		(50-1) / 5 +1=10
		50/5 +1 =11
		
		(51-1) / 5 +1= 10
		(52-1) / 5 +1= 10
		(53-1) / 5 +1= 10
		(54-1) / 5 +1= 10
		(55-1) / 5 +1= 10
		
		(56-1) / 5 = 11
		
		
		===============================
		 
		
	*/
		
		
		
	
		vo.setLastPage((vo.getTotal()-1)/vo.getPerPage()+1);	//마지막 페이지 set	
		vo.setStart((vo.getCurPage()-1)*vo.getPerPage()+1);		//페이지에 보여줄 게시물 시작
		vo.setEnd(vo.getStart()+vo.getPerPage());				//페이지에 보여줄 게시물 끝	sql에 들어갈 것들		
		
		
	
		vo.setStartPage((vo.getCurPage()-1)/vo.getPerPageSet()*vo.getPerPageSet()+1);	// < ' 1 ' <-이놈 2 3 4 5 >
		if(vo.getStartPage() < 1) {	
			vo.setStartPage(1);
		}
		
		vo.setEndPage(vo.getStartPage()+vo.getPerPageSet()-1);				
		if(vo.getEndPage() > vo.getLastPage()) {
			vo.setEndPage(vo.getLastPage());
		}
		
		System.out.println("pageDAO af :" + vo);
		
	
		
		
		return vo;
		
	}
	
	

	
	

}
