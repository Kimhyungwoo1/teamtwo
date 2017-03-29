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
	
</script>
<title>Insert title here</title>
</head>
<body>

	<p>총 ${count}개가 나왔습니다.</p>

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
							<c:forEach items="${result.lines }" var="line">
								<div>
									<xmp>${line.value }</xmp>
								</div>
							</c:forEach>
						</div></a><br /> <br /></td>




			</tr>
		</c:forEach>



	</table>





</body>
</html>