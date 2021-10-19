<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${empty sessionID}">
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
								<li><a href="Main.do">Homepage</a></li>
								<li class="active"><a href="Categories.do">Categories <span class="arrow_carrot-down"></span></a>
									 <ul class="dropdown">
									 <!-- 한글로 넘길수도 있음 -->
                                        <li><a href="Categories.do?mtype=액션">Action</a></li>
                                        <li><a href="Categories.do?mtype=애니메이션">Animation</a></li>
                                        <li><a href="Categories.do?mtype=멜로/로맨스">Melo/Romance</a></li><!-- 컨트롤과 맞추기 -->
                                        <li><a href="Categories.do?mtype=드라마">Drama</a></li>
                                        <li><a href="Categories.do?mtype=다큐멘터리">Documentary</a></li>
                                        <li><a href="Categories.do?mtype=ETC">ETC</a></li>
                                    </ul></li>
								<li><a href="project.jsp">Project</a></li>
                                <li><a href="aboutus.jsp">AboutUs</a></li>
							</ul>
						</nav>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="header__right">
						<a href="#" class="search-switch"><span class="icon_search" ></span></a>
						<a href="login.jsp"><span class="icon_profile"></span></a>
					</div>
				</div>
			</div>
	</c:when>
	<c:when test="${sessionID == 'admin' }">
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
								<li><a href="Main.do">Homepage</a></li>
								<li class="active"><a href="Categories.do">Categories <span class="arrow_carrot-down"></span></a>
									 <ul class="dropdown">
									 <!-- 한글로 넘길수도 있음 -->
                                        <li><a href="Categories.do?mtype=액션">Action</a></li>
                                        <li><a href="Categories.do?mtype=애니메이션">Animation</a></li>
                                        <li><a href="Categories.do?mtype=멜로/로맨스">Melo/Romance</a></li><!-- 컨트롤과 맞추기 -->
                                        <li><a href="Categories.do?mtype=드라마">Drama</a></li>
                                        <li><a href="Categories.do?mtype=다큐멘터리">Documentary</a></li>
                                        <li><a href="Categories.do?mtype=ETC">ETC</a></li>
                                    </ul></li>
                                <li><a href="Adminlist.do">AdminList</a></li>
								<li><a href="project.jsp">Project</a></li>
                                <li><a href="aboutus.jsp">AboutUs</a></li>
							</ul>
						</nav>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="header__right">
						<a href="#" class="search-switch"><span class="icon_search" ></span></a>
						<button type="button" onclick="logout()" style="
    color: #FFFFFF;
    background: #070720;
    outline: 0;
    border: 0;
"><span class="icon_profile"></span></button>
					</div>
				</div>
			</div>
	</c:when>
	<c:otherwise>
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
								<li><a href="Main.do">Homepage</a></li>
								<li class="active"><a href="Categories.do">Categories <span class="arrow_carrot-down"></span></a>
									 <ul class="dropdown">
									 <!-- 한글로 넘길수도 있음 -->
                                        <li><a href="Categories.do?mtype=액션">Action</a></li>
                                        <li><a href="Categories.do?mtype=애니메이션">Animation</a></li>
                                        <li><a href="Categories.do?mtype=멜로/로맨스">Melo/Romance</a></li><!-- 컨트롤과 맞추기 -->
                                        <li><a href="Categories.do?mtype=드라마">Drama</a></li>
                                        <li><a href="Categories.do?mtype=다큐멘터리">Documentary</a></li>
                                        <li><a href="Categories.do?mtype=ETC">ETC</a></li>
                                    </ul></li>
								<li><a href="project.jsp">Project</a></li>
                                <li><a href="aboutus.jsp">AboutUs</a></li>
                                <li><a href="CselectOne.do">MyPage</a></li>
							</ul>
						</nav>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="header__right">
						<a href="#" class="search-switch"><span class="icon_search" ></span></a>
						<button type="button" onclick="logout()" style="
    color: #FFFFFF;
    background: #070720;
    outline: 0;
    border: 0;
"><span class="icon_profile"></span></button>
					</div>
				</div>
			</div>
	</c:otherwise>
</c:choose>
