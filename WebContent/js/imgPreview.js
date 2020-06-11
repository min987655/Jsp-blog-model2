	/* 제이쿼리 이벤트 달아줌 */
	$("#img__preview").on("change", function(e){
		console.log(e.target.files);
		console.log(e.target.files[0].type);
		var f = e.target.files[0];
		if(!f.type.match("image*")) {
			alert('이미지만 첨부할 수 있습니다.');
			$("#img__preview").val("");
			return;
		}
		// if (f.size) {} = 1024*1024*2 이 사이즈를 넘어가면 return시킴 . 위에 참고		
		console.log(f.size);
		if (f.size > 2097152) {
			alert('사진 사이즈를 줄여주세요.');
			return;
		}

		var reader = new FileReader();
		
		//비동기 실행 : 파일을 하드에 저장하는 I/O는 저장장치가 처리하고 CPU는 다른 이벤트 대기함.
		reader.onload = function(e) { // 다운이 완료되는것을 바인딩하는 함수(CPU와 저장장치의 타이밍 문제 때문에 reader보다 위에 있어야 함)
			$("#img__wrap").attr("src", e.target.result);
		};
		reader.readAsDataURL(f); //파일 객체를 reader가 이미지 로드시킴 -> 이미지로드가 끝나면 생성되는 e의 target.result에 담김   
	});