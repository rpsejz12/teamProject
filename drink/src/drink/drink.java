package drink;

import java.util.Scanner;


public class drink{

	public static void menuChange(String[] drinkName, int[][] drinkData) { //매뉴의 이름을 변경해주는 함수
		int msChoice; //관리자가 업무 번호를 선택할 변수;
		while(true) { // 종료조건 작성해야함
			Scanner sc = new Scanner(System.in);


			System.out.println("★☆★☆★☆관리자 모드★☆★☆★☆");
			System.out.println("1. ======매뉴를 변경======");
			System.out.println("2. =====매뉴가격 변경=====");
			System.out.println("3. =======재고 추가=======");
			System.out.println("4. =======데이터 확인=======");
			System.out.println("5. =======종료=======");
			System.out.println("==원하는 업무의 번호를 입력하세요==");

			do {
				msChoice = sc.nextInt();
				if(msChoice < 1 || msChoice > 5) {
					System.out.println("재입력");
				}
			}while(msChoice < 1 || msChoice > 5);

			// 메뉴변경 모드를 실행했을때
			switch(msChoice) {
			case 1: //메뉴이름 변경하기! msChoice == 1

				//  DRINKNAME(음료 이름) 배열을 출력
				System.out.println("==========================");
				System.out.println("★어떤 매뉴의 이름을 변경하시겠습니까?★");
				for (int i = 0; i < drinkName.length; i++) { 
					System.out.print((i+1)+"."+drinkName[i]+" ");
				}
				//변경 음료의 이름 입력받기
				System.out.println();

				int changeName; //변경할 매뉴를 선택하는 변수
				while (true) { //매뉴의 번호가 없을때 유효성 검사
					changeName = sc.nextInt();
					if (changeName < 1 || changeName > drinkName.length) {
						System.out.print("============================");
						System.out.print("해당 매뉴는 없습니다 다시 입력해주세요 : ");
						continue;
					}
					break;
				}

				// 입력받을 매뉴 출력 및 수정
				System.out.println("변경할 이름을 작성하세요 : ");
				drinkName[changeName-1] = sc.next();
				System.out.print("\""+drinkName[changeName-1]+"\""+"로 변경완료");

				System.out.println();
				break;

			case 2:  //가격 변동 msChoice == 1
				// 매뉴의 이름을 출력
				System.out.println("==========================");
				System.out.println("★어떤 매뉴의 가격을 수정하시겠습니까?★");
				System.out.println("===========현재가격==========");
				for (int i = 0; i < drinkName.length; i++) { 
					System.out.print((i+1)+"."+drinkName[i]+drinkData[1][i]+"원 ");
				}
				// 변경 음료의 번호를 입력받기
				System.out.println();
				int changePrice; // 변경받을 번호를 입력받는 위치

				while(true) { // 선택한 번호의 유효성검사
					changePrice = sc.nextInt();
					if (changePrice < 1 || changePrice > drinkName.length) {
						System.out.println("==========================");
						System.out.print("해당 매뉴는 없습니다 다시 입력하세요 : ");
						continue;
					}
					break;
				}

				// 사용자가 고른 매뉴의 가격 변경
				System.out.print("변경할 가격을 입력하세요 : ");
				int putPrice;
				// 가격입력 0원 이하일 떄 유효성 검사
				while (true) {
					putPrice = sc.nextInt();
					if (putPrice <= 0) {
						System.out.print("0원 이하로 입력하실 수 없습니다 다시 입력하세요 : ");
						continue;
					}
					break;
				}

				drinkData[1][changePrice-1] = putPrice;
				System.out.print("\""+drinkData[1][changePrice-1]+"\""+"원으로 변경완료");
				System.out.println();
				break;

			case 3:  //재고 추가 입력 msChoice == 1
				// 매뉴의 이름을 출력
				System.out.println("★어떤 매뉴의 재고를 추가하시겠습니까?★");
				System.out.println("==========================");
				System.out.println("==========현재 재고===========");
				for (int i = 0; i < drinkName.length; i++) {
					System.out.print((i+1)+"."+drinkName[i]+drinkData[0][i]+"개 ");
				}

				// 변경 음료의 재고를 입력받기
				System.out.println();
				int changeStock;
				while(true) { // 매뉴 유효성 검사
					changeStock = sc.nextInt();
					if (changeStock < 0 || changeStock > drinkName.length) {
						System.out.print("==========================");
						System.out.print("해당 매뉴는 없습니다 다시 입력하세요 : ");
						continue;
					}
					break;
				}

				// 사용자가 고른 매뉴의 재고 변경
				System.out.print("재고의 개수를 입력하세요 : ");
				//재고를 0개 미만으로 입력시 재입력
				int putStock;
				while(true) {
					putStock = sc.nextInt();
					if (putStock < 0) {
						System.out.print("=================================");
						System.out.print("0개 미만으로 입력하실 수 없습니다 다시 입력하세요 :");
						continue;
					}
					break;
				}

				drinkData[0][changeStock-1] += putStock;
				System.out.print("총 \""+drinkData[0][changeStock-1]+"\""+"개로 추가완료");
				System.out.println();

				break;
				
			case 4:		//데이터 확인
				for(int i = 0 ; i < drinkName.length; i ++) {
					System.out.println(drinkName[i] + ": 재고[" + drinkData[0][i] + "] 가격  [" + drinkData[1][i] + "]"  );
				}
				break;
				
			case 5:		//종료
				return;


			}
		}
	}


