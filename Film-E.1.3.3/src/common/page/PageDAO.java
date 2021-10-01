package common.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.common.JNDI;

public class PageDAO {


	//total �� sql count�� ���ؾߵ�
	//�ϴ� jsp�� ������� �ϴ� ���� == sql count�� �� �Խù����� ���ؼ� �� ���������� ���ؾߵ�
	//�׸��� ���������� ?�� �̻��� �Ǹ� < > �� �̿��Ͽ� ?�� ������ ���� �ѹ��� �̵� �����ؾߵ�
	//ó������, �ǳ����� �� ������ ������?
	//���� ���������� get ����� ���� �츮���� ���� �������� �ٰŴ�
	//�κ� ���������� Ȯ���غ��� ���� ������ ������ �޾ƿ�


	//��ü�� 60���� �� ��Ʈ������ 12
	//

	String selectAll = "SELECT COUNT(*) FROM MOVIE";			//��ȭ ��ü ����Ʈ ī��Ʈ
	String selectAllR = "SELECT COUNT(*) FROM REVIEW";			//���� ��ü ����Ʈ ī��Ʈ
	String selectAllT = "SELECT COUNT(*) FROM MOVIE WHERE MTYPE = ?";	//��ȭ �帣 ���� ī��Ʈ
	String selectAllSearch = "SELECT COUNT(*) FROM MOVIE WHERE TITLE LIKE ?";	//��ȭ �˻��� ��ü����Ʈ ī��Ʈ
	String selectAllSearchT = "SELECT COUNT(*) FROM MOVIE WHERE MTYPE = ? AND TITLE LIKE ?";	//��ȭ �帣 �����ϰ� �˻��� ����Ʈ ī��Ʈ

	PageVO data = null;


	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;

	/*
	int curPage;	//���� ������ ��Ʈ�ѷ����� �޾ƿ�
	int perPage;	//���� ������ ��Ʈ�ѷ����� �޾ƿ� (�������� �� �Խù� ��)
	int perPageSet; //���� ������ ��Ʈ�ѷ����� �޾ƿ� (����¡ ���������� ����)

	int total;		//count sql ����Ͽ� set
	int startPage;	//curPage�� �̿��Ͽ� ����� set
	int endPage;	//curPage�� �̿��Ͽ� ����� set
	int lastPage;	//count sql ����Ͽ� set
	int start;		//curPage�� �̿��Ͽ� ����� set
	int end;		//curPage�� �̿��Ͽ� ����� set
	 */	

	public PageVO paging(PageVO vo, String mtype, String search, String table) {		
		System.out.println("pageDAO :" + vo);	
		conn = JNDI.connect();
		try {	//�� �Խù� �� count

			if(table.equals("movie")) {
				if(mtype == null || mtype.equals("")) {				

					if(search == null || search.equals("")) {		//type �� ���� �˻��� ������ ��ü ����Ʈ ���
						pstmt=conn.prepareStatement(selectAll);
						System.out.println("��ü ����Ʈ ī��Ʈ");
					}
					else {											//type �� ���� �˻��� ������ ��ü ����Ʈ ���� �˻��Ͽ� ���
						pstmt=conn.prepareStatement(selectAllSearch);
						pstmt.setString(1, "%"+search+"%" );
						System.out.println("��ü ����Ʈ �˻� ī��Ʈ");
					}
				}

				else{												
					if(search == null || search.equals("")) {		//type�� �������� type �帣 ����Ʈ�� ���
						pstmt=conn.prepareStatement(selectAllT);
						pstmt.setString(1, mtype);
						System.out.println(mtype+" ����Ʈ ī��Ʈ");
					}
					else {											//type�� �ְ�, �˻��ҽ� type �帣 ����Ʈ�� �˻��Ͽ� ���
						pstmt=conn.prepareStatement(selectAllT);
						pstmt.setString(1, mtype);
						pstmt.setString(2, "%"+search+"%" );
						System.out.println(mtype+" ����Ʈ �˻� ī��Ʈ");
					}
				}
			}
			
			else if(table.equals("review")){
				pstmt=conn.prepareStatement(selectAllR);
				System.out.println("���� ��ü ����Ʈ ī��Ʈ");
			}



			rs = pstmt.executeQuery();

			if(rs.next()) {
				vo.setTotal(rs.getInt(1));		//�ѰԽù��� vo�� set
			}		
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}

		System.out.println("pageDAO count :" + vo.getTotal());


		/*	<����> 1 2 3 4 5 <����>	

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
		foreach begin end�� ����ҽ� start 6 end 10 = 6 7 8 9 10


		===============================
		�Խù�
		1 ������ �̸�
		1~5�� ������ߵ�

		1 �϶� start = 1, end = 6
		2 �϶� start = 6, end = 11




		start = (1-1)*5 + 1
		end = start + 5

		start = (2-1)*5 + 1 

		==============================

		��Ʈ ������
		ī��Ʈ�� ������ ���� perPageSet���� ����

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




		vo.setLastPage((vo.getTotal()-1)/vo.getPerPage()+1);	//������ ������ set	
		vo.setStart((vo.getCurPage()-1)*vo.getPerPage()+1);		//�������� ������ �Խù� ����
		vo.setEnd(vo.getStart()+vo.getPerPage());				//�������� ������ �Խù� ��	sql�� �� �͵�		



		vo.setStartPage((vo.getCurPage()-1)/vo.getPerPageSet()*vo.getPerPageSet()+1);	// < ' 1 ' <-�̳� 2 3 4 5 >
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
