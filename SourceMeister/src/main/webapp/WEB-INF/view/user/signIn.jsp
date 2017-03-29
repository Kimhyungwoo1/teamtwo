<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 페이지</title>
<script type="text/javascript" src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {

		$("#signInForm").click(function() {
			$("#signInForm").attr({
				"method" : "post",
				"action" : "/SourceMeister/user/signIn"
			});
			$("#signInForm").submit();
		});

	});
</script>

</head>
<body>
	<h3>로그인 페이지</h3>
	<hr />
	<form id="signInForm">
		<input type="text" name="userId" id="userId" placeholder="아이디를 입력하세요" /><br />
		<input type="password" name="userPassword" placeholder="비밀번호를 입력하세요" /><br />
		<input type="button" value="로그인" /> <a href="/SourceMeister/user/signUp"><input type="button" value="회원가입" /></a>
	
	</form>

</body>
</html>