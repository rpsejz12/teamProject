package drink;

import java.util.Scanner;


public class drink{

	public static void menuChange(String[] drinkName, int[][] drinkData) { //�Ŵ��� �̸��� �������ִ� �Լ�
		int msChoice; //�����ڰ� ���� ��ȣ�� ������ ����;
		while(true) { // �������� �ۼ��ؾ���
			Scanner sc = new Scanner(System.in);


			System.out.println("�ڡ١ڡ١ڡٰ����� ���ڡ١ڡ١ڡ�");
			System.out.println("1. ======�Ŵ��� ����======");
			System.out.println("2. =====�Ŵ����� ����=====");
			System.out.println("3. =======��� �߰�=======");
			System.out.println("4. =======������ Ȯ��=======");
			System.out.println("5. =======����=======");
			System.out.println("==���ϴ� ������ ��ȣ�� �Է��ϼ���==");

			do {
				msChoice = sc.nextInt();
				if(msChoice < 1 || msChoice > 5) {
					System.out.println("���Է�");
				}
			}while(msChoice < 1 || msChoice > 5);

			// �޴����� ��带 ����������
			switch(msChoice) {
			case 1: //�޴��̸� �����ϱ�! msChoice == 1

				//  DRINKNAME(���� �̸�) �迭�� ���
				System.out.println("==========================");
				System.out.println("�ھ �Ŵ��� �̸��� �����Ͻðڽ��ϱ�?��");
				for (int i = 0; i < drinkName.length; i++) { 
					System.out.print((i+1)+"."+drinkName[i]+" ");
				}
				//���� ������ �̸� �Է¹ޱ�
				System.out.println();

				int changeName; //������ �Ŵ��� �����ϴ� ����
				while (true) { //�Ŵ��� ��ȣ�� ������ ��ȿ�� �˻�
					changeName = sc.nextInt();
					if (changeName < 1 || changeName > drinkName.length) {
						System.out.print("============================");
						System.out.print("�ش� �Ŵ��� �����ϴ� �ٽ� �Է����ּ��� : ");
						continue;
					}
					break;
				}

				// �Է¹��� �Ŵ� ��� �� ����
				System.out.println("������ �̸��� �ۼ��ϼ��� : ");
				drinkName[changeName-1] = sc.next();
				System.out.print("\""+drinkName[changeName-1]+"\""+"�� ����Ϸ�");

				System.out.println();
				break;

			case 2:  //���� ���� msChoice == 1
				// �Ŵ��� �̸��� ���
				System.out.println("==========================");
				System.out.println("�ھ �Ŵ��� ������ �����Ͻðڽ��ϱ�?��");
				System.out.println("===========���簡��==========");
				for (int i = 0; i < drinkName.length; i++) { 
					System.out.print((i+1)+"."+drinkName[i]+drinkData[1][i]+"�� ");
				}
				// ���� ������ ��ȣ�� �Է¹ޱ�
				System.out.println();
				int changePrice; // ������� ��ȣ�� �Է¹޴� ��ġ

				while(true) { // ������ ��ȣ�� ��ȿ���˻�
					changePrice = sc.nextInt();
					if (changePrice < 1 || changePrice > drinkName.length) {
						System.out.println("==========================");
						System.out.print("�ش� �Ŵ��� �����ϴ� �ٽ� �Է��ϼ��� : ");
						continue;
					}
					break;
				}

				// ����ڰ� �� �Ŵ��� ���� ����
				System.out.print("������ ������ �Է��ϼ��� : ");
				int putPrice;
				// �����Է� 0�� ������ �� ��ȿ�� �˻�
				while (true) {
					putPrice = sc.nextInt();
					if (putPrice <= 0) {
						System.out.print("0�� ���Ϸ� �Է��Ͻ� �� �����ϴ� �ٽ� �Է��ϼ��� : ");
						continue;
					}
					break;
				}

				drinkData[1][changePrice-1] = putPrice;
				System.out.print("\""+drinkData[1][changePrice-1]+"\""+"������ ����Ϸ�");
				System.out.println();
				break;

			case 3:  //��� �߰� �Է� msChoice == 1
				// �Ŵ��� �̸��� ���
				System.out.println("�ھ �Ŵ��� ��� �߰��Ͻðڽ��ϱ�?��");
				System.out.println("==========================");
				System.out.println("==========���� ���===========");
				for (int i = 0; i < drinkName.length; i++) {
					System.out.print((i+1)+"."+drinkName[i]+drinkData[0][i]+"�� ");
				}

				// ���� ������ ��� �Է¹ޱ�
				System.out.println();
				int changeStock;
				while(true) { // �Ŵ� ��ȿ�� �˻�
					changeStock = sc.nextInt();
					if (changeStock < 0 || changeStock > drinkName.length) {
						System.out.print("==========================");
						System.out.print("�ش� �Ŵ��� �����ϴ� �ٽ� �Է��ϼ��� : ");
						continue;
					}
					break;
				}

				// ����ڰ� �� �Ŵ��� ��� ����
				System.out.print("����� ������ �Է��ϼ��� : ");
				//��� 0�� �̸����� �Է½� ���Է�
				int putStock;
				while(true) {
					putStock = sc.nextInt();
					if (putStock < 0) {
						System.out.print("=================================");
						System.out.print("0�� �̸����� �Է��Ͻ� �� �����ϴ� �ٽ� �Է��ϼ��� :");
						continue;
					}
					break;
				}

				drinkData[0][changeStock-1] += putStock;
				System.out.print("�� \""+drinkData[0][changeStock-1]+"\""+"���� �߰��Ϸ�");
				System.out.println();

				break;
				
			case 4:		//������ Ȯ��
				for(int i = 0 ; i < drinkName.length; i ++) {
					System.out.println(drinkName[i] + ": ���[" + drinkData[0][i] + "] ����  [" + drinkData[1][i] + "]"  );
				}
				break;
				
			case 5:		//����
				return;


			}
		}
	}


