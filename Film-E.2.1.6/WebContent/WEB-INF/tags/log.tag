<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${empty sessionID}">
			<div class="col-lg-1">
					<div class="header__right">
						<a href="login.jsp"><span class="icon_profile"></span></a>
					</div>
			</div>
	</c:when>
	<c:when test="${sessionID == 'admin' }">
		<div class="col-lg-1">
               <div class="header__nav">
                  <nav class="header__menu mobile-menu">
                     <ul>
                        <li><a href="#;"><span class="icon_profile"></span></a>
                            <ul class="dropdown">
                            	<li><a href="Adminlist.do ">AdminList</a></li>
                                <li><a href="Logout.do">LogOut</a></li>
                            </ul>
                        </li>    
                     </ul>
                  </nav>
               </div>
            </div>
	</c:when>
	<c:otherwise>
			<div class="col-lg-1">
               <div class="header__nav">
                  <nav class="header__menu mobile-menu">
                     <ul>
                        <li><a href="#;"><span class="icon_profile"></span></a>
                            <ul class="dropdown">
                            	<li><a href="CselectOne.do">My Page</a></li>
                                <li><a href="Logout.do">LogOut</a></li>
                            </ul>
                        </li>    
                     </ul>
                  </nav>
               </div>
            </div>
	</c:otherwise>
</c:choose>
