package controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.HGairDAO;
import model.HGairVO;
import model.MemberDAO;
import model.MemberVO;
import model.ReservationDetailDAO;
import model.TicketDAO;
import model.TicketVO;
import view.view_HGAir;

public class AirController {
	static ReservationDetailDAO res = new ReservationDetailDAO();
	static MemberVO memvo = new MemberVO();
	static MemberDAO memdao = new MemberDAO();
	static TicketVO tkvo = new TicketVO();	
	static TicketDAO tkdao = new TicketDAO();
	static HGairVO hgvo = new HGairVO();
	static HGairDAO hgdao = new HGairDAO();

	static view_HGAir hgview = new view_HGAir();

	static ArrayList<MemberVO> memAl = new ArrayList();
	static ArrayList<HGairVO> hgAl = new ArrayList();
	static ArrayList<TicketVO> tkAl = new ArrayList();
	public int ans =1;								//선택할때 사용할 변수
	static int tNum = 1000;



	public void start() {							//로그인 성공시 DB호출 al에 저장
		memvo = memdao.selectOne(memvo);			//회원 정보 저장
		hgAl = res.getAirinfoList();				//항공기 정보 저장
		tkAl = tkdao.tCheckAll();					//예약 정보 저장
	}
	public void ctrStart() {
		ans = hgview.start();						//[로그인 회원가입 종료] 선택
	}

	public Boolean ctrLogin() {						//로그인
		memvo = hgview.viewLogin(memvo);			//로그인 입력 받기	
		return res.login(memvo);					//입력받은 id pw 맴버정보와 비교
	}

	public void ctrLoginSuccess() {					//로그인 성공화면 출력
		hgview.loginSuccess();
	}

	public void ctrLoginFailure() {					//로그인 실패화면 출력
		hgview.loginFailure();
	}

	public void ctrReserve() {						//[예약 예약확인 종료] 선택
		ans = hgview.viewReserveSel();		
	}

	public void ctrReserveAir() {					//항공편결정	
		hgvo = hgview.viewRerserveAir(hgAl);		//선택한 항공편 저장
	}

	public int ctrResrveNP() {						//인원설정
		tkvo = hgview.viewReserveNP(hgvo);			//선택한 인원 티캣정보에 저장
		return tkvo.getp_cnt();						//인원정보 리턴
	}
	
	public void ctrReserveNPSel() {					//인원확인 인원이 ?명 맞습니까[맞음 아님]
		ans = hgview.viewReserveNPsel(tkvo);
	}

	public void crtPointSel() {						//마일리지 사용여부 선택[사용 미사용]
		ans = hgview.viewPointSel(memvo);
	}

	public void ctrPointUse() {						//마일리지 사용		
		tkvo = hgview.viewPointUse(memvo, tkvo);	//마일리지 사용량 예약정보에 저장

	}
	public void ctrPaySel(){						//결제하시겠습니까 [결제 미결제]
		ans = hgview.viewPaySel();
	}

	public void ctrPay() {							//결제
		String tmp = null;
		int max = tNum;
		Iterator<TicketVO> itr = tkAl.iterator();	
		while(itr.hasNext()) {						//예약번호 에서 가장큰값을 찾아서 
			tmp = itr.next().getT_num();			//max에 저장후
			tmp = tmp.substring(4);					//예약번호에++ 하여 예약번호 저장
			if(max < Integer.parseInt(tmp)) {
				max = Integer.parseInt(tmp);
			}
		}
		
		tNum = max +1;

		tkvo.setT_num("RSNO"+tNum);					//예약번호 티켓정보에 저장
		tkvo.setId(memvo.getId());					//id 티켓정보에 저장

		hgview.viewPay(tkvo);						//결제완료 출력

		tkdao.airReservation(tkvo);					//티켓정보 DB업데이트
		if(tkvo.getUse_point()>0) {					//마일리지 DB업데이트
			memdao.mUse(tkvo);
		}
		else {										//마일리지++ DB업데이트
			memdao.mSave(tkvo);
		}
		res.SeatCntMinus(hgvo, tkvo);				//항공편 좌석-- DB업데이트

	}

