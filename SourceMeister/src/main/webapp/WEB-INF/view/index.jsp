<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/SourceMeister/static/css/index_layout.css" />
<script type="text/javascript">


$().ready(function () {
	
	
	$("#keyword").keydown(function(e) {
		if (e.which == 13) { //13 : Enter코드
			submitForm();
		}
	});
		
	
	$("#indexForm").find("input[type=image]").click(function() {
		submitForm();
	});
	
	
	function submitForm() {
		$("#indexForm").attr({
			"action" : "/SourceMeister/opensource",
			"method" : "post"
		}).submit();
	}
	
	
});

</script>


<title></title>
</head>
<body>
	<form id="indexForm">
			<img src="/SourceMeister/static/img/index.png">
			<input id="keyword" name="search" type="text"/> 
			<input type="image" src="/SourceMeister/static/img/search-icon2.png" alt="Submit" width="40" height="40" style="position:relative; top:17px;">
	</form>
</body>
</html>