	static final int MAX_MANU = 10;	//�޴��� �ִ밪 ����� ����
	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		System.out.println("���� �Ƹ� : �������^-^ ��ȯ���� ���Ǳ⿡ ���Ű��� ȯ���մϴ�!");
		System.out.println();

		String[] drinkName; 	//����� �̸� �迭
		String[] drinkNameSp;	//�ʱ� ����� �̸� �迭
		int[][] drinkData; 		//����� ����,���� �迭
		int[][] drinkDataSp;	//�ʱ� ����� ����,���� �迭
		int coin = 0; 		//�Է��� ���� ������
		int minPrice;			//������ ���� ����	
		int choice;				//���� ����
		int isExit;	//Ż��

		drinkName = new String[MAX_MANU];
		drinkData = new int[2][MAX_MANU];

		drinkNameSp = new String[] {"������ ����Ŀ��","����ֽ�","�����ݶ�","���� �ױ⽺"}; // �ʱ�޴�
		drinkDataSp = new int[][] {{5,5,5,5},{5000,1000,1500,1200}};				//�ʱ�޴��� ���,����

		for(int i = 0 ; i < drinkNameSp.length; i++) {		//�ʱ� �޴��̸��� i�� �޴��迭�� ����
			drinkName[i] = drinkNameSp[i];
			for(int j = 0 ; j < drinkDataSp.length; j++) { //i�޴��� ����, j�� �ݺ�(2) ��� ����
				drinkData[j][i] = drinkDataSp[j][i];	
			}
		}

		minPrice = drinkData[1][0];							//drinkData[0][0]�� �ּҰ����� ����
		for(int i = 0 ; i < drinkData[1].length;i++) {
			if(drinkData[1][i] == 0) {						//������ 0�̸� ���� drinkData���� ����ȹ迭�̿ܿ��� 0���� �ʱ�ȭ�Ǿ�����
				break;
			}
			if(minPrice > drinkData[1][i]) {				//������ Ž�� ����
				minPrice = drinkData[1][i];
			}

		}


		System.out.println("���� �������� ���� �ֹ��� ���ְڳ�?");
		System.out.println("=========================================================================");

		for (int i = 0; i < drinkName.length; i++) {		// �Ŵ��� ��� ���ִ� �ݺ���
			if(drinkName[i]!=null) {	//drinkName[i]�� null���� �ƴҶ��� �޴� ���
				System.out.print("["+(i+1)+"."+drinkName[i]+" "+drinkData[1][i]+"��� ]");
			}
		}
		System.out.println();
		System.out.println("=========================================================================");

		System.out.println("���� ���� : �ָӴϵ� �ε��Ͻ� �츮 �ճ� �ƴ� �մ�");
		System.out.println("���� ���� : \"���� �� ������\"");
		System.out.println("��... �帮�ڽ��ϴ�...");

