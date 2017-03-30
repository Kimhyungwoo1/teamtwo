<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" type="text/css" href="/SourceMeister/static/css/admin.css"/>
<script type="text/javascript" src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
</script>
<body>
<div id=total>
	<div id=top>
		<div id=login>
			SourceMeister 관리자님 환영합니다.
		</div>
		<div id=adminManu>
			관리자 메뉴
		</div>
	</div><!--
	--><div id=botton>
	
		<table id=table>
			<tr>
				<td>ID</td>
				<td>이름</td>
				<td>닉네임</td>
				<td>성별</td>
				<td>이메일</td>
				<td>권한</td>
			</tr>
			
		</table>
		<div id=add>
			<jsp:include page="/WEB-INF/view/admin/addauth.jsp"/>
		</div>
		<form id="allAuth">
			<a>권한 일괄 변경</a>
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
	</div>
	<div id=writer> copy writer Source Meister</div>
</div>
	
</body>
</html>