<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/SourceMeister/static/css/list_layout.css" />
<!-- <link rel="stylesheet" type="text/css" href="/SourceMeister/static/css/search_layout.css" /> -->
<script type="text/javascript">
$().ready(function() {
	$("tr").click(function(){
		var opensourceId = $(this).data("opensourceid");
		$("#list").hide();
		 $("#detail").load("/SourceMeister/opensource/detail?opensourceId="+opensourceId,{},function(){
			 $("#replyMain").load("/SourceMeister/reply/list?opensourceId="+opensourceId); 
		 });
	});
	
	$('tr:odd').addClass('odd');
	$('tr:even').addClass('even');

});


</script>
<title>Insert title here</title>

</head>
<body>

<div id="list">
	<p>총 ${count}개가 나왔습니다.</p>

	<div id=table-content>

		<table id="listTable" style="border-collapse:collapse;">


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

	</div>

</div>

<div id="detail"></div><hr/>
<div id="replyMain"></div>

</body>
</html>