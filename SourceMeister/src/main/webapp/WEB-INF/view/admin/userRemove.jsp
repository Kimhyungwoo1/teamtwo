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
		$("#delete").click(function() {
			$.post("/SourceMeister/user/delete", {
				"userId":$("#userId").val()
			}, function(){
			});
			location.reload();
		});
	});
</script>
</head>
<body>

	<!-- $("#removeUser").attr({
				"method":"post",
				"action":"/SourceMeister/user/delete"
			});
			$("#removeUser").submit();
		 -->
	<form id="removeUser">
	<a>유저 삭제</a>
	<!--<select id="deleteUser" name="authBefore">
				 <option value="">선택하세요</option>
				<c:forEach items="${userList}" var="userList">
					<option id="userId" value="${userList.userId}">${userList.userName}</option>
				</c:forEach> 
		</select>-->
			<input type="button" id="delete" value="확인"/>
	</form>

</body>
</html>