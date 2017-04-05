<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 페이지</title>
<style type="text/css">
		@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);
		body {font-family: 'Jeju Gothic', sans-serif;}
</style>
<link rel="stylesheet" type="text/css" href="/SourceMeister/static/css/admin.css"/>
<script type="text/javascript" src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function () {
		$("#delete").click(function() {
			$.post("/SourceMeister/user/delete", {
				"userId":$("#userId").val()
			}, function(){
			});
			location.reload();
		});
		
	});
</script>
<body>
<div id=total>
	<div id=top>
		<div id=login>
			SourceMeister 관리자님 환영합니다.
		</div>
		<div id=adminManu>
			관리자 메뉴<br/>
			<a href="/SourceMeister/authorizationadmin">1. 권한</a><br/>
			<a href="/SourceMeister/useradmin">2. 유저</a>
		</div>
	</div><!--
	--><div id=botton>
	<div id=table style="text-align: center">
	<span style="font-size: 20pt">User List</span>
		<table id=tableContent>
					<tr>
					<td></td>
					<td width="120">ID</td>
					<td width="120">이름</td>
					<td width="120">닉네임</td>
					<td width="120">성별</td>
					<td width="120">이메일</td>
					<td width="120">권한</td>
					</tr>
					<c:forEach items="${userList}" var="user">
					<tr>
						<td><input type="checkbox" name="authCheck" value="${user.userId}" form="removeUser"/></td>
						<td>${user.userId}</td>
						<td>${user.userName}</td>
						<td>${user.nickName}</td>
						<td>${user.gender}</td>
						<td>${user.email}</td>
						<td>${user.authorizationVO.authorizationName}</td>
					</tr>
					</c:forEach>
			</table>
		</div>
		<!-- <select id="deleteUser" name="authBefore">
						 <option value="">선택하세요</option>
						<c:forEach items="${userList}" var="userList">
							<option id="userId" value="${userList.userId}">${userList.userName}</option>
						</c:forEach> 
				</select> -->
		<div id=add>
			<form id="removeUser">
				<a>유저 삭제</a>
					
					<input type="button" id="delete" value="확인"/>
				</form>
		</div>
		<form id="allAuth">
			<!-- <a>권한 변경</a>
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
			<input type="button" value="변경"/> -->
		</form>
	</div>
	<div id=writer> copy writer Source Meister</div>
</div>
	
</body>
</html>