package team;

import controller.AirController;

public class Test1 {	
	public static void main(String[] args) {
		AirController airCtr = new AirController();   
		while(true) {
			airCtr.ctrStart();						//[�α��� ȸ������ ����] ����
			if(airCtr.ans==1) {						//�α���
				if(!airCtr.ctrLogin()) {
					airCtr.ctrLoginFailure();		//id,pw�� db�� ������ �α��� ����
					continue;						//�α��� ȭ��
				}
				else {
					airCtr.ctrLoginSuccess();		//�α��� ����
				}

				while(true) {
					airCtr.start();					//db�� �ִ� ������ ȣ��
					airCtr.ctrReserve();			//[���� ������ȸ ����] ����

					if(airCtr.ans==1) {				//����
						airCtr.ctrReserveAir();		//�װ��� ����

						if(airCtr.ctrResrveNP()==0) {//�ο����� 0���̸� �ٽ�
							continue;
						}
						airCtr.ctrReserveNPSel();	//�ο����� Ȯ��

						if(airCtr.ans==1) {


							while(true) {
								airCtr.crtPointSel();			//���ϸ��� ��뿩��

								if(airCtr.ans==1) {				//���ϸ��� ���
									airCtr.ctrPointUse();		//���ϸ��� ����
									while(true) {
										airCtr.ctrPaySel();		//��������
										if(airCtr.ans ==1) {	//����
											airCtr.ctrPay();
											break;
										}
										else if(airCtr.ans ==2) {//����x
											airCtr.ctrNoPay();
											break;
										}
										else {					//��ȿ��
											airCtr.ctrPaySelErr();
											continue;
										}
									}
									break;
								}	
								else if(airCtr.ans==2) {			//���ϸ��� �̻��
									while(true) {
										airCtr.ctrPaySel();			//��������
										if(airCtr.ans ==1) {		//����
											airCtr.ctrPay();
											break;
										}
										else if(airCtr.ans ==2) {	//����x
											airCtr.ctrNoPay();
											break;
										}
										else {						//��ȿ��
											airCtr.ctrPaySelErr();
											continue;
										}
									}								//����	
									break;
								}
								else {
									airCtr.ctrPaySelErr();			//��ȿ��
									continue;
								}
							}

						}
						else if(airCtr.ans==2) {	//�ο����� �ƴϿ�
							continue;
						}

						else {
							airCtr.ctrPaySelErr();
							continue;
						}

					}

					else if(airCtr.ans==2) {		//���� Ȯ��	
						while(true) {
							if(!airCtr.ctrReserveCheck()) {
								airCtr.ctrNoRes();
								break;
								
							}

							if(airCtr.ans==1) {
								airCtr.ctrReserveChange();
							}
							else if(airCtr.ans==2) {	//���� ���
								airCtr.ctrReserveCancleSelNum(); //Ƽ�ϼ���
								while(true) {
									airCtr.ctrReserveCancleSel();	//��� ����
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
			else if(airCtr.ans ==2) {			//ȸ�� ����
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
