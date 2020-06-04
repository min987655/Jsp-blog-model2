<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
div {
	border: 1px solid black;
	margin: 5px;
	padding: 5px;
}
</style>
</head>
<body>
	<div id="reply-box">
		<div id="reply-1">첫번째 댓글입니다.</div>
	</div>
	<input type="text" id="tf-reply" /><br />
	<button onclick="start()">댓글쓰기</button>

	<script>
		var num = 1;
		function start() {
			num++;
			var a = $('#tf-reply').val();

			var data = {
				username : "ssar",
				content : a
			};

			// 통신이 성공하면 아래 로직 실행
			$.ajax({
				type : 'POST', // 생략가능
				url : 'AjaxResponseTest.jsp', // 필수 값
				data : JSON.stringify(data), //data String이 날아감. // 보내는 데이터 // 생략가능
				// 데이터 형태가 Json일 경우 협업하는 사람을 위해 타입 표기 해줘야 함
				contentType : 'application/json; charset=utf-8', // 보낼 데이터 타입 // 생략가능
				dataType : 'json' // 응답받을 데이터를 어떻게 파싱할까를 정의 // text(기본값) or json(자동으로 파싱해줌/ 타입 안적고 done 받을 때 파싱해도 됨) // 생략가능
			}).done(function(result) { // 통신 성공시 콜백
				console.log(result);
				$('#reply-box').prepend("<div id='reply-"+num+"'>" + a + "</div>");	
			}).fail(function(error) { // 통신 실패시 콜백
				console.log('에러 확인');
				console.log(error);
			});
		}
	</script>
</body>
</html>