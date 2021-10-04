<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="rpk" %>
<%@ attribute name="id" %>
<%@ attribute name="mpk" %>
<c:if test="${sessionID == id}">
	<a href="r_delete.do?&rpk=${rpk}&mpk=${mpk}">삭제</a>
</c:if>