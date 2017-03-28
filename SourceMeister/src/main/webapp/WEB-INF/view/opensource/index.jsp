<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/api2/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">

	$().ready(function () {
			
		$("#search").keydown(function(e) {
			if (e.which == 13) {
				submitForm();
			}
		});
			
		
		$("#searchForm").find("input[type=button]").click(function() {
			submitForm();
		});
		
		
	});

	function submitForm() {
		$("#searchForm").attr({
			"action" : "/api2/result",
			"method" : "post"
		}).submit();
	}

</script>

<title>검색을 해보자</title>
</head>
<body>
	
	<form id = "searchForm">	
		<input type="text" name="search" id="search"/>
		<input type="button" value="검색"/>
	</form>
	
</body>
</html>