<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="title"%>
<c:choose>
	<c:when test="${title != null && title != '' }">
		<form action="Mupdate.do" method="post" name="form1"
			enctype="multipart/form-data">
			<div class="col-lg-6 col-md-6 col-sm-6">
				<input type="file" name="filename" value="포스터등록">
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6">
				<input type="text" name="title" placeholder="Title">
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6">
				<select name="mtype">
				<option selected value="액션">Action</option>
				<option value="애니메이션">Animation</option>
				<option value="멜로/로맨스">Melo/Romance</option>
				<option value="드라마">Drama</option>
				<option value="다큐멘터리">Documentary</option>
				<option value="ETC">ETC</option>
			</select> 
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6">
				<input type="date" name="mdate" placeholder="date">
			</div>
			<input type="submit" class="watch-btn" value="수정"> <a
				href="Mdelete.do" class="watch-btn">삭제</a>
		</form>
	</c:when>

	<c:when test="${title == null || title == '' }">
		<form action="Minsert.do" method="post" name="form1" enctype="multipart/form-data">
			<div class="col-lg-6 col-md-6 col-sm-6">
				<input type="file" name="filename" value="포스터등록">
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6">
				<input type="text" name="title" placeholder="Title">
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6">
				<input type="date" name="mdate" placeholder="date">
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6">
				<input type="text" name="content" placeholder="content">
			</div>
			<select name="mtype">
				<option selected value="액션">Action</option>
				<option value="애니메이션">Animation</option>
				<option value="멜로/로맨스">Melo/Romance</option>
				<option value="드라마">Drama</option>
				<option value="다큐멘터리">Documentary</option>
				<option value="ETC">ETC</option>
			</select> 
			<input type="submit" class="follow-btn" value="등록">
		</form>
	</c:when>
</c:choose>
