<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>
<!DOCTYPE html>
<html lang="zxx">
<script type="text/javascript">
   function logout(){
      if(confirm("정말로 로그아웃 하시겠습니까?")==true){
         location.href="Logout.do";      
      }
      else{
         return;
      }
   }
</script>
<head>
<meta charset="UTF-8">
<meta name="description" content="Anime Template">
<meta name="keywords" content="Anime, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Anime | AboutUs</title>

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
			<mytag:aboutus />
			<div id="mobile-menu-wrap"></div>
		</div>
	</header>
   <!-- Header End -->

   <!-- 멤버 소개 -->
   <section class="anime-details spad" style="padding-top: 65px">
      <div class="container">
         <div class="anime__details__content">
            <div class="col-lg-12 col-md-8 col-sm-8">
               <br>
               <div class="section-title">
                  <h4>조원 소개</h4>
               </div>
            </div>
            <div class="row">
               <div class="col-lg-3">
                  <div style="height: 350px" class="anime__details__pic set-bg" data-setbg="img/gm.jpg">
                  </div>
               </div>
               <div class="col-lg-9">
                  <div class="anime__details__text">
                     <div class="anime__details__title">
                        <br>
                        <h3>오규민</h3>
                        <span>View</span>
                     </div>
                     <p>Every human inhabiting the world of Alcia is branded by a
                        “Count” or a number written on their body. For Hina’s mother,
                        her total drops to 0 and she’s pulled into the Abyss, never to
                        be seen again. But her mother’s last words send Hina on a quest
                        to find a legendary hero from the Waste War - the fabled Ace!</p>

                     <div class="anime__details__btn">
                        <a href="#" class="follow-btn">Blog</a> <a href="#"
                           class="follow-btn">GitHub</a>
                     </div>
                  </div>
               </div>
            </div>
         </div>

      </div>
   </section>
   <section class="anime-details spad">
      <div class="container">
         <div class="anime__details__content">
            <div class="row">
               <div class="col-lg-3">
                  <div style="height: 350px" class="anime__details__pic set-bg" data-setbg="img/gs.jpg">

                  </div>
               </div>
               <div class="col-lg-9">
                  <div class="anime__details__text">
                     <div class="anime__details__title">
                        <br>
                        <h3>김권수</h3>
                        <span>Model / View</span>
                     </div>
                     <p>Every human inhabiting the world of Alcia is branded by a
                        “Count” or a number written on their body. For Hina’s mother,
                        her total drops to 0 and she’s pulled into the Abyss, never to
                        be seen again. But her mother’s last words send Hina on a quest
                        to find a legendary hero from the Waste War - the fabled Ace!</p>

                     <div class="anime__details__btn">
                        <a href="#" class="follow-btn">Blog</a> <a href="#"
                           class="follow-btn">GitHub</a>
                     </div>
                  </div>
               </div>
            </div>
         </div>

      </div>
   </section>
   <section class="anime-details spad">
      <div class="container">
         <div class="anime__details__content">
            <div class="row">
               <div class="col-lg-3">
                  <div style="height: 350px" class="anime__details__pic set-bg" data-setbg="img/jh.jpg">

                  </div>
               </div>
               <div class="col-lg-9">
                  <div class="anime__details__text">
                     <div class="anime__details__title">
                        <br>
                        <h3>김진한</h3>
                        <span>Controller</span>
                     </div>
                     <p>Every human inhabiting the world of Alcia is branded by a
                        “Count” or a number written on their body. For Hina’s mother,
                        her total drops to 0 and she’s pulled into the Abyss, never to
                        be seen again. But her mother’s last words send Hina on a quest
                        to find a legendary hero from the Waste War - the fabled Ace!</p>

                     <div class="anime__details__btn">
                        <a href="#" class="follow-btn">Blog</a> <a href="#"
                           class="follow-btn">GitHub</a>
                     </div>
                  </div>
               </div>
            </div>
         </div>

      </div>
   </section>
   <section class="anime-details spad">
      <div class="container">
         <div class="anime__details__content">
            <div class="row">
               <div class="col-lg-3">
                  <div style="height: 350px" class="anime__details__pic set-bg" data-setbg="img/yh.jpg">

                  </div>
               </div>
               <div class="col-lg-9">
                  <div class="anime__details__text">
                     <div class="anime__details__title">
                        <br>
                        <h3>성윤혜</h3>
                        <span>Controller</span>
                     </div>
                     <p>Every human inhabiting the world of Alcia is branded by a
                        “Count” or a number written on their body. For Hina’s mother,
                        her total drops to 0 and she’s pulled into the Abyss, never to
                        be seen again. But her mother’s last words send Hina on a quest
                        to find a legendary hero from the Waste War - the fabled Ace!</p>

                     <div class="anime__details__btn">
                        <a href="#" class="follow-btn">Blog</a> <a href="#"
                           class="follow-btn">GitHub</a>
                     </div>
                  </div>
               </div>
            </div>
         </div>

      </div>
   </section>
   <section class="anime-details spad">
      <div class="container">
         <div class="anime__details__content">
            <div class="row">
               <div class="col-lg-3">
                  <div style="height: 350px" class="anime__details__pic set-bg" data-setbg="img/jk.jpg">

                  </div>
               </div>
               <div class="col-lg-9">
                  <div class="anime__details__text">
                     <div class="anime__details__title">
                        <br>
                        <h3>이자경</h3>
                        <span>Model / View</span>
                     </div>
                     <p>Every human inhabiting the world of Alcia is branded by a
                        “Count” or a number written on their body. For Hina’s mother,
                        her total drops to 0 and she’s pulled into the Abyss, never to
                        be seen again. But her mother’s last words send Hina on a quest
                        to find a legendary hero from the Waste War - the fabled Ace!</p>

                     <div class="anime__details__btn">
                        <a href="#" class="follow-btn">Blog</a> <a href="#"
                           class="follow-btn">GitHub</a>
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
                  <a href="Main.do"><img src="img/logo.png" alt="logo확인"></a>
               </div>
            </div>
            <div class="col-lg-3">
               <p>
                  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                  Copyright &copy;
                  <script>document.write(new Date().getFullYear());</script>
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
            <input type="text" id="search-input" name="search"
               placeholder="영화 제목 검색.....">
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