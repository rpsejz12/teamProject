<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="title"%>

<c:if test="${title == null || title == '' }">
   <form action="m_insert.do" method="post" name="form1"
      enctype="multipart/form-data">
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
            <option selected value="ACTION">Action</option>
            <option value="ANIMATION">Animation</option>
            <option value="HT">Horror·Thriller</option>
            <option value="ROMANCE">Romance</option>
            <option value="SF">Sf·Fantasy</option>
         </select>
      <input type="submit" class="follow-btn" value="등록">
   </form>
</c:if>

<c:if test="${title !=null && title != '' }">
   <form action="m_update.do" method="post" name="form1"
      enctype="multipart/form-data">
      <div class="col-lg-6 col-md-6 col-sm-6">
         <input type="file" name="filename" value="포스터등록">
      </div>
      <div class="col-lg-6 col-md-6 col-sm-6">
         <input type="text" name="title" placeholder="Title">
      </div>
      <div class="col-lg-6 col-md-6 col-sm-6">
          <select name="mtype">
            <option selected value="ACTION">Action</option>
            <option value="ANIMATION">Animation</option>
            <option value="HT">Horror·Thriller</option>
            <option value="ROMANCE">Romance</option>
            <option value="SF">Sf·Fantasy</option>
         </select>
      </div>
      <div class="col-lg-6 col-md-6 col-sm-6">
         <input type="date" name="mdate" placeholder="date">
      </div>
      <input type="submit" class="watch-btn" value="수정"> <a
         href="m_delete.do" class="watch-btn">삭제</a>
   </form>
</c:if>

