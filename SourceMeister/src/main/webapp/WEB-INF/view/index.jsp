<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/SourceMeister/static/css/index_layout.css" />
<link href="https://fonts.googleapis.com/css?family=Damion|Work+Sans" rel="stylesheet">
<script type="text/javascript">


$().ready(function () {
	
	
	$("#keyword").keydown(function(e) {
		if (e.which == 13) { //13 : Enter코드
			submitForm();
		}
	});
		
	
	$("#Submit").click(function() {
		submitForm();
	});
	
	
	function submitForm() {
		$("#indexForm").attr({
			"action" : "/SourceMeister/opensource?search="+$("#keyword").val(),
			"method" : "post"
		}).submit();
	}
	
	
});

</script>


<title></title>
</head>
<body>
	<form id="indexForm">
		<div style="text-align:center; vertical-align:middle;">
			<a href = "/SourceMeister/index" style="margin-left: -52px; color:black; font-family:'Damion', sans-serif; font-size: 75px; text-decoration: none">Source Meister</a><br/>
			<input id="keyword" type="text" style="margin-top: 30px; width:530px; height:30px;"/>
			<input type="button" style="font-size: 15px; margin-left: 15px; border:0; outline: 0; background-color: #FFFFFF" id="Submit" value="Search">
		</div>
	</form>
</body>
</html>