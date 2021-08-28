$.ajax({
	type : "GET",
	url : "locate.json",
	dataType : "json",
	success : function(data) { // data == data1.json 전체 데이터
		// 화면에 데이터 내용을 출력
		var str = "";
		$.each(data, function(index, obj) {
			str += "<li>";
			str += "<a href='" + obj.link + "'>";
			str += obj.locate;
			str += "</a></li>";
		});
		$(".dloc").append(str);
		$(".aloc").append(str);
	},
	error : function() {
		alert("에러발생!");
	}
});