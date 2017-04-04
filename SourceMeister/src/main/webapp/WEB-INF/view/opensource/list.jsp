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
<<<<<<< HEAD

	$().ready(
			function() {
				//댓글
				$(".detailLink > a").click(
						function() {

							var opensourceId = $(this).data("opensourceid");
							alert("[opensourceId]  " + opensourceId);
							$(".reply").load(
									"/SourceMeister/reply/list?opensourceId="
											+ opensourceId);
						});
			});

=======
	$().ready(function() {
		$("tr").click(function(){
			var opensourceId = $(this).data("opensourceid");
			console.log(opensourceId);
			$("#list").hide();
		    $("#detail").load("/SourceMeister/opensource/detail?opensourceId="+opensourceId); 
		    $("#reply").load("/SourceMeister/reply/list?opensourceId="+opensourceId); 
		});
		
	});

>>>>>>> ehm
</script>
<title>Insert title here</title>

</head>
<body>

<div id="list">
	<p>총 ${count}개가 나왔습니다.</p>

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
<<<<<<< HEAD
	</div>
======= --%>
	
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
					<div class="detailLink" ><a data-opensourceid="${result.id}"  href="/SourceMeister/opensource/detail?opensourceId=${result.id}" >${result.name}---${result.repo }</a><br /> <br /> 
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



	</table>
</div>
=======
>>>>>>> 27fefc580217010cea4e64a2492d9c5d6a3319fd

	</div>
</div>
<div id="detail"></div>
<div id="reply"></div>

</body>
</html>