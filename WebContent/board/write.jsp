<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp"%>
<%@ include file="../include/authentication.jsp"%>

<div class="container">
	<form action="/blog/board?cmd=writeProc" method="POST">

		<div class="form-group">
			<label for="comment">Title:</label> <input type="text" class="form-control" placeholder="title" id="title" name="title">
		</div>

		<div class="form-group">
			<label for="content">Content:</label>
			<textarea id="summernote" class="form-control" rows="5" id="content" name="content"></textarea>
		</div>

		<button type="submit" class="btn btn-primary">글쓰기 등록</button>
	</form>
</div>

<script>
	$(document).ready(function() {
		$('#summernote').summernote({
			tapsize : 2,
			height : 400
		});
	});
</script>
<%@ include file="../include/footer.jsp"%>