	static final int MAX_MANU = 10;	//메뉴의 최대값 상수로 선언
	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		System.out.println("점원 아리 : 어서오세요^-^ 소환사의 자판기에 오신것을 환영합니다!");
		System.out.println();

		String[] drinkName; 	//음료수 이름 배열
		String[] drinkNameSp;	//초기 음료수 이름 배열
		int[][] drinkData; 		//음료수 개수,가격 배열
		int[][] drinkDataSp;	//초기 음료수 개수,가격 배열
		int coin = 0; 		//입력한 돈의 누적액
		int minPrice;			//최저가 음료 변수	
		int choice;				//음료 선택
		int isExit;	//탈출

		drinkName = new String[MAX_MANU];
		drinkData = new int[2][MAX_MANU];

		drinkNameSp = new String[] {"오른의 수제커피","블루주스","레드콜라","몬스터 액기스"}; // 초기메뉴
		drinkDataSp = new int[][] {{5,5,5,5},{5000,1000,1500,1200}};				//초기메뉴의 재고,가격

		for(int i = 0 ; i < drinkNameSp.length; i++) {		//초기 메뉴이름을 i개 메뉴배열에 삽입
			drinkName[i] = drinkNameSp[i];
			for(int j = 0 ; j < drinkDataSp.length; j++) { //i메뉴의 가격, j번 반복(2) 재고 삽입
				drinkData[j][i] = drinkDataSp[j][i];	
			}
		}

		minPrice = drinkData[1][0];							//drinkData[0][0]를 최소값으로 가정
		for(int i = 0 ; i < drinkData[1].length;i++) {
			if(drinkData[1][i] == 0) {						//가격이 0이면 중지 drinkData에는 저장된배열이외에는 0으로 초기화되어있음
				break;
			}
			if(minPrice > drinkData[1][i]) {				//최저가 탐색 로직
				minPrice = drinkData[1][i];
			}

		}


		System.out.println("자자 걱정말고 이제 주문을 해주겠나?");
		System.out.println("=========================================================================");

		for (int i = 0; i < drinkName.length; i++) {		// 매뉴를 출력 해주는 반복문
			if(drinkName[i]!=null) {	//drinkName[i]가 null값이 아닐때만 메뉴 출력
				System.out.print("["+(i+1)+"."+drinkName[i]+" "+drinkData[1][i]+"골드 ]");
			}
		}
		System.out.println();
		System.out.println("=========================================================================");

		System.out.println("사장 오른 : 주머니도 두둑하신 우리 손놈 아니 손님");
		System.out.println("사장 오른 : \"이제 돈 내놓게\"");
		System.out.println("드... 드리겠습니다...");

