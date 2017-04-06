<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 페이지</title>
<script type="text/javascript"
	src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/SourceMeister/static/css/index_layout.css" />
<script type="text/javascript">
	$().ready(function() {
		$("#loginBtn").click(function() {
			$("#signInForm").attr({
				"method" : "post",
				"action" : "/SourceMeister/user/doSignIn"
			});
			$("#signInForm").submit();
		});
	});
</script>
<style type="text/css">
#loginBtn {
	font-size: 12px;
	font-family: Arial;
	font-weight: normal;
	height: 30px;
	border: 1px solid #dcdcdc;
	background: #f9f9f9;
	border-radius: 5px;
	margin-top: -35px;
	
	width: 24.5%;
	text-align: center;
	border: 1px solid #bcbcbc;
	margin-right: 20px;
	float: right;
}

#userInfo {
	margin-right: 60px;
}

*::-webkit-input-placeholder {
	color: gray;
	font-size: 12px;
	
}

#userHelp {
	margin-right: 132px;
}
</style>


</head>
<body>
	<h3>로그인 페이지</h3>
	<hr />
				

	<c:if test="${empty sessionScope._USER_ }">

		<form id="signInForm">
			<div id="userInfo">
				<input type="text" name="userId" id="userId"
					placeholder="아이디를 입력하세요" size="14"  /><br />  
					<input
					type="password" name="userPassword" id="userPassword"
					placeholder="비밀번호를 입력하세요" size="14"  /><br />
			</div>
			<br>
				<input id="loginBtn" type="button" value="로그인" />

			<div id="userHelp">
				<a href="/SourceMeister/user/signUp"> 회원가입</a>
			</div>
		</form>

	</c:if>

	<c:if test="${not empty sessionScope._USER_}">
		${sessionScope._USER_.userName}님, 환영합니다! <br/>
		<a href="/SourceMeister/user/doSignOut">로그아웃</a>
		<a href="/SourceMeister/user/signUpModify?userId=${sessionScope._USER_.userId}">회원정보수정</a>
	</c:if>
	<br/>
	<c:if test="${isAdminUser}" >
		<a href="/SourceMeister/authorizationadmin">관리자 페이지</a>
	</c:if>



</body>
</html>