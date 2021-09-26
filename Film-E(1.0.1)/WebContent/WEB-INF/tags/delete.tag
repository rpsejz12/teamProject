<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="rpk" %>
<%@ attribute name="id" %>

<c:if test="${seUser == id}">
	<a href="r_delete.do?&rpk=${rpk}">삭제</a>
</c:if>