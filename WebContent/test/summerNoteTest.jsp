<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/nav.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Summernote</title>
  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
</head>
<body>
<div class="container">
  <div id="summernote"><p>Hello Summernote</p></div>
  <script>
    $(document).ready(function() {
        $('#summernote').summernote({
			tapsize : 2,
        	height : 500
        });
    });
  </script>
 </div>
</body>
</html>