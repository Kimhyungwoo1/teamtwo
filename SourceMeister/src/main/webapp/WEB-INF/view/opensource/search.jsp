<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="/SourceMeister/static/js/hilitor.js"></script>
<link rel="stylesheet" type="text/css" href="/SourceMeister/static/css/index_layout.css" />
<script type="text/javascript">

$().ready(function () {
	
	
	$("#keyword").keydown(function(e) {
		if (e.which == 13) { //13 : Enter코드
			submitForm();
		}
	});
		
	

	$("#searchForm").find("input[type=image]").click(function() {
		submitForm();
	});
	
	
	function submitForm() {
		$("#searchForm").attr({
			"action" : "/SourceMeister/opensource",
			"method" : "post"
		}).submit();
	}
	
	 var myHilitor = new Hilitor("#table-content");
	  myHilitor.apply($("#keyword").val());
	  
	
});


 

</script>


<title>검색</title>
</head>
<body>

	<div id="container">
		
		<div id="left">

		
			<div class="rank-repo">
				<span>저장소별 소스 수</span><br/>
				<c:forEach items="${sources}" var="sources">

					<a href="/SourceMeister/opensource?langId=${param.langId}&q=${search}&srcId=${sources.id}">${sources.source}: ${sources.count}</a><br>

				</c:forEach><br />
			</div>
			
			
			<div class="rank-lan">
				<span>언어별 소스 수</span><br/>
				<c:forEach items="${languages}" var="languages">
						<a href="/SourceMeister/opensource?srcId=${param.srcId}&q=${search}&langId=${languages.id}">${languages.language}: ${languages.count}</a><br>
				</c:forEach><br />
			</div>
			
		</div>
		
		<div id="middle">
		
			<div id="search">
			
				<br/><br/><br/>
				<form id="searchForm">

					<input id="keyword" name="search" type="text" value="${search}" /> 
					<input type="image" src="/SourceMeister/static/img/search-icon2.png" alt="Submit" width="40" height="40" style="position:relative; top:17px;">

				</form>
				
				<c:if test="${ not empty page}">
				
				<c:if test="${page == 1}">
				<a href="/SourceMeister/opensource?srcId=${param.srcId}&langId=${param.langId}&q=${search}&pageNum=${page - 1}">${page}</a>
				<span>${page+1}</span>
				<a href="/SourceMeister/opensource?srcId=${param.srcId}&langId=${param.langId}&q=${search}&pageNum=${page + 1 }">${page+2}</a>
				<a href="/SourceMeister/opensource?srcId=${param.srcId}&langId=${param.langId}&q=${search}&pageNum=${page + 2 }">${page+3}</a>
				<a href="/SourceMeister/opensource?srcId=${param.srcId}&langId=${param.langId}&q=${search}&pageNum=${page + 2 }">${page+4}</a>
				
				</c:if>
				<c:if test="${page == 0}">
				<span>${page+1}</span>
				<a href="/SourceMeister/opensource?srcId=${param.srcId}&langId=${param.langId}&q=${search}&pageNum=${page + 1 }">${page+2}</a>
				<a href="/SourceMeister/opensource?srcId=${param.srcId}&langId=${param.langId}&q=${search}&pageNum=${page + 2 }">${page+3}</a>
				<a href="/SourceMeister/opensource?srcId=${param.srcId}&langId=${param.langId}&q=${search}&pageNum=${page + 3 }">${page+4}</a>
				<a href="/SourceMeister/opensource?srcId=${param.srcId}&langId=${param.langId}&q=${search}&pageNum=${page + 4 }">${page+5}</a>
				
				</c:if>
				<c:if test="${page gt 1}">
				<a href="/SourceMeister/opensource?srcId=${param.srcId}&langId=${param.langId}&q=${search}&pageNum=${page - 1}">${page-1}</a>
				<a href="/SourceMeister/opensource?srcId=${param.srcId}&langId=${param.langId}&q=${search}&pageNum=${page}">${page}</a>
				<span>${page+1}</span>
				<a href="/SourceMeister/opensource?srcId=${param.srcId}&langId=${param.langId}&q=${search}&pageNum=${page + 1 }">${page+2}</a>
				<a href="/SourceMeister/opensource?srcId=${param.srcId}&langId=${param.langId}&q=${search}&pageNum=${page + 2 }">${page+3}</a>
				</c:if>
				
				</c:if>
					
			</div>
			
	
			<div class="login">
				<c:import url="/user/signIn" />
			</div>

			
		  
			<div id="middle-content">
				<jsp:include page="${includeUrl}"></jsp:include>
			</div>	
		
		</div>
		
		
		<div id="footer">
			 <span>Copyright </span>
		</div>	
		
		
</div>

		
		
</body>
</html>
			