		while (true) { 	//동전 투입	
			System.out.print("(System) 돈을 카운터에 올려놓으세요 : ");
			do {
				coin += sc.nextInt(); 
				if(coin == -3333) {
					break;
				}
				if(coin<0) {
					System.out.println("재투입 :");
					coin = 0;
				}
				
			}while(coin<=0);
			
			//코인이 -3333 이거나 코인이 0보다 크면 반복중지
			
			
			if(coin == -3333) {
				menuChange(drinkName,drinkData);
				coin = 0;
				for (int i = 0; i < drinkName.length; i++) {
					if(drinkName[i] != null) {	//drinkName[i]가 null값이 아닐때만 메뉴 출력
						System.out.print((i+1)+"."+drinkName[i]+drinkData[1][i]+"골드  ");
					}
				}

			}

			if (coin < minPrice) { //누적금액이 최저가보다 작으면 추가입력 while의 첫부분으로 이동
				System.out.println("이봐 골드가 부족하잖아!" + minPrice + "골드 정돈 올리라고!!");
				System.out.println("★현재 잔액 : "+coin);
				continue; 
			}
			else { 		//최저가 이상 입력시 반복중단
				System.out.println("★현재 잔액 : "+coin);
				break;
			}
		}
		while(true) { 
			for (int i = 0; i < drinkName.length; i++) {
				if(drinkName[i] != null) {	//drinkName[i]가 null값이 아닐때만 메뉴 출력
					System.out.print((i+1)+"."+drinkName[i]+drinkData[1][i]+"골드  ");
				}
			}

			System.out.println();
			System.out.print("점원 아리 : 선택하실 음료수의 번호를 입력해주세요 : ");

			do {	//음료 선택 유효성 검사
				choice = sc.nextInt();
				if (choice < 0+1 || choice > drinkName.length || drinkName[choice-1] == null ) { //존재하지 않는 음료일때
					System.out.println(choice+"그런 음료수는 없어요... 다른 것을 주문해줘요");
					System.out.println("=========================");
				}
			}while( choice < 0+1 || choice > drinkName.length || drinkName[choice-1] == null );

			if (coin < drinkData[1][choice-1]) { //돈이 음료가격보다 적을때
				System.out.println("주신돈이 부족한데요?");
				System.out.println("=========================");
				System.out.println("골드를 추가하시겠습니까?");
				System.out.println(" 1.YES    2.NO ");
				do {	//골드추가 할지 유효성 검사
					isExit = sc.nextInt();
					if(isExit < 1 || isExit>2) {
						System.out.println("재입력");
					}
				}while(isExit < 1 || isExit>2);

				if(isExit == 1) {
					System.out.print("추가할 골드을 입력하세요 : ");
					int addMoney;
					do {	//골드추가 유효성 검사 0미만 입력되면 다시
						addMoney = sc.nextInt();
					}while(addMoney < 0);//사용자가 추가할 돈을 입력함
					coin += addMoney;
					continue;
				}

				else if(isExit == 2){
					System.out.println(coin + "골드 반환됩니다.");
					System.out.println("감사합니다 다음에 또 이용해주세요");
					System.out.println("=========================");
					return;
				}
			}



			if (drinkData[0][choice-1] == 0) { // 재고가 없을 때 차감이 안됌
				System.out.println(drinkName[choice-1]+"음 재고가 부족해요.. 다른것으로 부탁드려도 될까요?");
				continue;
			}
			else{
				// 사용자가 선택한 음료수 이름을 출력
				System.out.print('"'+drinkName[choice-1]+'"'+" 나왔습니다~~ 가져가주세요! ");
				coin -= drinkData[1][choice-1]; //해당 음료수 가격 차감
				drinkData[0][choice-1]--; // 해당 음료수 개수 차감
				System.out.println("★현재 남은 금액 : "+coin);
			}


			System.out.println();
			System.out.println("=========================");
			System.out.println("점원 아리 : 계속 하시겠습니까?");
			System.out.println(" 1.YES    2.NO ");
			System.out.println("================");

			do {		//종료 유효성검사
				isExit = sc.nextInt();
				if(isExit < 1 || isExit>2) {
					System.out.println("재입력");
				}
			}while(isExit < 1 || isExit>2);

			switch (isExit) {
			case 1:	
				while(coin <minPrice) {

					System.out.print("추가할 골드을 입력하세요 : ");
					int addMoney = sc.nextInt(); //사용자가 추가할 돈을 입력함
					coin += addMoney;

					if (coin < minPrice) { 
						System.out.println("잔액이 "+minPrice+"골드 이상이어야합니다.");
					}
				}
				break;


			case 2: //종료
				System.out.println(coin + "골드 반환됩니다.");
				System.out.println("감사합니다 다음에 또 이용해주세요");
				System.out.println("=========================");
				return;
			}


			System.out.println("=========================");	

		}




	}
}

