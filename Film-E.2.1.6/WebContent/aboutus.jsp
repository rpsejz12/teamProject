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
<meta name="naver-site-verification" content="07e267492e17e9e2ec83e71588f1108e89250462" />
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
                     <p>지난번 수행했던 프로젝트에서 못했던 디테일한 점이나 혹은 부족했던 점들을 보강하기 위해 설계부터 다같이 튼튼하게 진행을 하였으며 잦은 회의와 꾸준한 개발일지 작성 버전관리를 뿐만 아니라 프로젝트의 완성도가 조원분들의 꾸준한 도움과 노력 덕분에 높게 나온것 같다. 다만 템플릿의 구조적인 이해가 조금 부족했다 라는 생각이 든다. 특히 레이아웃 그리드와 같이 처음 보는 디자인은 조금 더 공부를 하는 계기가 되었던것 같다.
</p>

                     <div class="anime__details__btn">
                        <a href="https://pyungdo.tistory.com/" class="follow-btn" target='_blank'>Blog</a>
                        <a href="https://github.com/Ohgyumin" class="follow-btn" target='_blank'>GitHub</a>
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
                     <p>이번 프로젝트는 영화 리뷰 사이트 제작 프로젝트 였습니다. 팀원과 협업하여 
MVC패턴에 맞춰 웹제작의 전반적인 작업에 프로세스에 대해 숙지 할수 있었던 기회가 되었습니다.
이번 프로젝트를 진행하면서 헤매었던 부분 중 하나는 버전관리였습니다. 초반에 버전관리에 관해 많은 신경을 쓰지 않고
진행하였는대, 어느순간 최신버전이 아닌 버전에서 수정을 하다가 취합이 잘못 되어서 코드가 실행이 되지 않았습니다.
수정내역도 정확히 기록하지 않았기때문에 복구하는대 노력이 필요했는대 그덕에, 버전관리의 중요성을 알게된 계기가 되었고
진행하며 버전관리에 익숙하게 될수 있었습니다.전반적으로 진행하며 프로젝트의 결과가 실제 사이트와 유사하게 완성이 되어서
만족스러웠던 결과였습니다.</p>

                     <div class="anime__details__btn">
                        <a href="https://github.com/rpsejz12"class="follow-btn" target='_blank'>GitHub</a>
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
                     <p>이번 프로젝트를 통해 jsp를 기반한 M-V-C 구조에 한층 더 도약하는 계기가 되었습니다
컨트롤러 부분이 많이 어려워서 이번 기회에 컨트롤러에 도전을 했습니다
어려운 부분은 팀원들과의 소통과 협력심으로 극복을 했고 아주 재미있는 팀플 이였습니다
저희 프로젝트의 메인 기능인 파일 입출력을 공부할 수 있어서 좋았던 시간이였습니다


</p>

                     <div class="anime__details__btn">
                        <a href="https://github.com/jinhan72/javacatch" class="follow-btn" target='_blank'>GitHub</a>
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
                     <p>이번 프로젝트를 통해 페이지 이동 방식 등 컨트롤러에 관련한 헷갈렸던 개념을 다지는데에 큰 도움이 되었으며,
MVC 기반의 웹 제작에 대한 전반적인 흐름을 확실히 이해할 수 있는 유익한 시간이었습니다.
크롤링, 파일 입출력, 별점 처리 등 전에 해보지 않아던 시도를 해보면서 다양한 기능을 배울 수 있었고,
팀원들과 함께 소통하고 서로 도와가며 즐겁게 프로젝트에 임할 수 있었습니다.
결과물이 기대보다 더 완성도 높게 만들어진 것 같아 굉장히 만족스러운 프로젝트였습니다.</p>

                     <div class="anime__details__btn">
                        <a href="https://sngynhy.tistory.com/" class="follow-btn" target='_blank'>Blog</a>
                        <a href="https://github.com/sngynhy" class="follow-btn" target='_blank'>GitHub</a>
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
                     <p>템플릿을 사용해보니 사용자가 보는 화면이 훨씬 깔끔하게 구현되고, 실제와 비슷한 프로그램이 만들어지는 것을 보며
                        템플릿을 분석하는 재미를 느꼈다. 이번 프로젝트에서 model파트를 맡아 그동안 헷갈렸던 sql문 작성이나,
                        트랜잭션 처리방식에 대해 확실하게 알 수 있게 된 시간이되었고, 설계부터 탄탄히  구현을 시작하니
                        이전 프로젝트에서처럼 중간에 설계를 바꾸거나 팀원끼리 소통이 덜 된 부분들이 보완 되어 설계의 중요성을 강조하는
                        이유가 이런 부분 때문이구나 다시 한번 느낄 수 있는 시간이었다.</p>

                     <div class="anime__details__btn">
                        <a href="https://blog.naver.com/ljk940826" class="follow-btn" target='_blank'>Blog</a>
                        <a href="https://github.com/leejakyung " class="follow-btn" target='_blank'>GitHub</a>
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