<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<<<<<<< HEAD
<link rel="stylesheet" type="text/css"
	href="/SourceMeister/static/css/list_layout.css" />
=======
>>>>>>> 3a4247aa481e0491c076340a8833dd32814a9466
<script type="text/javascript"
	src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	
</script>
<title>Insert title here</title>
</head>
<body>

	<p>총 ${count}개가 나왔습니다.</p>
	<div id="table-content">
		<table>
			<colgroup>
				<col span="1" style="width: 50%;">
				<col span="1" style="width: 50%;">
			</colgroup>
			<c:forEach items="${results}" var="result">
				<tr>

<<<<<<< HEAD
					<td><c:forEach items="${result.langArr}" var="lang" begin="0"
							end="2">
							<div>${lang.language}:${lang.count }</div>
						</c:forEach><br /></td>
					<td><a
						href="/SourceMeister/opensource/detail?opensourceId=${result.id}">
						<div id="detailLink">${result.name}---${result.repo }<br /> <br />
=======
	<table>
		<colgroup>
			<col span="1" style="width: 50%;">
			<col span="1" style="width: 50%;">
		</colgroup>
		<c:forEach items="${results}" var="result">
			<tr>

				<td><c:forEach items="${result.langArr}" var="lang" begin="0"
						end="2">
						<div>${lang.language}:${lang.count }</div>
					</c:forEach><br /></td>
				<td><a
					href="/SourceMeister/opensource/detail?opensourceId=${result.id}"><div
							id="detailLink">${result.name}---${result.repo }<br /> <br />
>>>>>>> 3a4247aa481e0491c076340a8833dd32814a9466
							<c:forEach items="${result.lines }" var="line">
								<div>
									<xmp>${line.value }</xmp>
								</div>
							</c:forEach>
						</div></a><br /> <br /></td>
<<<<<<< HEAD
=======

>>>>>>> 3a4247aa481e0491c076340a8833dd32814a9466




				</tr>
			</c:forEach>



		</table>
	</div>




</body>
</html>