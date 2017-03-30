<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/SourceMeister/static/css/index_layout.css" />
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

	<div id="container">
		
<%-- <<<<<<< HEAD
		<div id="top">
			<div id="login"></div>
			<div id="search">
				<form id="searchForm">
					<input id="keyword" name="search" type="text" /> <input type="button" value="검색" />
				</form>
			</div>
		</div>
		
		<div id="left-rank">
			<div id="rank-repo">
				<span>저장소별 소스 수</span>
			</div>
			<div id="rank-lan">
				<span>언어별 소스 수</span>
			</div>
		</div>
			
		<div id="middle-content">
			<jsp:include page="/WEB-INF/view/opensource/list.jsp" flush="false"></jsp:include>
		</div>	
		<div id="right-rank">
			<span>검색어 순위</span>
			
		</div>
			<div id="footer">
			 	<span>Copyright </span>
			</div>	
		</div>




======= --%>
		<div id="left">
			<div class="login">
				<jsp:include page="/WEB-INF/view/user/signIn.jsp" flush="false"></jsp:include>
			</div>
			
			<div class="rank-repo">
				<span>저장소별 소스 수</span>
			</div>
			
			<div class="rank-lan">
				<span>언어별 소스 수</span>
			</div>
			
		</div><div id="middle">
		
			<div id="search">
				<br/><br/><br/><br/>
				<form id="searchForm">
					<input id="keyword" name="search" type="text" /> <input type="button" value="검색" />
				</form>
			</div>
			
			<div id="middle-content">
				<jsp:include page="/WEB-INF/view/opensource/list.jsp" flush="false"></jsp:include>
			</div>	
			
		</div>
		
		<div id="right">
			<div id="serchRank">
				<span>검색어 순위</span>
			</div>
			
			<div id="reply">
				<span>댓글</span>
			</div>
		</div>
		<div id="footer">
			 <span>Copyright </span>
		</div>	
		
		
		
</div>

		
		
</body>
</html>