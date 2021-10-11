<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>
<!DOCTYPE html>
<html lang="zxx">
<link rel="stylesheet" href="css/rating.css">
<script type="text/javascript">
	function Rinsert() {
		if (confirm("리뷰를 작성하시겠습니까?") == true) {
			document.review.submit();
		} else {
			return;
		}
	}
	function Rdelete() {
		return confirm("리뷰를 삭제하시겠습니까?");
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
			<div class="row">
				<div class="col-lg-2">
					<div class="header__logo">
						<a href="Main.do"> <img src="img/logo.png" alt="logo확인">
						</a>
					</div>
				</div>
				<div class="col-lg-8">
					<div class="header__nav">
						<nav class="header__menu mobile-menu">
							<ul>
								<li class="active"><a href="Main.do">Homepage</a></li>
								<li><a href="Categories.do">Categories <span
										class="arrow_carrot-down"></span></a>
									<ul class="dropdown">
										<li><a href="Categories.do?mtype=액션">Action</a></li>
										<li><a href="Categories.do?mtype=애니메이션">Animation</a></li>
										<li><a href="Categories.do?mtype=멜로/로맨스">Melo/Romance</a></li>
										<!-- 컨트롤과 맞추기 -->
										<li><a href="Categories.do?mtype=드라마">Drama</a></li>
										<li><a href="Categories.do?mtype=다큐멘터리">Documentary</a></li>
										<li><a href="Categories.do?mtype=ETC">ETC</a></li>
									</ul>
								<li><a href="project.jsp">Project</a></li>
								<li><a href="aboutus.jsp">AboutUs</a></li>
							</ul>
						</nav>
					</div>
				</div>
				<div class="col-lg-1">
					<div class="header__right">
						<a href="#" class="search-switch"><span class="icon_search"></span></a>
					</div>
				</div>
				<mytag:log />
			</div>
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
								<h3>${mdata.mtype}</h3><h3>별점:☆☆☆☆☆</h3>
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
							<div class="section-title">
								<h5>Your Comment</h5>
							</div>
							<c:choose>
								<c:when test="${empty sessionID}">
									<form action="login.jsp">
										<textarea readonly placeholder="Please Log In"></textarea>
										<button type="submit">
											<i class="fa fa-location-arrow"></i> Log In
										</button>
									</form>
								</c:when>
								<c:otherwise>
									<form action="Rinsert.do" method="post" name="review" id="myform" style=" user-select: none;">
										<input type="hidden" name="mpk" value="${mdata.mpk}">
										<textarea name="cmt" placeholder="Your Comment"></textarea>
										<fieldset>
									        <input type="radio" name="rating" value="5" id="rate1"><label for="rate1">⭐</label>
									        <input type="radio" name="rating" value="4" id="rate2"><label for="rate2">⭐</label>
									        <input type="radio" name="rating" value="3" id="rate3"><label for="rate3">⭐</label>
									        <input type="radio" name="rating" value="2" id="rate4"><label for="rate4">⭐</label>
									        <input type="radio" name="rating" value="1" id="rate5"><label for="rate5">⭐</label>
									    </fieldset>
									    <br>
										<button type="button" class="fa fa-location-arrow"
											onclick="Rinsert()">Review</button>
									</form>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<br>

					<div class="col-lg-12 col-md-12">
						<div class="anime__details__review">
							<div class="section-title">
								<h5>Reviews</h5>
							</div>
							<c:forEach var="v" items="${datas}">
								<div class="anime__review__item">
									<div class="anime__review__item__text">
										<!-- 주석지우기 -->
										<h6>${v.id}
											- <span>${v.rdate}&nbsp</span>&nbsp별점:☆☆☆☆☆
											<mytag:delete rpk="${v.rpk}" id="${v.id}" mpk="${v.mpk}" />
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
							<h5>how about this movie ?</h5>
						</div>
						<div class="anime__details__pic set-bg"
							data-setbg="${mrand.filename}"></div>
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
						<a href="./index.html"><img src="img/logo.png" alt=""></a>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="footer__nav">
						<ul>
							<li class="active"><a href="./index.html">Homepage</a></li>
							<li><a href="./categories.html">Categories</a></li>
							<li><a href="./blog.html">Our Blog</a></li>
							<li><a href="#">Contacts</a></li>
						</ul>
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
			<form class="search-model-form">
				<input type="text" id="search-input" placeholder="Search here.....">
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