<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 세션이 없으면 인덱스로 이동 -->
<c:if test="${empty sessionScope.principal}">
	<c:redirect url="/index.jsp"></c:redirect>
</c:if>