	public void ctrNoPay() {						//결제 미선택
		hgview.viewNoPay();							//종료 화면출력
	}

	public void ctrPaySelErr() {					//선택 오류
		hgview.viewPaySelErr();
	}

	public Boolean ctrReserveCheck() {					//예약확인 및 선택[예약변경 예약취소 종료]
		Boolean flag = true;
		tkAl = tkdao.TicketCheck(memvo);
		if(tkAl.isEmpty()) {
			flag = false;
		}
		else {
			ans = hgview.viewReserveCheck(tkAl);
			flag = true;			
		}
		return flag;
	}
	
	public void ctrNoRes() {
		hgview.NoRes();
	}

	public void ctrReserveChange() {				//예약변경
		hgAl = res.getAirinfoList();
		HGairVO hgvocg = new HGairVO();				//변경할 항공변수 선언
		
		tkvo = hgview.viewReserveChangeSel(tkAl);	//변경할 에약선택[1번 2번 3번 ...]
		for(int i = 0 ; i < hgAl.size(); i++) {
			if(tkvo.getAir_num().equals(hgAl.get(i).getAir_num())) {
				hgvo = hgAl.get(i);					//변경전 항공기 저장
			}
			
		}
		hgvocg = hgview.viewReserveChange(hgAl);	//변경하려는 항공기 선택[1번 2번 3번...]

		if(tkvo.getPayment()+tkvo.getUse_point() == hgvocg.getPrice()*tkvo.getp_cnt() && hgvocg.getS_cnt() >= tkvo.getp_cnt()) {	//가격이 같으면
		
			res.SeatCntPlus(hgvo, tkvo);			//변경전 좌석 증가 DB업데이트
			res.TicketCancel(tkvo,memvo);			//예약취소 DB업데이트
		
			tkvo.setAir_num(hgvocg.getAir_num());	//항공편 변경
			res.SeatCntMinus(hgvocg, tkvo);			//좌석변경 DB업데이트	
			tkdao.airReservation(tkvo);				//티켓 DB업데이트
			if(tkvo.getUse_point()>0) {				//마일리지 DB업데이트
				memdao.mUse(tkvo);
			}
			else {									//마일리지++ DB업데이트
				memdao.mSave(tkvo);
			}
			hgview.viewReserveChangeSuccess();		//좌석변경 성공 출력 
		}
		else {
			hgview.viewReserveChangeFailure();
		}
	}

	public void ctrReserveCancleSelNum() {			//취소할 번호 선택[1번 2번 3번...]
		tkvo = hgview.viewReserveCancleSelNum(tkAl);//취소할 예약정보 저장
		Iterator<HGairVO> itr = hgAl.iterator();
		
		while(itr.hasNext()) {						
			hgvo = itr.next();
			if(hgvo.getAir_num().equals(tkvo.getAir_num())) {	//항공정보와 예약정보의 항공번호가 같은경우 항공편 저장
				break;
			}
		}

	}
	public void ctrReserveCancleSel() {				//취소하겠습니까[네 아니요]
		ans=hgview.viewReserveCancleSel();
	}
	public void ctrReserveCancle() {				//취소
		hgview.viewReserveCancle();		
		res.SeatCntMinus(hgvo, tkvo);
		res.SeatCntPlus(hgvo, tkvo);				//좌석증가 DB업데이트
		res.TicketCancel(tkvo,memvo);				//예약취소 및 마일리지 반환 DB업데이트
	}

	public void ctrSignup() {					
		memdao.singUp(hgview.viewSignup());			//회원가입 DB업데이트
	}


	public void ctrClose() {
		hgview.viewClose();
	}

	public void ctrReserveClose() {
		hgview.viewReserveClose();
	}



}
