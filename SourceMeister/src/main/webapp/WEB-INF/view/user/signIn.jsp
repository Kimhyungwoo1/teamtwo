<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<<<<<<< HEAD
=======
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
>>>>>>> jun
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 페이지</title>
<script type="text/javascript" src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
 $().ready(function(){
	 $("#signInForm").find("input[type=button]").click(function(){
		$("#signInForm").attr({ 
			"method": "post" ,
			"action" : "/SourceMeister/user/doSignIn" 
		});
		 
		$("#signInForm").submit();
	 
	 });
	 
 });

</script>
</head>
<body>
<<<<<<< HEAD
	<h3>로그인 페이지</h3>
	<hr />
	<form id="signInForm">
=======
	


		 <c:if test="${empty sessionScope._USER_ }">
		 
		<form id="signInForm">
>>>>>>> jun
		<div class="userInfo">
		<input type="text" name="userId" id="userId" placeholder="아이디를 입력하세요" /><br />
		<input type="password" name="userPassword" placeholder="비밀번호를 입력하세요" /><br />
		</div>
		<div class="btn">
		<input type="button" value="로그인"  id="login" /> 
		</div>
		<div class="userHelp" >
		<a href="/SourceMeister/user/signUp"><input type="button" value="회원가입" /></a>
		</div>
	</form>
<<<<<<< HEAD
=======
		</c:if>
		
		
		
		<c:if test="${not empty sessionScope._USER_ }">
				${sessionScope._USER_.userName}님, 환영합니다! <a href="/SourceMeister/user/doSignOut">로그아웃</a>
		</c:if> 
		
			
	 
	
>>>>>>> jun

</body>
</html>