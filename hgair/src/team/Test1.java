package team;

import controller.AirController;

public class Test1 {	
	public static void main(String[] args) {
		AirController airCtr = new AirController();   
		while(true) {
			airCtr.ctrStart();						//[로그인 회원가입 종료] 선택
			if(airCtr.ans==1) {						//로그인
				if(!airCtr.ctrLogin()) {
					airCtr.ctrLoginFailure();		//id,pw가 db에 없을시 로그인 실패
					continue;						//로그인 화면
				}
				else {
					airCtr.ctrLoginSuccess();		//로그인 성공
				}

				while(true) {
					airCtr.start();					//db에 있는 데이터 호출
					airCtr.ctrReserve();			//[예약 예약조회 종료] 선택

					if(airCtr.ans==1) {				//예약
						airCtr.ctrReserveAir();		//항공편 선택

						if(airCtr.ctrResrveNP()==0) {//인원선택 0명이면 다시
							continue;
						}
						airCtr.ctrReserveNPSel();	//인원선택 확인

						if(airCtr.ans==1) {


							while(true) {
								airCtr.crtPointSel();			//마일리지 사용여부

								if(airCtr.ans==1) {				//마일리지 사용
									airCtr.ctrPointUse();		//마일리지 감소
									while(true) {
										airCtr.ctrPaySel();		//결제선택
										if(airCtr.ans ==1) {	//결제
											airCtr.ctrPay();
											break;
										}
										else if(airCtr.ans ==2) {//결제x
											airCtr.ctrNoPay();
											break;
										}
										else {					//유효성
											airCtr.ctrPaySelErr();
											continue;
										}
									}
									break;
								}	
								else if(airCtr.ans==2) {			//마일리지 미사용
									while(true) {
										airCtr.ctrPaySel();			//결제선택
										if(airCtr.ans ==1) {		//결제
											airCtr.ctrPay();
											break;
										}
										else if(airCtr.ans ==2) {	//결제x
											airCtr.ctrNoPay();
											break;
										}
										else {						//유효성
											airCtr.ctrPaySelErr();
											continue;
										}
									}								//결제	
									break;
								}
								else {
									airCtr.ctrPaySelErr();			//유효성
									continue;
								}
							}

						}
						else if(airCtr.ans==2) {	//인원선택 아니요
							continue;
						}

						else {
							airCtr.ctrPaySelErr();
							continue;
						}

					}

					else if(airCtr.ans==2) {		//예약 확인	
						while(true) {
							if(!airCtr.ctrReserveCheck()) {
								airCtr.ctrNoRes();
								break;
								
							}

							if(airCtr.ans==1) {
								airCtr.ctrReserveChange();
							}
							else if(airCtr.ans==2) {	//예약 취소
								airCtr.ctrReserveCancleSelNum(); //티켓선택
								while(true) {
									airCtr.ctrReserveCancleSel();	//취소 여부
									if(airCtr.ans ==1) {
										airCtr.ctrReserveCancle();
										break;
									}
									else if(airCtr.ans ==2) {
										airCtr.ctrReserveClose();
										break;
									}
									else {
										airCtr.ctrPaySelErr();
										continue;
									}
								}
							}

							else if(airCtr.ans==3) {
								airCtr.ctrReserveClose();
								break;
							}

							else {
								airCtr.ctrPaySelErr();
								continue;
							}
						}

					}
					else if(airCtr.ans==3) {
						airCtr.ctrReserveClose();
						break;
					}

					else {
						airCtr.ctrPaySelErr();
						continue;
					}

				}	

			}
			else if(airCtr.ans ==2) {			//회원 가입
				airCtr.ctrSignup();

			}
			else if(airCtr.ans ==3) {
				airCtr.ctrClose();

				return;
			}	
			else {
				airCtr.ctrPaySelErr();
			}
		}

	}

}
