$(document).ready(function() {
	var aNum = 1;	/*성인*/
	var cNum = 0;	/*미성년*/

	/* 클릭시 메인으로 이동 */
	$("#logo").click(function() {
		window.location = "main.html";
	});

	/* 왕복 편도 선택 라디오버튼 */
	$('input[name="tripType"]').change(function() {
		var flag = $('input[name=tripType]:checked').val();
		if (flag == 'RT') { 		/* 왕복선택시 block */
			$(".arrivalDate").css("display", "block");
		} else { 					/* 편도 선택시 none */
			$(".arrivalDate").css("display", "none");
			$("#adinfo").text("mm/dd/yyyy");

		}
	})

	/* 메뉴 토글클래스 */
	$(".menu1").click(function() {
		/* $("li").removeClass("on");  이렇게 할경우 토글클래스가 먹히지 않는다*/
		/*메뉴를 클릭시 on class가 추가되어 display가 none 에서 block이 된다*/
		$(".tnum > li").toggleClass("on");
		$(".dloc > li").removeClass("on");
		$(".aloc > li").removeClass("on");
		$(".ddate > li").removeClass("on");
		$(".adate > li").removeClass("on");
	});

	$(".menu2").click(function() {
		$(".tnum > li").removeClass("on");
		$(".dloc > li").toggleClass("on");
		$(".aloc > li").removeClass("on");
		$(".ddate > li").removeClass("on");
		$(".adate > li").removeClass("on");
	});

	$(".menu3").click(function() {
		$(".tnum > li").removeClass("on");
		$(".dloc > li").removeClass("on");
		$(".aloc > li").toggleClass("on");
		$(".ddate > li").removeClass("on");
		$(".adate > li").removeClass("on");
	});

	$(".menu4").click(function() {

		$(".tnum > li").removeClass("on");
		$(".dloc > li").removeClass("on");
		$(".aloc > li").removeClass("on");
		$(".ddate > li").toggleClass("on");
		$(".adate > li").removeClass("on");

	});

	$(".menu5").click(function() {
		$(".tnum > li").removeClass("on");
		$(".dloc > li").removeClass("on");
		$(".aloc > li").removeClass("on");
		$(".ddate > li").removeClass("on");
		$(".adate > li").toggleClass("on");
	});

	/* 인원수 설정 */
	$(".adult > .minus").click(function() { /*성인 -*/
		if (aNum > 1) {
			aNum--;
		} else {
			aNum = 0;
		}
		$(".adult > .num").text(aNum); /* 인원수 출력 */
		$("#ninfo1").text(aNum);
	});

	$(".adult > .plus").click(function() { /*성인 +*/
		aNum++;
		$(".adult > .num").text(aNum);
		$("#ninfo1").text(aNum);
	});

	$(".child > .minus").click(function() { /*미성년 -*/
		if (cNum > 1) {
			cNum--;
		} else {
			cNum = 0;
		}
		$(".child > .num").text(cNum); /* 인원수 출력 */
		$("#ninfo2").text(cNum);
	});

	$(".child > .plus").click(function() { /*미성년 +*/
		cNum++;
		$(".child > .num").text(cNum);
		$("#ninfo2").text(cNum);
	});

	$(".dloc > li > a").click(function() { /* 출발지 클릭시 예약에 출력 */
		$("#dinfo").text($(this).html());
	});

	$(".aloc > li > a").click(function() { /* 도착지 클릭시 예약에 출력 */
		$("#ainfo").text($(this).html());
	});

	$(".aloc > li > a").click(function() { /* 도착지 클릭시 예약에 출력 */
		$("#ainfo").text($(this).html());
	});

	$("#datepicker1").datepicker({			/*가는날에서 날짜 선택시 예약정보에 출력*/
		onSelect : function(date) {
			$("#ddinfo").text(date);
		}
	});
	
	$("#datepicker2").datepicker({			/*오는날에서 날짜 선택시 예약정보에 출력*/
		onSelect : function(date) {
			$("#adinfo").text(date);

		}
	});
});