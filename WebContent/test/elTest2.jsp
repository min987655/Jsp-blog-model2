<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	/* String username = (String)request.getAttribute("username");
	String password = (String)request.getAttribute("password"); */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>elTest2.jsp</title>
</head>
<body>
	<h1>elTest2.jsp 파일</h1>
	<hr />
	유저네임 : ${username}
	<br /> 패스워드 : ${sessionScope.username}
	<br />
</body>
</html>