
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
		System.out.println("      [1]�α���     [2]ȸ������     [3]���α׷�����         ");
		return sc.nextInt();  // �μ� �Է�
	}

	public MemberVO viewLogin(MemberVO memvo) {
		System.out.println("=======================�α���=======================");
		while(true) {
			System.out.print("���̵�: ");
			String id=sc.next(); // ȸ�� ���̵� �Է�
			System.out.print("��й�ȣ: ");
			String pw=sc.next(); // ȸ�� ��й�ȣ �Է�
			// ���̵� ��й�ȣ üŷ 
			memvo.setId(id);
			memvo.setPw(pw);
			return memvo;
		}
	}

	public void loginSuccess() {
		System.out.println();
		System.out.println("�α��� ����!");
		System.out.println("==================================================");
	}
	public void loginFailure() {
		System.err.println("���̵�� ��й�ȣ�� �ٽ� Ȯ�����ּ���!");
	}

	public int viewReserveSel() {
		System.out.println();
		System.out.println("[1]����     [2]������ȸ     [3]����");
		return sc.nextInt();
	}

	public HGairVO viewRerserveAir(ArrayList<HGairVO> al) {
		int air_choice;
		while(true) {
			int i = 0;
			int index=1; // ��ȣ
			System.out.println("==============================HG AIR LINE=============================="); 
			System.out.println("��ȣ\t���\t��߳�¥\t\t\t�����\t������\t����\t�ܿ��¼�");
			for(HGairVO vo: al) { // ��ü �װ��� ���
				// ��߳�¥ ����� ������ ����
				System.out.println(index+"\t"+vo.getAir_num()+"\t"+vo.getFlight_date()+"\t"+vo.getDeparture()+"\t"+vo.getArrive()+"\t"+vo.getPrice()+"��\t"+vo.getS_cnt()+"��");
				index++;
			}
			System.out.println("=======================================================================");
			System.out.println("\n���ϴ� �װ����� �������ֽʽÿ�.");
			air_choice=sc.nextInt(); // �װ����� ���ϴ� �װ���(PK?)����
			if( air_choice>0&& air_choice<12) { //11���� �װ���
				System.out.println("\t\t��ȣ\t���\t��߳�¥\t\t\t�����\t������\t����\t�ܿ��¼�");
				System.out.print("������ �װ�����\t");
				System.out.println(air_choice+"\t"+al.get(air_choice-1).getAir_num()+"\t"+al.get(air_choice-1).getFlight_date()+"\t"+al.get(air_choice-1).getDeparture()+"\t"+al.get(air_choice-1).getArrive()+"\t"+al.get(air_choice-1).getPrice()+"��\t"+al.get(air_choice-1).getS_cnt()+"��\t�Դϴ�"); // ������ �װ��� ���
				System.out.println();

				break;
			}
			else {
				System.err.println("\n���Է��ϼ���.\n");
			}


		}
		return al.get(air_choice-1);
	}

	public TicketVO viewReserveNP(HGairVO hgvo) {
		TicketVO tkvo = new TicketVO();
		while(true) {
			if(hgvo.getS_cnt()==0) {
				System.out.println("�ܿ��¼� ����");
				break;
			}
			System.out.print("\n�ο��� �Է�: ");
			int personal=sc.nextInt(); // �ο�����
			if(personal<1||personal>hgvo.getS_cnt()) { // ��ȿ�� �����ο��� �¼����� ����X
				System.err.println("\n���Է��ϼ���.\n");
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
			System.out.println(tkvo.getp_cnt()+"�� �����ʴϱ�?");
			System.out.println("[1]��     [2]�ƴϿ�");
			s_choice=sc.nextInt(); // �ο��� Ȯ�� ����
			if(s_choice==1) { // Ż��->�ο����� ����
				break;
			}
			else if (s_choice==2){ // ����Ż��->�ο����Է�
				break;
			}
			else { // ��ȿ���˻�->���� �ݺ�
				System.err.println("\n���Է��ϼ���.");
			}
		}

		return s_choice;	
	}


	public int viewPointSel(MemberVO vo) {
		int purchase_choice = 0; // ��������
		System.out.println("���� "+vo.getId()+"���� ������ ��� ���ϸ����� "+vo.getM_point()+"�Դϴ�.\n");


		System.out.println("���ϸ����� ����Ͻðڽ��ϱ�?");
		System.out.println("\n[1]��     [2]�ƴϿ�");
		purchase_choice = sc.nextInt();
		return purchase_choice;
	}

	public TicketVO viewPointUse(MemberVO memvo, TicketVO tkvo) {
		if(memvo.getM_point()>=tkvo.getPayment()) { // ���ϸ��� >= �ѱݾ� 
			System.out.println(memvo.getId()+"���� ���� ���� �ݾ��� 0�� �Դϴ�."); // memvo.getName()�߰� ����̸� �������ּ���!
			tkvo.setUse_point(tkvo.getPayment());
			memvo.setM_point(memvo.getM_point()-tkvo.getPayment()); // ���ϸ��� ����
			tkvo.setPayment(0);

		}
		else { // ���ϸ��� < �ѱݾ�
			System.out.println(memvo.getId()+"���� ���� ���� �ݾ��� "+(tkvo.getPayment()-memvo.getM_point())+"�� �Դϴ�."); // memvo.getName()�߰� ����̸� �������ּ���!
			tkvo.setPayment(tkvo.getPayment()-memvo.getM_point());
			tkvo.setUse_point(memvo.getM_point());
			memvo.setM_point(0);; // ���ϸ��� ����
		}
		System.out.println("\n���� ���ϸ����� "+memvo.getM_point()+" �Դϴ�.");
		System.out.println("\n������ �����մϴ�.");
		return tkvo;
	}

	public int viewPaySel() {
		System.out.println();
		System.out.println("�����Ͻðڽ��ϱ�?\n[1]��     [2]�ƴϿ�"); 
		int purchase_choice=sc.nextInt();
		return purchase_choice;
	}

	public void viewPay(TicketVO tkvo) {

		System.out.print("������ �������Դϴ�.");
		for(int i=0; i<5; i++) {
			System.out.print("."); // ���� ���
			try {
				Thread.sleep(1000); // 1��
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println("\n������ �Ϸ�Ǿ����ϴ�.\n");
		System.out.println("=======================��������=======================");
		System.out.println("�����ȣ\t\t���\tID\t�����ݾ�\t���ϸ���(���)");
		System.out.println(tkvo.getT_num()+"\t\t"+tkvo.getAir_num()+"\t"+tkvo.getId()+"\t"+tkvo.getPayment()+"\t"+tkvo.getUse_point());
		System.out.println("====================================================");
	}

	public void viewPaySelErr() {
		System.err.println("\n���Է��ϼ���.");
	}


	public void viewNoPay() {
		System.out.println("\n������ �����մϴ�.");

	}

	public int viewReserveCheck(ArrayList<TicketVO> tkAl) {
		System.out.println("=======================��������=======================");
		System.out.println("�����ȣ\t\t���\tID\t�����ݾ�\t���ϸ���(���)"); // ticket_info ���̺�
		for(TicketVO tkvo:tkAl) {
			System.out.println(tkvo.getT_num()+"\t"+tkvo.getAir_num()+"\t"+tkvo.getId()+"\t"+tkvo.getPayment()+"\t"+tkvo.getUse_point());
		}
		System.out.println("====================================================");
		// ���� ��� �� ... -> ���ຯ�� or ��� or ���� 
		// +3��â
		System.out.println("[1]���ຯ��     [2]�������     [3]����");
		int changeOrCancle=sc.nextInt();
		return changeOrCancle;
	}

	public void NoRes() {
		System.out.println();
		System.out.println("���������� �����ϴ�.");
	}
	
	public TicketVO viewReserveChangeSel(ArrayList<TicketVO> tkAl) {
		TicketVO tkvo = new TicketVO();
		
		System.out.println();
		System.out.println("============================���ຯ��===========================");
		System.out.println("\t�����ȣ\t\t���\tID\t�����ݾ�\t���ϸ���(���)"); // ticket_info ���̺�
		int num=0;
		for(TicketVO tkvv:tkAl) {
			System.out.println((num+1)+".\t"+tkvv.getT_num()+"\t"+tkvv.getAir_num()+"\t"+tkvv.getId()+"\t"+tkvv.getPayment()+"\t"+tkvv.getUse_point());
			num++;
		}
		System.out.println("=============================================================");
		System.out.println();
		

		
		
		int t_num_sel=0;
		while(true) {
			System.out.println("������ ��ȣ�� �Է��ϼ���.");// ����ڰ� ������ ������ �ִ� �װ��� �� ����
			t_num_sel=sc.nextInt();
			System.out.println();
			if(t_num_sel<1||t_num_sel>num) {
				System.err.println("���Է��ϼ���.");
			}
			else {
				break;
			}

		}
		tkvo = tkAl.get(t_num_sel-1);
		return tkvo;	
	}

	public HGairVO viewReserveChange(ArrayList<HGairVO> hgAl) {

		System.out.println("============================���ຯ��===========================");
		HGairVO hgvo = new HGairVO();

		//+
		System.out.println();
		System.out.println("==============================HG AIR LINE=============================="); 
		System.out.println("��ȣ\t���\t��߳�¥\t\t\t�����\t������\t����\t�ܿ��¼�");
		int num = 1;
		for(HGairVO vo: hgAl) { // ��ü �װ��� ���
			// ��߳�¥ ����� ������ ����
			System.out.println(num+"\t"+vo.getAir_num()+"\t"+vo.getFlight_date()+"\t"+vo.getDeparture()+"\t"+vo.getArrive()+"\t"+vo.getPrice()+"��\t"+vo.getS_cnt()+"��");
			num++;
		}
		System.out.println("=============================================================");
		System.out.println();
		//-
		//view_ticket();
		int change=0;
		while(true) {
			System.out.println();
			System.out.println("������ �ݾ����θ� ���氡���մϴ�.");
			System.out.println("�����Ͻ� �������� ��ȣ�� �Է��ϼ���."); // ���ο� �װ��� ����
			change=sc.nextInt(); 
			//hgvo = hgAl.get(change-1);
			if(change<1||num<change) {
				System.err.println("���Է��ϼ���.");
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
		System.out.println("���ຯ���� �Ϸ�Ǿ����ϴ�!");
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
		System.out.println("���� ���� ����!");
		System.out.println("������ �ݾ����θ� ���氡���մϴ�. ���������� �ٽ� Ȯ�����ּ���!");
		System.out.println();
	}

	public TicketVO viewReserveCancleSelNum(ArrayList<TicketVO> tkAl) {
		TicketVO tkvo = new TicketVO();
		System.out.println("============================�������===========================");
		System.out.println("\t�����ȣ\t\t���\tID\t�����ݾ�\t���ϸ���(���)"); // ticket_info ���̺�
		int num=0;
		for(TicketVO tkvv:tkAl) {
			System.out.println((num+1)+".\t"+tkvv.getT_num()+"\t"+tkvv.getAir_num()+"\t"+tkvv.getId()+"\t"+tkvv.getPayment()+"\t"+tkvv.getUse_point());
			num++;
		}

		System.out.println("=============================================================");
		int t_num_sel=0;

		while(true) {

			System.out.println("����� �������ȣ �Է�");// ����� �װ��� ����
			t_num_sel=sc.nextInt();
			if(t_num_sel<1||t_num_sel>num) {
				System.err.println("���Է��ϼ���.");
			}
			else {
				break;
			}
		}


		return tkAl.get(t_num_sel-1);
	}

	public int viewReserveCancleSel() {
		System.out.println("���� ����Ͻðڽ��ϱ�...?");
		System.out.println();
		System.out.println("[1]��     [2]�ƴϿ�");
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
		System.out.print("������� �Ϸ�!");
		System.out.println();
	}

	public void viewReserveClose() {
		System.out.println("����ȭ������ ���ư��ϴ�.");
	}

	public MemberVO viewSignup() {
		MemberVO memvo = new MemberVO();
		System.out.println();
		System.out.println("=======================ȸ������=======================");
		System.out.println();

		System.out.print("���̵� �Է�: ");
		String id=sc.next();
		System.out.print("��й�ȣ �Է�: ");
		String pw=sc.next();
		System.out.print("�̸� �Է�: ");
		String name=sc.next();
		int m_point=0; // ȸ�����Խ� ���ϸ���=0

		System.out.println("ȸ�����ԿϷ�!");
		memvo.setId(id);
		memvo.setPw(pw);
		memvo.setName(name);
		memvo.setM_point(m_point);
		return memvo;

	}
	public void viewClose() {
		System.out.println("���α׷��� ����Ǿ����ϴ�.");
	}

}