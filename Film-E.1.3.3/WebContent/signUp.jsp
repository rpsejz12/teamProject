<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>    
<!DOCTYPE html>
<html lang="zxx">
<script type="text/javascript">
	function send(){
		// 아이디 유효성 검사 (영어대소문자,숫자만허용)
		for(var i=0; i<document.check.id.value.length; i++){ // 아이디 길이체크
			ch=document.check.id.value.charAt(i) // char타입으로 변환
			if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z')&&!(ch >= 'A' && ch <= 'Z')) { // 대소문자 및 숫자 입력가능
                alert("아이디에 영문 대소문자, 숫자만 입력가능합니다.")
                document.check.id.focus(); // document.엘리멘트.focus로 해당 위치 이동
                document.check.id.select();
                return false;
            }
		}
		// 아이디 유효성 검사 (공백)
		if (document.check.id.value.indexOf(" ") >= 0) {
			alert("아이디에 공백을 사용할 수 없습니다.")
	        document.check.id.focus();
	        document.check.id.select()
	        return false;
	    }
		// 아이디 유효성 검사(4~12글자)
		if (document.check.id.value.length<4 || document.check.id.value.length>12) {
            alert("아이디를 4~12자까지 입력해주세요.")
            document.check.id.focus();
            document.check.id.select();
            return false;
        }
		// 아이디와 비밀번호가 같음
		if (check.pw.value == check.id.value) {
            alert("아이디와 비밀번호가 같습니다.")
            document.pw.password.focus();
            return false;
        }
		// 비밀번호 유효성 검사 (공백)
		if (document.check.pw.value == "") {
	        alert("비밀번호를 입력하지 않았습니다.")
	        document.check.pw.focus();
	        return false;
	    }
		// 비밀번호 유효성 검사(4~16자 까지 허용)
        if (document.check.pw.value.length<4 || document.check.pw.value.length>16) {
            alert("비밀번호를 4~16자까지 입력해주세요.")
            document.check.pw.focus();
            document.check.pw.select();
            return false;
        }
        // 비밀번호와 비밀번호 확인 일치여부 체크
         if (document.check.pw1.value != document.check.pw.value) {
            alert("비밀번호가 일치하지 않습니다")
            document.check.pw1.value = ""
            document.check.pw1.focus();
            return false;
        } 
	}
</script>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Anime Template">
    <meta name="keywords" content="Anime, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Film-E | SignUp</title>

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
                                <li><a href="main.do">Homepage</a></li>
                                <li><a href="categories.do">Categories <span class="arrow_carrot-down"></span></a>
                                    <ul class="dropdown">
                                        <li><a href="categories.do?mtype=ACTION">Action</a></li>
                                        <li><a href="categories.do?mtype=ANIMATION">Animation</a></li>
                                        <li><a href="categories.do?mtype=HT">Horror·Thriller</a></li><!-- 컨트롤과 맞추기 -->
                                        <li><a href="categories.do?mtype=ROMANCE">Romance</a></li>
                                        <li><a href="categories.do?mtype=SF">Sf·Fantasy</a></li>
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

    <!-- Normal Breadcrumb Begin -->
    <section class="normal-breadcrumb set-bg" data-setbg="img/login_img.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="normal__breadcrumb__text">
                        <h2>Sign Up</h2>
                        <p>Welcome to the Film-E.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Normal Breadcrumb End -->

    <!-- Signup Section Begin -->
     <section class="signup spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="login__form">
                        <h3>Sign Up</h3>
                        <form action="c_insert.do" method="post" id="form1" name="check" onsubmit="return send();">
                            <div class="input__item">
                                <input type="text" name="id" id="id" placeholder="ID" required>
                                <span class="icon_profile"></span>
                            </div>
                            <div class="input__item">
                                <input type="password" name="pw" id="pw" placeholder="Password" required>
                                <span class="icon_lock"></span>
                            </div>
                           <div class="input__item">
                                <input type="password" id="pw1" placeholder="Password Check" required>
                                <span class="icon_lock"></span>
                            </div>
                            <div class="input__item">
                                <input type="text" name="email" id="email" placeholder="Email address" required>
                                <span class="icon_mail"></span>
                            </div>
                                <!-- css로 조정할것 -->
                            <button type="submit" class="site-btn">Sign Up</button>
                            <button type="reset" class="site-btn">reset</button>
                        </form>
                        <h5>Already have an account? <a href="login.jsp">Log In!</a></h5>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="login__social__links">
                        <h3>이용약관</h3>
                        <ul>
                            <li><textarea readonly style="width:400px; height:100px; resize:none;">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</textarea>
                            <input type="checkbox" required form=form1></li>
                            <li><textarea readonly style="width:400px; height:100px; resize:none;">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</textarea>
                            <input type="checkbox" required form=form1></li>
                            <li><textarea readonly style="width:400px; height:100px; resize:none;">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</textarea>
                            <input type="checkbox" required form=form1></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Signup Section End -->

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