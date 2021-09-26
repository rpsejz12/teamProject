<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="title" %>
<%@ attribute name="type" %>

<c:when test="${type == 'frm' }">
<c:if test="${title == null }">
	<form action="m_insert.do" method="post" name="form1" enctype="multipart/form-data">
</c:if>
<c:if test="${title !=null }">
	<form action="m_update.do" method="post" name="form1" enctype="multipart/form-data">
</c:if>
</c:when>

<c:when test="${type=='btn'}">
<c:if test="${title == null}">
	 <input type="submit" class="follow-btn" value="등록">
 </c:if>
<c:if test="${title != null }">
	<input type="submit" class="watch-btn" value="수정">
	<a href="m_delete.do" class="watch-btn">삭제</a>
</c:if>	
</c:when>

</form>