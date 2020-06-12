function replyWrite(boardId, userId) {
	var data = {
		// 변수가 아니라 그냥 key : value 임
		boardId : boardId,
		userId : userId,
		content : $("#reply__write__form").val()
	};

	$.ajax({
		type : "post",
		url : "/blog/reply?cmd=writeProc",
		data : JSON.stringify(data),
		contentType : "application/json; charset=utf-8",
		dataType : "json"
	}).done(function(result) {
		console.log(result);
		// 정상 응답
		// 1. reply__list를 찾아서 내부 비우기(remove같은 함수 찾아서 비우기) -------- 까지하기
		if (result == -1 || result == 0) {
			alert("댓글 작성 실패");
		} else {
			alert("댓글 작성 성공");
			$("#relpy__list").empty();
			console.log(result);
			renderReplyList(result);
			$("#relpy__write__form").val("");
		}
		// 2. ajax 재호출 findAll()
		// 3. reply__list를 찾아서 내부에 채워주기
	}).fail(function(error) {
		alert("댓글 작성 실패");
	});
}

function renderReplyList(replyDtos) {
	for(var replyDto of replyDtos) {
		$("#relpy__list").append(makeReplyItem(replyDto));
	}
}

function makeReplyItem(replyDto) {
	var replyItem = `<li class="media">`;
	if(replyDto.userProfile == null){
		replyItem += `<img src="/blog/image/userProfile.png" class="img-circle">`;	
	}else{
		replyItem += `<img src="${replyDto.userProfile}" class="img-circle">`;
	}

	replyItem += `<div class="media-body">`;
	replyItem += `<strong class="text-primary">${replyDto.username}</strong>`;
	replyItem += `<p>${replyDto.reply.content}</p>`;
	replyItem += `</div>`;
	replyItem += `</li>`;
	return replyItem;
} 