
package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.HGairVO;
import model.MemberVO;
import model.TicketVO;

public class view_HGAir{
	Scanner sc=new Scanner(System.in);
	Exception e=new Exception();


	public int start() {
		System.out.println();
		System.out.println("---________________HG_air ->->->>________________---");
		System.out.println();
		System.out.println("      [1]로그인     [2]회원가입     [3]프로그램종료         ");
		return sc.nextInt();  // 인수 입력
	}

	public MemberVO viewLogin(MemberVO memvo) {
		System.out.println("=======================로그인=======================");
		while(true) {
			System.out.print("아이디: ");
			String id=sc.next(); // 회원 아이디 입력
			System.out.print("비밀번호: ");
			String pw=sc.next(); // 회원 비밀번호 입력
			// 아이디 비밀번호 체킹 
			memvo.setId(id);
			memvo.setPw(pw);
			return memvo;
		}
	}

	public void loginSuccess() {
		System.out.println();
		System.out.println("로그인 성공!");
		System.out.println("==================================================");
	}
	public void loginFailure() {
		System.err.println("아이디와 비밀번호를 다시 확인해주세요!");
	}

	public int viewReserveSel() {
		System.out.println();
		System.out.println("[1]예약     [2]예약조회     [3]종료");
		return sc.nextInt();
	}

	public HGairVO viewRerserveAir(ArrayList<HGairVO> al) {
		int air_choice;
		while(true) {
			int i = 0;
			int index=1; // 번호
			System.out.println("==============================HG AIR LINE=============================="); 
			System.out.println("번호\t편명\t출발날짜\t\t\t출발지\t도착지\t가격\t잔여좌석");
			for(HGairVO vo: al) { // 전체 항공편 출력
				// 출발날짜 출발지 도착지 가격
				System.out.println(index+"\t"+vo.getAir_num()+"\t"+vo.getFlight_date()+"\t"+vo.getDeparture()+"\t"+vo.getArrive()+"\t"+vo.getPrice()+"원\t"+vo.getS_cnt()+"석");
				index++;
			}
			System.out.println("=======================================================================");
			System.out.println("\n원하는 항공편을 선택해주십시오.");
			air_choice=sc.nextInt(); // 항공편보고 원하는 항공편(PK?)선택
			if( air_choice>0&& air_choice<12) { //11개의 항공편
				System.out.println("\t\t번호\t편명\t출발날짜\t\t\t출발지\t도착지\t가격\t잔여좌석");
				System.out.print("선택한 항공편은\t");
				System.out.println(air_choice+"\t"+al.get(air_choice-1).getAir_num()+"\t"+al.get(air_choice-1).getFlight_date()+"\t"+al.get(air_choice-1).getDeparture()+"\t"+al.get(air_choice-1).getArrive()+"\t"+al.get(air_choice-1).getPrice()+"원\t"+al.get(air_choice-1).getS_cnt()+"석\t입니다"); // 선택한 항공편 출력
				System.out.println();

				break;
			}
			else {
				System.err.println("\n재입력하세요.\n");
			}


		}
		return al.get(air_choice-1);
	}

	public TicketVO viewReserveNP(HGairVO hgvo) {
		TicketVO tkvo = new TicketVO();
		while(true) {
			if(hgvo.getS_cnt()==0) {
				System.out.println("잔여좌석 없음");
				break;
			}
			System.out.print("\n인원수 입력: ");
			int personal=sc.nextInt(); // 인원선택
			if(personal<1||personal>hgvo.getS_cnt()) { // 유효성 선택인원은 좌석갯수 오버X
				System.err.println("\n재입력하세요.\n");
			}
			else {
				tkvo.setAir_num(hgvo.getAir_num());
				tkvo.setp_cnt(personal);
				tkvo.setPayment(hgvo.getPrice()*personal);
				break;
			}
		}
		return tkvo;
	}

	public int viewReserveNPsel(TicketVO tkvo) {
		int s_choice;
		while(true) {
			System.out.println();
			System.out.println(tkvo.getp_cnt()+"분 맞으십니까?");
			System.out.println("[1]예     [2]아니오");
			s_choice=sc.nextInt(); // 인원수 확인 선택
			if(s_choice==1) { // 탈출->인원선택 종료
				break;
			}
			else if (s_choice==2){ // 현재탈출->인원수입력
				break;
			}
			else { // 유효성검사->현재 반복
				System.err.println("\n재입력하세요.");
			}
		}

		return s_choice;	
	}


