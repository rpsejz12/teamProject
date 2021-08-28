$(document).ready(function() {
	$(".logo").on("mouseover", function() {			/*마우스오버시 img크기 증가*/
		$(".logo").addClass("on");
	})

	$(".logo").on("mouseleave", function() {		/*마우스리브시 img크기 감소*/
		$(".logo").removeClass("on");
	})

	$(".logo").click(function() {					/*마우스클릭*/
		$(".logo").delay(100).animate({				/*위로 가면서 사라지는 애니메이션*/
			opacity : 0,
			top : '0px',
			height : '250px',
			width : '250px'
		}, 200, "swing");

		window.setInterval(function(){				/*대기시간 이후 페이지 이동*/
			window.location = "main.html";
		},1000);

	});
});