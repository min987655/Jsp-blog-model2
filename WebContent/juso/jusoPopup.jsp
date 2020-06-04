<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");
	String inputYn = request.getParameter("inputYn"); 
	String roadFullAddr = request.getParameter("roadFullAddr"); 
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Juso.kr 팝업</title>
</head>
<script>

function init(){
	var url = location.href; // returnUrl
/* 	alert(url); // http://localhost:8000/blog/test/jusoPopup.jsp == 자기 자신 */
	var confmKey = "devU01TX0FVVEgyMDIwMDYwMjA5NDkwMjEwOTgyMDE=";
	var resultType = "4";
	var inputYn= "<%=inputYn%>";
	/* 처음에 inputYn=0, Y가 아니기 때문에 if문 돔*/
	if(inputYn != "Y"){
		document.form.confmKey.value = confmKey;
		document.form.returnUrl.value = url;
		document.form.resultType.value = resultType;
		document.form.action="http://www.juso.go.kr/addrlink/addrLinkUrl.do"; //인터넷망
		document.form.submit();
	}else{
		opener.jusoCallBack("<%=roadFullAddr%>");
		window.close();
		}
}
</script>
<body onload="init()"> 
	<form id="form" name="form" method="post">
		<input type="hidden" id="confmKey" name="confmKey" value=""/>
		<input type="hidden" id="returnUrl" name="returnUrl" value=""/>
		<input type="hidden" id="resultType" name="resultType" value=""/>
	</form>
</body>
</html>