	public int viewPointSel(MemberVO vo) {
		int purchase_choice = 0; // 결제선택
		System.out.println("현재 "+vo.getId()+"님이 가지고 계신 마일리지는 "+vo.getM_point()+"입니다.\n");


		System.out.println("마일리지를 사용하시겠습니까?");
		System.out.println("\n[1]예     [2]아니오");
		purchase_choice = sc.nextInt();
		return purchase_choice;
	}

	public TicketVO viewPointUse(MemberVO memvo, TicketVO tkvo) {
		if(memvo.getM_point()>=tkvo.getPayment()) { // 마일리지 >= 총금액 
			System.out.println(memvo.getId()+"님의 남은 결제 금액은 0원 입니다."); // memvo.getName()추가 멤버이름 연결해주세요!
			tkvo.setUse_point(tkvo.getPayment());
			memvo.setM_point(memvo.getM_point()-tkvo.getPayment()); // 마일리지 감소
			tkvo.setPayment(0);

		}
		else { // 마일리지 < 총금액
			System.out.println(memvo.getId()+"님의 남은 결제 금액은 "+(tkvo.getPayment()-memvo.getM_point())+"원 입니다."); // memvo.getName()추가 멤버이름 연결해주세요!
			tkvo.setPayment(tkvo.getPayment()-memvo.getM_point());
			tkvo.setUse_point(memvo.getM_point());
			memvo.setM_point(0);; // 마일리지 감소
		}
		System.out.println("\n남은 마일리지는 "+memvo.getM_point()+" 입니다.");
		System.out.println("\n결제를 진행합니다.");
		return tkvo;
	}

	public int viewPaySel() {
		System.out.println();
		System.out.println("결제하시겠습니까?\n[1]예     [2]아니오"); 
		int purchase_choice=sc.nextInt();
		return purchase_choice;
	}

