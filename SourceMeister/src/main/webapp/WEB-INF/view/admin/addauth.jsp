<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function () {
		$("#insertAuth").find("#add").click(function() {
			$("#insertAuth").attr({
				"method":"post",
				"action":"/SourceMeister/auth/add"
			});
			$("#insertAuth").submit();
		});
		
		$("#delete").click(function(){
			$.post("/SourceMeister/auth/delete", {
				"authorizationId":$("#authId").val()
			}, function(){
			});
			location.reload();
		});
		
	});
</script>
</head>
<body>

	<div>
		<form id="insertAuth">
			<a>권한추가</a>
			<input type="text" name="authName" placeholder="권한이름을 입력하세요." />
			<input type="button" id="add" value="확인" />
			<a>권한 삭제</a>
		<select id="deleteSelect" name="authBefore">
				<option value="">권한없음</option>
				<c:forEach items="${authList}" var="authList">
					<option id="authId" value="${authList.authorizationId}">${authList.authorizationName}</option>
				</c:forEach>
		</select>
			<input type="button" id="delete" value="확인"/>
		</form>
				
	</div>

</body>
</html>