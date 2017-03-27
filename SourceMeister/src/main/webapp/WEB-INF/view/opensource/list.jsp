<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	
	<br />

	<p>총 ${count}개가 나왔습니다.</p>

	<c:forEach items="sourceList" var="source">
	
	${source.name}
	
	</c:forEach> 
</body>
</html>