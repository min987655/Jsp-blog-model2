var isCheckedUsername = false;

// juso.go.kr 라이브러리 함수 (시작) ---------------------------------------

function goPopup() {
	window.open("/blog/juso/jusoPopup.jsp", "pop", "width=570,height=420, scrollbars=yes, resizable=yes");
}

function jusoCallBack(roadFullAddr) {
	var tfAddress = document.querySelector("#address");
	tfAddress.value = roadFullAddr;
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	/* document.form.roadFullAddr.value = roadFullAddr; */
}

// 중복체크 함수
function validate() {
	if (!isCheckedUsername) {
		alert('username 중복체크를 해주세요');
	}
	return isCheckedUsername;
}

// 데이터베이스 Ajax 요청을 해서 중복 유저네임이 있는지 확인
// 있으면 1을 리턴, 없으면 0을 리턴, 오류나면 -1을 리턴
function usernameCheck() {
	// 성공(Ajax)
	var tfUsername = $('#username').val();
	// alert(tfUsername);
	console.log(tfUsername);
	$.ajax({
		type : 'get',
		url : `/blog/user?cmd=usernameCheck&username=${tfUsername}`
	}).done(function(result) {
		console.log(result);
		if (result == 1) {
			alert('아이디가 중복되었습니다');
		} else if (result == 0) {
			alert('사용하실 수 있는 아이디입니다');
			isCheckedUsername = true;
		} else {
			console.log('develop : 서버 오류');
		}
	}).fail(function() {
		console.log(error);
	});

}