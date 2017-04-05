<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">

	$().ready(function() {
		$("tr").click(function(){
			var opensourceId = $(this).data("opensourceid");
			$("#list").hide();
			 $("#detail").load("/SourceMeister/opensource/detail?opensourceId="+opensourceId,{},function(){
				 $("#replyMain").load("/SourceMeister/reply/list?opensourceId="+opensourceId); 
			 });
		});
	});

</script>
<title>Insert title here</title>

</head>
<body>

<div id="list">
	<p>총 ${count}개가 나왔습니다.</p>

<<<<<<< HEAD
	
	<div id=table-content>
	
	<table>
	
		<colgroup>
			<col span="1" style="width: 15%;">
			<col span="1" style="width: 85%;">
		</colgroup>
		
		<c:forEach items="${results}" var="result">
			<tr>
				<td>
					<c:forEach items="${result.langArr}" var="lang" begin="0" end="2">
						${lang.language}:${lang.count }<br>
					</c:forEach><br />
				</td>
				<td>
					<div id="detailLink"><a href="/SourceMeister/opensource/detail?opensourceId=${result.id}">${result.name}---${result.repo }</a><br /> <br /> 
							<c:forEach items="${result.lines }" var="line">
								<div>
									<span>${line.value}</span>
								</div>
							</c:forEach>
					</div>
					
					<br/>
					<br/>
				</td>
			</tr>
		</c:forEach>

=======
	<div id=table-content>

		<table border="1">

			<colgroup>
				<col span="1" style="width: 15%;">
				<col span="1" style="width: 85%;">
			</colgroup>

			<c:forEach items="${results}" var="result">

				<tr style="cursor: pointer;"  data-opensourceid="${result.id}">
					<td>${result.language}</td>

					<td>
						<div id="detailLink">
							<span >${result.name}---${result.repo }</span><br />
							<c:forEach items="${result.lines }" var="line">
								
								<c:out value="${line.value}" escapeXml="true" />
									
								
							</c:forEach>
						</div> <br /> <br />
					</td>
				</tr>
			</c:forEach>

		</table>
>>>>>>> jun

	</div>

</div>
<div id="detail"></div><hr>
<div id="replyMain"></div>

</body>
</html>