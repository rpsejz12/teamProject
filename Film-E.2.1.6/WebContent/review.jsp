<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>
<!DOCTYPE html>
<html lang="zxx">
<link rel="stylesheet" href="css/rating.css">
 <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script type="text/javascript">
$(document).ready(function() {
    $('#test').on('keyup', function() {
        $('#test_cnt').html("("+$(this).val().length+" / 1000)");

        if($(this).val().length > 1000) {
            $(this).val($(this).val().substring(0, 1000));
            $('#test_cnt').html("(1000 / 1000)");
        }
    });
});
	function Rinsert() {
		if (confirm("리뷰를 작성하시겠습니까?") == true) {
			document.review.submit();
		} else {
			return;
		}
	}
	function Rdelete() {
		if (confirm("리뷰를 삭제하시겠습니까?") == true) {
			location.href = "Rdelete.do?&rpk=${data.rpk}&mpk=${data.mpk}";
		} else {
			return;
		}
	}
	function logout() {
		if (confirm("정말로 로그아웃 하시겠습니까?") == true) {
			location.href = "Logout.do";
		} else {
			return;
		}
	}
</script>
<script type="text/javascript">
	
</script>
<head>
<meta charset="UTF-8">
<meta name="description" content="Anime Template">
<meta name="keywords" content="Anime, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Film-E | Review</title>
<link rel="Film-E icon" href="img/main.ico">

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/plyr.css" type="text/css">
<link rel="stylesheet" href="css/nice-select.css" type="text/css">
<link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Header Section Begin -->
	<header class="header">
		<div class="container">
			<mytag:categories />
			<div id="mobile-menu-wrap"></div>
		</div>
	</header>
	<!-- Header End -->

	<!-- Breadcrumb Begin -->
	<!-- Breadcrumb End -->

	<!-- Anime Section Begin -->
	<section class="anime-details spad">
		<div class="container">
			<div class="anime__details__content">
				<div class="col-lg-12 col-md-8 col-sm-8">
					<div class="section-title">
						<br>
						<h4>영화 리뷰</h4>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-4">
						<div class="anime__details__pic set-bg"
							data-setbg="${mdata.filename}"></div>
					</div>
					<div class="col-lg-8">
						<div class="anime__details__text">
							<div class="anime__details__title">
								<h2 style="color: #FFFFFF; font-size: xxx-large;">${mdata.title}</h2>
								<br>
								<h3>${mdata.mtype}</h3>

								<h3 style="color: #ffc107;">
									<c:if test="${mdata.ratingavg < 0.5}">
                                 ☆☆☆☆☆
                                 </c:if>


									<c:if test="${mdata.ratingavg >= 0.5 && mdata.ratingavg < 1.5}">
                                 ★☆☆☆☆
                                 </c:if>

									<c:if test="${mdata.ratingavg >= 1.5 && mdata.ratingavg < 2.5}">
                                ★★☆☆☆
                                 </c:if>

									<c:if test="${mdata.ratingavg >= 2.5 && mdata.ratingavg < 3.5}">
                           		★★★☆☆
                                 </c:if>

									<c:if test="${mdata.ratingavg >= 3.5 && mdata.ratingavg < 4.5}">
                                ★★★★☆
                                 </c:if>

									<c:if test="${mdata.ratingavg >= 4.5 && mdata.ratingavg <= 5}">
                                 ★★★★★
                                 </c:if>

									&nbsp${mdata.ratingavg}&nbsp/&nbsp5.0&nbsp
								</h3>

								<h3>개봉일 : ${mdata.mdate}</h3>
							</div>
							<p>${mdata.content}</p>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-8">
					<div class="col-lg-12 col-md-12">
						<div class="anime__details__form">
							<c:choose>
								<c:when test="${empty sessionID}">
									<div class="section-title">
										<h5>내 댓글</h5>
										<form action="login.jsp" style="margin-top: 30px;">
											<textarea readonly placeholder="Please Log In"></textarea>
											<button type="submit">
												<i class="fa fa-location-arrow"></i> Log In
											</button>
										</form>
									</div>
								</c:when>
								<c:when test="${data.id != sessionID}">
									<div class="section-title">
										<h5 style="float: left;">내 댓글</h5>
										<form action="Rinsert.do" method="post" name="review"
											id="myform" style="user-select: none;">
											<fieldset style="float: right;">
												<input type="radio" name="rating" value="5" id="rate1">
												<label for="rate1">⭐</label> <input type="radio"
													name="rating" value="4" id="rate2"> <label
													for="rate2">⭐</label> <input type="radio" name="rating"
													value="3" id="rate3" checked> <label for="rate3">⭐</label>
												<input type="radio" name="rating" value="2" id="rate4">
												<label for="rate4">⭐</label> <input type="radio"
													name="rating" value="1" id="rate5"> <label
													for="rate5">⭐</label>
											</fieldset>
											<input type="hidden" name="mpk" value="${mdata.mpk}">
											<textarea id="test" name="cmt" cols="30" rows="10" placeholder="Your Comment" required></textarea>
    										<div id="test_cnt" style="color: #FFFFFF;">(0 / 1000)</div>
											<br>
											<button type="button" class="fa fa-location-arrow"
												onclick="Rinsert()">Review</button>
										</form>
									</div>
								</c:when>
								<c:otherwise>
									<div class="section-title">
										<h5>내 댓글</h5>
										<div class="anime__review__item" style="margin-top: 25px;">
											<div class="anime__review__item__text">
												<!-- 주석지우기 -->
												<h6>${sessionID}
													- <span>${data.rdate}&nbsp</span> <span
														style="color: #ffc107;"> <c:if
															test="${v.rating== 0}">
                                 ☆☆☆☆☆
                                 </c:if> <c:if test="${data.rating== 1}">
                                 ★☆☆☆☆
                                 </c:if> <c:if test="${data.rating  == 2}">
                                ★★☆☆☆
                                 </c:if> <c:if test="${data.rating  == 3}">
                                 ★★★☆☆
                                 </c:if> <c:if test="${data.rating  == 4}">
                                 ★★★★☆
                                 </c:if> <c:if test="${data.rating  == 5}">
                                 ★★★★★
                                 </c:if>
													</span>
													<button type="button" onclick="Rdelete()" style="color: #007bff;background: #1d1e39;outline: 0;border: 0;">Delete</button>
												</h6>
												<p style="overflow-wrap: break-word;">${data.cmt}</p>
											</div>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<br>

					<div class="col-lg-12 col-md-12">
						<div class="anime__details__review">
							<div class="section-title">
								<h5>댓글</h5>
							</div>
							<c:forEach var="v" items="${datas}">
								<div class="anime__review__item">
									<div class="anime__review__item__text">
										<!-- 주석지우기 -->
										<h6>${v.id}
											- <span>${v.rdate}&nbsp</span> <span style="color: #ffc107;">
												<c:if test="${v.rating== 0}">
                                ☆☆☆☆☆
                                 </c:if> <c:if test="${v.rating== 1}">
                                 ★☆☆☆☆
                                 </c:if> <c:if test="${v.rating  == 2}">
                                 ★★☆☆☆
                                 </c:if> <c:if test="${v.rating  == 3}">
                                ★★★☆☆
                                 </c:if> <c:if test="${v.rating  == 4}">
                                ★★★★☆
                                 </c:if> <c:if test="${v.rating  == 5}">
                               ★★★★★
                                 </c:if>
											</span>
										</h6>
										<p style="overflow-wrap: break-word;">${v.cmt}</p>
									</div>
								</div>
							</c:forEach>
						</div>
						<div class="product__pagination">
							<a href="RselectAll.do?page=1&mpk=${mdata.mpk}"><i
								class="fa fa-angle-double-left"></i></a>
							<!-- 처음으로 -->
							<c:if test="${paging.startPage != 1 }">
								<a
									href="RselectAll.do?page=${(page-1)-(page-1)%paging.perPagSet - paging.perPageSet + 1}&mpk=${mdata.mpk}"><i
									class="fa fa-angle-left"></i></a>
								<!-- 이전페이지 -->
							</c:if>
							<c:forEach begin="${paging.startPage}" end="${paging.endPage}"
								var="p">
								<c:if test="${paging.curPage == p}">
									<a href="RselectAll.do?page=${p}&mpk=${mdata.mpk}"
										class="current-page">${p}</a>
								</c:if>
								<c:if test="${paging.curPage != p}">
									<a href="RselectAll.do?page=${p}&mpk=${mdata.mpk}">${p}</a>
								</c:if>
							</c:forEach>
							<c:if test="${paging.endPage != paging.lastPage}">
								<a
									href="RselectAll.do?page=${(page-1)-(page-1)%paging.perPageSet + paging.perPageSet + 1}&mpk=${mdata.mpk}"><i
									class="fa fa-angle-right"></i></a>
								<!-- 다음페이지 -->
							</c:if>
							<a href="RselectAll.do?page=${paging.lastPage}&mpk=${mdata.mpk}"><i
								class="fa fa-angle-double-right"></i></a>
							<!-- 마지막으로 -->
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-4">
					<div class="anime__details__sidebar">
						<div class="section-title">
							<h5>이런 영화는 어때요?</h5>
						</div>
						<div class="anime__details__pic set-bg"
							data-setbg="${mrand.filename}" style="cursor: pointer;"
							onclick="location.href='RselectAll.do?mpk=${mrand.mpk}'"></div>
						<div class="product__item__text">
							<h5>
								<a href="RselectAll.do?mpk=${mrand.mpk}">${mrand.title}</a>
							</h5>
						</div>
					</div>
				</div>
			</div>



		</div>
	</section>
	<!-- Anime Section End -->

	<!-- Footer Section Begin -->
	<footer class="footer">
		<div class="page-up">
			<a href="#" id="scrollToTopButton"><span class="arrow_carrot-up"></span></a>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="footer__logo">
						<a href="./index.html"><img src="img/logo.png" alt="logo확인"></a>
					</div>
				</div>
				<div class="col-lg-3">
					<p>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						Copyright &copy;
						<script>
							document.write(new Date().getFullYear());
						</script>
						All rights reserved | This template is made with <i
							class="fa fa-heart" aria-hidden="true"></i> by <a
							href="https://colorlib.com" target="_blank">Colorlib</a>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					</p>

				</div>
			</div>
		</div>
	</footer>
	<!-- Footer Section End -->

	<!-- Search model Begin -->
	<div class="search-model">
		<div class="h-100 d-flex align-items-center justify-content-center">
			<div class="search-close-switch">
				<i class="icon_close"></i>
			</div>
			<form class="search-model-form" action="Categories.do">
				<input type="text" id="search-input" name="search" placeholder="영화 제목 검색.....">
			</form>
		</div>
	</div>
	<!-- Search model end -->

	<!-- Js Plugins -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/player.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/mixitup.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>

</body>

</html>