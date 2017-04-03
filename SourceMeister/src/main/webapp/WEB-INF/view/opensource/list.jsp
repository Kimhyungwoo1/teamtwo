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
		//댓글
		$(".detailLink > a").click(function(){
			
			var opensourceId = $(this).data("opensourceid");
			alert("[opensourceId]  " + opensourceId);
		    $(".reply").load("/SourceMeister/reply/list?opensourceId="+opensourceId);
		});
	});
</script>
<title>Insert title here</title>

</head>
<body>

	<p>총 ${count}개가 나왔습니다.</p>
	
	<div id=table-content>
	
	<table>
	
		<colgroup>
			<col span="1" style="width: 15%;">
			<col span="1" style="width: 85%;">
		</colgroup>
		
		<c:forEach items="${results}" var="result">

			<tr>
				<td> ${result.language} </td>

				<td>

					<div class="detailLink"><a data-opensourceid="${result.id}" href="/SourceMeister/opensource/detail?opensourceId=${result.id}">${result.name}---${result.repo }</a><br /> <br /> 

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

<div class="reply">
 <span>댓글</span>
 <jsp:include page="${ReplyUrl}" flush="false"></jsp:include>
</div>

</body>
</html>