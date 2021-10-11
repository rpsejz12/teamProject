<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>    
<!DOCTYPE html>
<html lang="zxx">
<script src="jquery-3.6.0.min.js"></script>
<script type="text/javascript">
function logout(){
	if(confirm("정말로 로그아웃 하시겠습니까?")==true){
		location.href="Logout.do";		
	}
	else{
		return;
	}
}
   function checkId(){
      $.ajax({ 
         type: "GET", // 단순 정보 조회 시에는 GET, 정보가 너무 많거나 insert/update를 할때는 POST
         url: "CheckID.do", /////////// ???????
         data: {
            id : $("#id").val() // $().val() : 값 가져오기
         },
         success: function(data) { 
            if (data.trim()=="false") {
               for(var i=0; i<document.check.id.value.length; i++){ // 아이디 길이체크
                  ch=document.check.id.value.charAt(i) // char타입으로 변환
                  if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z')&&!(ch >= 'A' && ch <= 'Z')) { // 대소문자 및 숫자 입력가능
                     $("#resultId").css("color","red");
                     $("#resultId").css("margin-top","-5px");
                     $("#resultId").css("margin-bottom","0px");
                     $("#resultId").css("font-size","small");
                     $("#resultId").text("아이디에 영문 대소문자, 숫자만 입력가능합니다.");
                     }
                  else if (document.check.id.value.length<4 || document.check.id.value.length>12) {
                     $("#resultId").css("color","red");
                     $("#resultId").css("margin-top","-5px");
                     $("#resultId").css("margin-bottom","0px");
                     $("#resultId").css("font-size","small");
                     $("#resultId").text("아이디를 4~12자까지 입력해주세요.");
                    }
                  else{
                     $("#resultId").css("color","green");
                     $("#resultId").css("margin-top","-5px");
                     $("#resultId").css("margin-bottom","0px");
                     $("#resultId").css("font-size","small");
                     $("#resultId").text("사용 가능한 아이디입니다.");
                  }
               }
               
               /* alert("사용 가능한 ID입니다."); */
            } else {
               $("#resultId").css("color","red");
               $("#resultId").css("margin-top","-5px");
               $("#resultId").css("margin-bottom","0px");
               $("#resultId").css("font-size","small");
               $("#resultId").text("아이디가 이미 존재합니다. 다시 입력하세요.");
               /* alert("ID가 이미 존재합니다. 다시 입력하세요."); */
            }
         },
         error: function(xhr) {
            console.log(xhr.status + " : " + xhr.errorText);
            alert("에러발생!");
         }
      });
   }
   function checkPw1(){
         if(check.pw.value == check.id.value){
            $("#resultPw1").css("color","red");
            $("#resultPw1").css("margin-top","-5px");
            $("#resultPw1").css("margin-bottom","0px");
            $("#resultPw1").css("font-size","small");
            $("#resultPw1").text("아이디와 비밀번호가 같습니다.");
         }
         else{
            $("#resultPw1").css("color","green");
            $("#resultPw1").css("margin-top","-5px");
            $("#resultPw1").css("margin-bottom","0px");
            $("#resultPw1").css("font-size","small");
            $("#resultPw1").text("사용 가능한 비밀번호 입니다.");
         }
   }
   function checkPw2(){
      if(document.check.pw1.value != document.check.pw.value){
         $("#resultPw2").css("color","red");
         $("#resultPw2").css("margin-top","-5px");
         $("#resultPw2").css("margin-bottom","0px");
         $("#resultPw2").css("font-size","small");
         $("#resultPw2").text("비밀번호가 일치하지 않습니다.");
      }
      else{
         $("#resultPw2").css("color","green");
         $("#resultPw2").css("margin-top","-5px");
         $("#resultPw2").css("margin-bottom","0px");
         $("#resultPw2").css("font-size","small");
         $("#resultPw2").text("비밀번호가 일치합니다.");
      }
   }
   function checkEmail(){
      var regExp = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
        if(document.check.email.value.match(regExp) == null) {
         $("#resultEmail").css("color","red");
         $("#resultEmail").css("margin-top","-5px");
         $("#resultEmail").css("margin-bottom","0px");
         $("#resultEmail").css("font-size","small");
         $("#resultEmail").text("올바른 이메일 형식이 아닙니다.");
      }
      else{
         $("#resultEmail").css("color","green");
         $("#resultEmail").css("margin-top","-5px");
         $("#resultEmail").css("margin-bottom","0px");
         $("#resultEmail").css("font-size","small");
         $("#resultEmail").text("사용 가능한 이메일입니다.");
      }
   }
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
         // 이메일 유효성 검사
         var regExp = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
         if(document.check.email.value.match(regExp) == null) {
           alert('올바른 이메일 형식이 아닙니다.')
           document.check.email.value = ""
          document.check.email.focus();
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
			<mytag:main/>
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
                        <form action="Cinsert.do" method="post" id="form1" name="check" onsubmit="return send();">
                            <div class="input__item">
                                <input type="text" name="id" id="id" placeholder="ID" onkeyup="checkId()"style="margin-bottom: -15px;" required>
                                <span class="icon_profile"></span>
                            </div>
                                <p id="resultId" style="margin-bottom: -5px;"><br></p>
                            <div class="input__item">
                                <input type="password" name="pw" id="pw" placeholder="Password" onkeyup="checkPw1()" style="margin-bottom: -15px;" required>
                                <span class="icon_lock"></span>
                            </div>
                               <p id="resultPw1"  style="margin-bottom: -5px;"><br></p>
                           <div class="input__item">
                                <input type="password" id="pw1" placeholder="Password Check" onkeyup="checkPw2()"style="margin-bottom: -15px;" required>
                                <span class="icon_lock"></span>
                            </div>
                               <p id="resultPw2"  style="margin-bottom: -5px;"><br></p>
                            <div class="input__item">
                                <input type="text" name="email" id="email" placeholder="Email address" onkeyup="checkEmail()" style="margin-bottom: -15px;" required>
                                <span class="icon_mail" ></span>
                            </div>
                               <p id="resultEmail"  style="margin-bottom: -5px;"><br></p>
                                <!-- css로 조정할것 -->
                                 <div >
                            <button type="submit" class="site-btn">Sign Up</button>
                            <button type="reset" class="site-btn">reset</button>
                     </div>
                        </form>
                        <h5>Already have an account? <a href="login.jsp">Log In!</a></h5>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="login__social__links">
                        <h3>이용약관</h3>
                        <ul>
                            <li><textarea readonly style="width:400px;height:100px;resize:none;margin-top: -3px;">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</textarea>
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
                        <a href="Main.do"><img src="img/logo.png" alt="logo확인"></a>
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