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
	public int ans =1;								//�����Ҷ� ����� ����
	static int tNum = 1000;



	public void start() {							//�α��� ������ DBȣ�� al�� ����
		memvo = memdao.selectOne(memvo);			//ȸ�� ���� ����
		hgAl = res.getAirinfoList();				//�װ��� ���� ����
		tkAl = tkdao.tCheckAll();					//���� ���� ����
	}
	public void ctrStart() {
		ans = hgview.start();						//[�α��� ȸ������ ����] ����
	}

	public Boolean ctrLogin() {						//�α���
		memvo = hgview.viewLogin(memvo);			//�α��� �Է� �ޱ�	
		return res.login(memvo);					//�Է¹��� id pw �ɹ������� ��
	}

	public void ctrLoginSuccess() {					//�α��� ����ȭ�� ���
		hgview.loginSuccess();
	}

	public void ctrLoginFailure() {					//�α��� ����ȭ�� ���
		hgview.loginFailure();
	}

	public void ctrReserve() {						//[���� ����Ȯ�� ����] ����
		ans = hgview.viewReserveSel();		
	}

	public void ctrReserveAir() {					//�װ������	
		hgvo = hgview.viewRerserveAir(hgAl);		//������ �װ��� ����
	}

	public int ctrResrveNP() {						//�ο�����
		tkvo = hgview.viewReserveNP(hgvo);			//������ �ο� ƼĹ������ ����
		return tkvo.getp_cnt();						//�ο����� ����
	}
	
	public void ctrReserveNPSel() {					//�ο�Ȯ�� �ο��� ?�� �½��ϱ�[���� �ƴ�]
		ans = hgview.viewReserveNPsel(tkvo);
	}

	public void crtPointSel() {						//���ϸ��� ��뿩�� ����[��� �̻��]
		ans = hgview.viewPointSel(memvo);
	}

	public void ctrPointUse() {						//���ϸ��� ���		
		tkvo = hgview.viewPointUse(memvo, tkvo);	//���ϸ��� ��뷮 ���������� ����

	}
	public void ctrPaySel(){						//�����Ͻðڽ��ϱ� [���� �̰���]
		ans = hgview.viewPaySel();
	}

	public void ctrPay() {							//����
		String tmp = null;
		int max = tNum;
		Iterator<TicketVO> itr = tkAl.iterator();	
		while(itr.hasNext()) {						//�����ȣ ���� ����ū���� ã�Ƽ� 
			tmp = itr.next().getT_num();			//max�� ������
			tmp = tmp.substring(4);					//�����ȣ��++ �Ͽ� �����ȣ ����
			if(max < Integer.parseInt(tmp)) {
				max = Integer.parseInt(tmp);
			}
		}
		
		tNum = max +1;

		tkvo.setT_num("RSNO"+tNum);					//�����ȣ Ƽ�������� ����
		tkvo.setId(memvo.getId());					//id Ƽ�������� ����

		hgview.viewPay(tkvo);						//�����Ϸ� ���

		tkdao.airReservation(tkvo);					//Ƽ������ DB������Ʈ
		if(tkvo.getUse_point()>0) {					//���ϸ��� DB������Ʈ
			memdao.mUse(tkvo);
		}
		else {										//���ϸ���++ DB������Ʈ
			memdao.mSave(tkvo);
		}
		res.SeatCntMinus(hgvo, tkvo);				//�װ��� �¼�-- DB������Ʈ

	}

	public void ctrNoPay() {						//���� �̼���
		hgview.viewNoPay();							//���� ȭ�����
	}

	public void ctrPaySelErr() {					//���� ����
		hgview.viewPaySelErr();
	}

	public Boolean ctrReserveCheck() {					//����Ȯ�� �� ����[���ຯ�� ������� ����]
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

	public void ctrReserveChange() {				//���ຯ��
		hgAl = res.getAirinfoList();
		HGairVO hgvocg = new HGairVO();				//������ �װ����� ����
		
		tkvo = hgview.viewReserveChangeSel(tkAl);	//������ ���༱��[1�� 2�� 3�� ...]
		for(int i = 0 ; i < hgAl.size(); i++) {
			if(tkvo.getAir_num().equals(hgAl.get(i).getAir_num())) {
				hgvo = hgAl.get(i);					//������ �װ��� ����
			}
			
		}
		hgvocg = hgview.viewReserveChange(hgAl);	//�����Ϸ��� �װ��� ����[1�� 2�� 3��...]

		if(tkvo.getPayment()+tkvo.getUse_point() == hgvocg.getPrice()*tkvo.getp_cnt() && hgvocg.getS_cnt() >= tkvo.getp_cnt()) {	//������ ������
		
			res.SeatCntPlus(hgvo, tkvo);			//������ �¼� ���� DB������Ʈ
			res.TicketCancel(tkvo,memvo);			//������� DB������Ʈ
		
			tkvo.setAir_num(hgvocg.getAir_num());	//�װ��� ����
			res.SeatCntMinus(hgvocg, tkvo);			//�¼����� DB������Ʈ	
			tkdao.airReservation(tkvo);				//Ƽ�� DB������Ʈ
			if(tkvo.getUse_point()>0) {				//���ϸ��� DB������Ʈ
				memdao.mUse(tkvo);
			}
			else {									//���ϸ���++ DB������Ʈ
				memdao.mSave(tkvo);
			}
			hgview.viewReserveChangeSuccess();		//�¼����� ���� ��� 
		}
		else {
			hgview.viewReserveChangeFailure();
		}
	}

	public void ctrReserveCancleSelNum() {			//����� ��ȣ ����[1�� 2�� 3��...]
		tkvo = hgview.viewReserveCancleSelNum(tkAl);//����� �������� ����
		Iterator<HGairVO> itr = hgAl.iterator();
		
		while(itr.hasNext()) {						
			hgvo = itr.next();
			if(hgvo.getAir_num().equals(tkvo.getAir_num())) {	//�װ������� ���������� �װ���ȣ�� ������� �װ��� ����
				break;
			}
		}

	}
	public void ctrReserveCancleSel() {				//����ϰڽ��ϱ�[�� �ƴϿ�]
		ans=hgview.viewReserveCancleSel();
	}
	public void ctrReserveCancle() {				//���
		hgview.viewReserveCancle();		
		res.SeatCntMinus(hgvo, tkvo);
		res.SeatCntPlus(hgvo, tkvo);				//�¼����� DB������Ʈ
		res.TicketCancel(tkvo,memvo);				//������� �� ���ϸ��� ��ȯ DB������Ʈ
	}

	public void ctrSignup() {					
		memdao.singUp(hgview.viewSignup());			//ȸ������ DB������Ʈ
	}


	public void ctrClose() {
		hgview.viewClose();
	}

	public void ctrReserveClose() {
		hgview.viewReserveClose();
	}



}
