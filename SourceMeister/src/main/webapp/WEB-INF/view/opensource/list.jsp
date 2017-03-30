<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">


</script>
<title>Insert title here</title>
</head>
<body>

	<p>총 ${count}개가 나왔습니다.</p>

	<table>

		<c:forEach items="${results}" var="result">
			<tr>

				<td><c:forEach items="${result.langArr}" var="lang">
				<div>${lang}</div>
				</c:forEach><br/></td>
				<td><a href="/SourceMeister/opensource/detail?=${result.id}"><div id="detailLink">${result.name}---${result.repo }<br/><br/>
				<c:forEach items="${result.lines }" var="line">
											<div>${line.value }</div>
											</c:forEach></div></a><br/><br/></td>

			</tr>
		</c:forEach>
	


	</table>
		<jsp:include page="/WEB-INF/view/user/signIn.jsp" />

		

</body>
</html>