		while (true) { 	//���� ����	
			System.out.print("(System) ���� ī���Ϳ� �÷��������� : ");
			do {
				coin += sc.nextInt(); 
				if(coin == -3333) {
					break;
				}
				if(coin<0) {
					System.out.println("������ :");
					coin = 0;
				}
				
			}while(coin<=0);
			
			//������ -3333 �̰ų� ������ 0���� ũ�� �ݺ�����
			
			
			if(coin == -3333) {
				menuChange(drinkName,drinkData);
				coin = 0;
				for (int i = 0; i < drinkName.length; i++) {
					if(drinkName[i] != null) {	//drinkName[i]�� null���� �ƴҶ��� �޴� ���
						System.out.print((i+1)+"."+drinkName[i]+drinkData[1][i]+"���  ");
					}
				}

			}

			if (coin < minPrice) { //�����ݾ��� ���������� ������ �߰��Է� while�� ù�κ����� �̵�
				System.out.println("�̺� ��尡 �������ݾ�!" + minPrice + "��� ���� �ø����!!");
				System.out.println("������ �ܾ� : "+coin);
				continue; 
			}
			else { 		//������ �̻� �Է½� �ݺ��ߴ�
				System.out.println("������ �ܾ� : "+coin);
				break;
			}
		}
		while(true) { 
			for (int i = 0; i < drinkName.length; i++) {
				if(drinkName[i] != null) {	//drinkName[i]�� null���� �ƴҶ��� �޴� ���
					System.out.print((i+1)+"."+drinkName[i]+drinkData[1][i]+"���  ");
				}
			}

			System.out.println();
			System.out.print("���� �Ƹ� : �����Ͻ� ������� ��ȣ�� �Է����ּ��� : ");

			do {	//���� ���� ��ȿ�� �˻�
				choice = sc.nextInt();
				if (choice < 0+1 || choice > drinkName.length || drinkName[choice-1] == null ) { //�������� �ʴ� �����϶�
					System.out.println(choice+"�׷� ������� �����... �ٸ� ���� �ֹ������");
					System.out.println("=========================");
				}
			}while( choice < 0+1 || choice > drinkName.length || drinkName[choice-1] == null );

			if (coin < drinkData[1][choice-1]) { //���� ���ᰡ�ݺ��� ������
				System.out.println("�ֽŵ��� �����ѵ���?");
				System.out.println("=========================");
				System.out.println("��带 �߰��Ͻðڽ��ϱ�?");
				System.out.println(" 1.YES    2.NO ");
				do {	//����߰� ���� ��ȿ�� �˻�
					isExit = sc.nextInt();
					if(isExit < 1 || isExit>2) {
						System.out.println("���Է�");
					}
				}while(isExit < 1 || isExit>2);

				if(isExit == 1) {
					System.out.print("�߰��� ����� �Է��ϼ��� : ");
					int addMoney;
					do {	//����߰� ��ȿ�� �˻� 0�̸� �ԷµǸ� �ٽ�
						addMoney = sc.nextInt();
					}while(addMoney < 0);//����ڰ� �߰��� ���� �Է���
					coin += addMoney;
					continue;
				}

				else if(isExit == 2){
					System.out.println(coin + "��� ��ȯ�˴ϴ�.");
					System.out.println("�����մϴ� ������ �� �̿����ּ���");
					System.out.println("=========================");
					return;
				}
			}



			if (drinkData[0][choice-1] == 0) { // ��� ���� �� ������ �ȉ�
				System.out.println(drinkName[choice-1]+"�� ��� �����ؿ�.. �ٸ������� ��Ź����� �ɱ��?");
				continue;
			}
			else{
				// ����ڰ� ������ ����� �̸��� ���
				System.out.print('"'+drinkName[choice-1]+'"'+" ���Խ��ϴ�~~ �������ּ���! ");
				coin -= drinkData[1][choice-1]; //�ش� ����� ���� ����
				drinkData[0][choice-1]--; // �ش� ����� ���� ����
				System.out.println("������ ���� �ݾ� : "+coin);
			}


			System.out.println();
			System.out.println("=========================");
			System.out.println("���� �Ƹ� : ��� �Ͻðڽ��ϱ�?");
			System.out.println(" 1.YES    2.NO ");
			System.out.println("================");

			do {		//���� ��ȿ���˻�
				isExit = sc.nextInt();
				if(isExit < 1 || isExit>2) {
					System.out.println("���Է�");
				}
			}while(isExit < 1 || isExit>2);

			switch (isExit) {
			case 1:	
				while(coin <minPrice) {

					System.out.print("�߰��� ����� �Է��ϼ��� : ");
					int addMoney = sc.nextInt(); //����ڰ� �߰��� ���� �Է���
					coin += addMoney;

					if (coin < minPrice) { 
						System.out.println("�ܾ��� "+minPrice+"��� �̻��̾���մϴ�.");
					}
				}
				break;


			case 2: //����
				System.out.println(coin + "��� ��ȯ�˴ϴ�.");
				System.out.println("�����մϴ� ������ �� �̿����ּ���");
				System.out.println("=========================");
				return;
			}


			System.out.println("=========================");	

		}




	}
}

