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
		
	
	$("#searchForm").find("input[type=button]").click(function() {
		submitForm();
	});
	
	
	function submitForm() {
		$("#searchForm").attr({
			"action" : "/SourceMeister/opensource",
			"method" : "post"
		}).submit();
	}
	
<<<<<<< HEAD
	//댓글
	//$("#middle-content > .detailLink > a").click(function(){
	$("#middle-content > .detailLink").click(function(){
		$("#reply").load("/SourceMeister/reply/list");
		
		var opensourceId =  $(this).data("opensourceid")
		
		alert("opensourceId ?? " + opensourceId);
		/* 
		$.post("/SourceMeister/reply/list",{
			"openSourceId" : $(this).data("opensourceid")
		},function(response){
			if (response == 'OK') {
				 writeDiv.load("/SourceMeister/reply/list");
				//화면 새로고침
				// location.reload(); 
			} else {
				alert("댓글 목록 load 실패 했습니다.\n관리자에게 문의하세요");
			}
		});   */
	});	
=======
	 var myHilitor = new Hilitor("#table-content");
	  myHilitor.apply($("#keyword").val());
	  

	
>>>>>>> 27fefc580217010cea4e64a2492d9c5d6a3319fd
	
	
});


 

</script>


<title>검색</title>
</head>
<body>

	<div id="container">
		
		<div id="left">
			<div class="login">
				<c:import url="/user/signIn" />
			</div>
			
			<div class="rank-repo" style="overflow-y: auto">
				<span>저장소별 소스 수</span><br/>
				<c:forEach items="${sources}" var="sources">

				<a href="/SourceMeister/opensource?langId=${param.langId}&q=${search}&srcId=${sources.id}">${sources.source}: ${sources.count}</a><br>

				</c:forEach><br />
			</div>
			
			
			<div class="rank-lan" style="overflow-y: auto">
				<span>언어별 소스 수</span><br/>
				<c:forEach items="${languages}" var="languages">
						<a href="/SourceMeister/opensource?srcId=${param.srcId}&q=${search}&langId=${languages.id}">${languages.language}: ${languages.count}</a><br>
				</c:forEach><br />
			</div>
			
		</div><div id="middle">
		
			<div id="search">
				<br/><br/><br/><br/>
				<form id="searchForm">
					<input id="keyword" name="search" type="text" value="${search}" /> <input type="button" value="검색" />
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
			
			<div id="middle-content">
				<jsp:include page="${includeUrl}"></jsp:include>
			</div>	
			
		</div>
		
		<div id="right">
			<div id="searchRank">
				<span>검색어 순위</span> <br/>
				
				<c:forEach items="${result.langArr}" var="lang">
						${lang.language}: ${lang.count }<br/>
				</c:forEach><br />
				
			</div>
			
		<%-- 	<div id="reply">
				<span>댓글</span>
				<jsp:include page="${ReplyUrl}" flush="false"></jsp:include>
			</div> --%>
		</div>
		<div id="footer">
			 <span>Copyright </span>
		</div>	
		
		
</div>

		
		
</body>
</html>