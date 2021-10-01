<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Anime Template">
    <meta name="keywords" content="Anime, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Film-E | Review</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800;900&display=swap"
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
                        <a href="main.do">
                            <img src="img/logo.png" alt="logo확인">
                        </a>
                    </div>
                </div>
                <div class="col-lg-8">
					<div class="header__nav">
						<nav class="header__menu mobile-menu">
							<ul>
								<li class="active"><a href="main.do">Homepage</a></li>
								<li><a href="categories.do">Categories <span class="arrow_carrot-down"></span></a>
									 <ul class="dropdown">
                                        <li><a href="categories.do?mtype=ACTION">Action</a></li>
                                        <li><a href="categories.do?mtype=ANIMATION">Animation</a></li>
                                        <li><a href="categories.do?mtype=HT">Horror·Thriller</a></li><!-- 컨트롤과 맞추기 -->
                                        <li><a href="categories.do?mtype=ROMANCE">Romance</a></li>
                                        <li><a href="categories.do?mtype=SF">Sf·Fantasy</a></li>
                                    </ul>
								<li><a href="project.do">Project</a></li>
                                <li><a href="aboutus.jsp">AboutUs</a></li>
							</ul>
						</nav>
					</div>
				</div>
                <div class="col-lg-1">
					<div class="header__right">
						<a href="#" class="search-switch"><span class="icon_search" ></span></a>
					</div>
				</div>
				<mytag:log/>
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
                <div class="row">
                    <div class="col-lg-3">
                        <div class="anime__details__pic set-bg" data-setbg="img/${filename}">
                        </div>
                    </div>
                    <div class="col-lg-9">
                        <div class="anime__details__text">
                            <div class="anime__details__title">
                                <h3>${title}제목</h3>
                                <span>${mtype}장르  / Date:${date}날짜</span>
                            </div>
                            <p>${content}내용</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="anime__details__form">
                	<div class="section-title">
                    	<h5>Your Comment</h5>
                    </div>
                    <form action="r_insert.do" method="post">
                    	<textarea placeholder="Your Comment"></textarea>
                        <button type="submit"><i class="fa fa-location-arrow"></i> Review</button>
                    </form>
                </div>
                <br>
                <div class="row">
                    <div class="col-lg-12 col-md-12">
                        <div class="anime__details__review">
                            <div class="section-title">
                                <h5>Reviews</h5>
                            </div>
                            <div class="anime__review__item">
                                <div class="anime__review__item__text">
                                	<%-- <c:forEach var="v" items="datas">
                                	<!-- 주석지우기 -->
                                    <h6>${v.id}아이디<span>${v.date}시간</span><mytag:delete rpk="${v.rpk}" id="${v.id}"/></h6>
                                    <p>${v.comment}코멘트</p>
                                    </c:forEach> --%>
                                </div>
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
                            <a href="main.do"><img src="img/logo.png" alt="logo확인"></a>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                          Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                          <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>

                      </div>
                  </div>
              </div>
          </footer>
          <!-- Footer Section End -->

          <!-- Search model Begin -->
          <div class="search-model">
            <div class="h-100 d-flex align-items-center justify-content-center">
                <div class="search-close-switch"><i class="icon_close"></i></div>
                <form class="search-model-form" action="categories.do">
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