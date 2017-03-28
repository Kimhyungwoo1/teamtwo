<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
$().ready(function () {
	
	$("#keyword").keydown(function(e) {
		if (e.which == 13) {
			submitForm();
		}
	});
		
	
	$("#searchForm").find("input[type=button]").click(function() {
		submitForm();
	});
	
	
	function submitForm() {
		$("#searchForm").attr({
			"action" : "/SourceMeister/opensource",
			"method" : "post"
		}).submit();
	}
	
	
	
});



</script>

<title>검색을 해보자</title>
</head>
<body>

	<form id="searchForm">
		<input id="keyword" name="search" type="text" /> <input type="button" value="검색" />
	</form>


	<jsp:include page="/WEB-INF/view/opensource/list.jsp"></jsp:include>


</body>
</html>