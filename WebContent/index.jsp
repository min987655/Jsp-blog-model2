<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	/* response.sendRedirect("/blog/board?cmd=home"); */
%>
<%-- <c:redirect 잘 안씀 그냥 테스트, 태그 또 넣어야해서 자바 코드로 보통 사용 함.--%> 
<c:redirect url="/board?cmd=home&page=0" />