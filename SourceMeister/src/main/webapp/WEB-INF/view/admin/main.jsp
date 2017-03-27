<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 페이지</title>
</head>
<body>

	<table>
		<tr>
			<td>ID</td>
			<td>이름</td>
			<td>닉네임</td>
			<td>성별</td>
			<td>이메일</td>
			<td>권한</td>
		</tr>
	</table>
	
	<jsp:include page="/WEB-INF/view/admin/addauth.jsp"/>
	
	<form id="allAuth">
		<select name="authBefore">
			<option value="">권한없음</option>
			<c:forEach items="${authList}" var="authList">
				<option value="${authList.authorizationId}">${authList.authorizationName}</option>
			</c:forEach>
		</select>
		<span>을</span>
		<select name="authAfter">
			<option value="">권한없음</option>
			<c:forEach items="${authList}" var="authList">
				<option value="${authList.authorizationId}">${authList.authorizationName}</option>
			</c:forEach>
		</select>
		<span>로</span>
		<input type="button" value="변경"/>
	</form>
	
</body>
</html>