<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="5점">
		⭐⭐⭐⭐⭐
	</c:when>
	<c:when test="4점">
		⭐⭐⭐⭐
	</c:when>
	<c:when test="3점">
		⭐⭐⭐
	</c:when>
	<c:when test="2점">
		⭐⭐
	</c:when>
	<c:when test="1점">
		⭐
	</c:when>
	<c:otherwise>
		☆☆☆☆☆
	</c:otherwise>
</c:choose>