	public void viewPay(TicketVO tkvo) {

		System.out.print("결제가 진행중입니다.");
		for(int i=0; i<5; i++) {
			System.out.print("."); // 슬립 사용
			try {
				Thread.sleep(1000); // 1초
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println("\n결제가 완료되었습니다.\n");
		System.out.println("=======================예약정보=======================");
		System.out.println("예약번호\t\t편명\tID\t결제금액\t마일리지(사용)");
		System.out.println(tkvo.getT_num()+"\t\t"+tkvo.getAir_num()+"\t"+tkvo.getId()+"\t"+tkvo.getPayment()+"\t"+tkvo.getUse_point());
		System.out.println("====================================================");
	}

	public void viewPaySelErr() {
		System.err.println("\n재입력하세요.");
	}


	public void viewNoPay() {
		System.out.println("\n결제를 종료합니다.");

	}

	public int viewReserveCheck(ArrayList<TicketVO> tkAl) {
		System.out.println("=======================예약정보=======================");
		System.out.println("예약번호\t\t편명\tID\t결제금액\t마일리지(사용)"); // ticket_info 테이블
		for(TicketVO tkvo:tkAl) {
			System.out.println(tkvo.getT_num()+"\t"+tkvo.getAir_num()+"\t"+tkvo.getId()+"\t"+tkvo.getPayment()+"\t"+tkvo.getUse_point());
		}
		System.out.println("====================================================");
		// 정보 출력 뒤 ... -> 예약변경 or 취소 or 종료 
		// +3번창
		System.out.println("[1]예약변경     [2]예약취소     [3]종료");
		int changeOrCancle=sc.nextInt();
		return changeOrCancle;
	}

	public void NoRes() {
		System.out.println();
		System.out.println("예약정보가 없습니다.");
	}
	
	public TicketVO viewReserveChangeSel(ArrayList<TicketVO> tkAl) {
		TicketVO tkvo = new TicketVO();
		
		System.out.println();
		System.out.println("============================예약변경===========================");
		System.out.println("\t예약번호\t\t편명\tID\t결제금액\t마일리지(사용)"); // ticket_info 테이블
		int num=0;
		for(TicketVO tkvv:tkAl) {
			System.out.println((num+1)+".\t"+tkvv.getT_num()+"\t"+tkvv.getAir_num()+"\t"+tkvv.getId()+"\t"+tkvv.getPayment()+"\t"+tkvv.getUse_point());
			num++;
		}
		System.out.println("=============================================================");
		System.out.println();
		

		
		
		int t_num_sel=0;
		while(true) {
			System.out.println("변경할 번호를 입력하세요.");// 사용자가 기존에 가지고 있던 항공권 중 선택
			t_num_sel=sc.nextInt();
			System.out.println();
			if(t_num_sel<1||t_num_sel>num) {
				System.err.println("재입력하세요.");
			}
			else {
				break;
			}

		}
		tkvo = tkAl.get(t_num_sel-1);
		return tkvo;	
	}

	public HGairVO viewReserveChange(ArrayList<HGairVO> hgAl) {

		System.out.println("============================예약변경===========================");
		HGairVO hgvo = new HGairVO();

		//+
		System.out.println();
		System.out.println("==============================HG AIR LINE=============================="); 
		System.out.println("번호\t편명\t출발날짜\t\t\t출발지\t도착지\t가격\t잔여좌석");
		int num = 1;
		for(HGairVO vo: hgAl) { // 전체 항공편 출력
			// 출발날짜 출발지 도착지 가격
			System.out.println(num+"\t"+vo.getAir_num()+"\t"+vo.getFlight_date()+"\t"+vo.getDeparture()+"\t"+vo.getArrive()+"\t"+vo.getPrice()+"원\t"+vo.getS_cnt()+"석");
			num++;
		}
		System.out.println("=============================================================");
		System.out.println();
		//-
		//view_ticket();
		int change=0;
		while(true) {
			System.out.println();
			System.out.println("동일한 금액으로만 변경가능합니다.");
			System.out.println("변경하실 운항편의 번호를 입력하세요."); // 새로운 항공편 선택
			change=sc.nextInt(); 
			//hgvo = hgAl.get(change-1);
			if(change<1||num<change) {
				System.err.println("재입력하세요.");
			}
			else {
				break;
			}
		}
		hgvo = hgAl.get(change-1);
		return hgvo;
	}

	public void viewReserveChangeSuccess() {
		for (int i = 0; i < 3; i++) {
			System.out.print(".");
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("예약변경이 완료되었습니다!");
		System.out.println();
	}
	public void viewReserveChangeFailure() {
		for (int i = 0; i < 3; i++) {
			System.out.print(".");
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("예약 변경 실패!");
		System.out.println("동일한 금액으로만 변경가능합니다. 예약정보를 다시 확인해주세요!");
		System.out.println();
	}

	public TicketVO viewReserveCancleSelNum(ArrayList<TicketVO> tkAl) {
		TicketVO tkvo = new TicketVO();
		System.out.println("============================예약취소===========================");
		System.out.println("\t예약번호\t\t편명\tID\t결제금액\t마일리지(사용)"); // ticket_info 테이블
		int num=0;
		for(TicketVO tkvv:tkAl) {
			System.out.println((num+1)+".\t"+tkvv.getT_num()+"\t"+tkvv.getAir_num()+"\t"+tkvv.getId()+"\t"+tkvv.getPayment()+"\t"+tkvv.getUse_point());
			num++;
		}

		System.out.println("=============================================================");
		int t_num_sel=0;

		while(true) {

			System.out.println("취소할 운항편번호 입력");// 취소할 항공권 선택
			t_num_sel=sc.nextInt();
			if(t_num_sel<1||t_num_sel>num) {
				System.err.println("재입력하세요.");
			}
			else {
				break;
			}
		}


		return tkAl.get(t_num_sel-1);
	}

	public int viewReserveCancleSel() {
		System.out.println("정말 취소하시겠습니까...?");
		System.out.println();
		System.out.println("[1]예     [2]아니오");
		int YN=sc.nextInt();
		return YN;

	}

	public void viewReserveCancle() {
		for (int i = 0; i < 3; i++) {
			System.out.print(".");
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.print("예약취소 완료!");
		System.out.println();
	}

	public void viewReserveClose() {
		System.out.println("메인화면으로 돌아갑니다.");
	}

	public MemberVO viewSignup() {
		MemberVO memvo = new MemberVO();
		System.out.println();
		System.out.println("=======================회원가입=======================");
		System.out.println();

		System.out.print("아이디 입력: ");
		String id=sc.next();
		System.out.print("비밀번호 입력: ");
		String pw=sc.next();
		System.out.print("이름 입력: ");
		String name=sc.next();
		int m_point=0; // 회원가입시 마일리지=0

		System.out.println("회원가입완료!");
		memvo.setId(id);
		memvo.setPw(pw);
		memvo.setName(name);
		memvo.setM_point(m_point);
		return memvo;

	}
	public void viewClose() {
		System.out.println("프로그램이 종료되었습니다.");
	}

}