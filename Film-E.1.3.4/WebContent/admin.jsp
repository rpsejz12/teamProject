<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>    
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Anime Template">
    <meta name="keywords" content="Anime, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Film-E | Admin</title>

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
                                        <li><a href="categories.do?mtype=액션">Action</a></li>
                                        <li><a href="categories.do?mtype=애니메이션">Animation</a></li>
                                        <li><a href="categories.do?mtype=멜로/로맨스">Melo/Romance</a></li><!-- 컨트롤과 맞추기 -->
                                        <li><a href="categories.do?mtype=드라마">Drama</a></li>
                                        <li><a href="categories.do?mtype=다큐멘터리">Documentary</a></li>
                                        <li><a href="categories.do?mtype=ETC">ETC</a></li>
                                    </ul>
                                </li>
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
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="main.do"><i class="fa fa-home"></i> Home</a>
                        <a href="categories.do?mtype=${mtype}">Categories</a><!-- 카테고리별로 넘기기 생각 -->
                        <span>${mtype}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->

    <!-- Anime Section Begin -->
    <section class="anime-details spad">
        <div class="container">
            <div class="anime__details__content">
                <div class="row">
                    <div class="col-lg-9">
                         <div class="blog__details__form">
                                    <div class="row">
                                    	<mytag:movie title="${title}"/>
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
                <form class="search-model-form" action="adminlist.do">
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