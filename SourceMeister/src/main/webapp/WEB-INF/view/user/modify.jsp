<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보수정</title>
<script type="text/javascript"
	src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
	
		$("#modifySignUpForm").find("input[type=button]").click(function() {
		if ($("#nickName").val() == "") {
			alert("닉네임을 입력해주세요");
			$("#nickName").focus();
			return;
		}

		if ($("#userPassword").val() == "") {
			alert("비밀번호를 입력해 주세요");
			$("#userPassword").focus();
			return;
		}

		if ($("#userPassword").val().length < 7) {
			alert("문자와 숫자를 포함해서 비밀번호는 8자 이상 입력해주세요.");
			$("#userPassword").focus();
			return;
		}
		var email = $('#email');
		var CheckEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

		if (email.val() == "") {
			alert('이메일주소를 입력 해 주세요');
			email.focus();
			return;

		} else {
			if (!CheckEmail.test(email.val())) {
				alert('이메일 주소가 유효하지 않습니다');
				email.focus();
				return;
			}
		}

		$("#modifySignUpForm").attr(
						{
							"method" : "post",
							"action" : "/SourceMeister/user/doSignUpModify?userId=${param.userId}"
						});
		$("#modifySignUpForm").submit();

	});
		
		
	$("#drop_out").click(function(){
		//confirm을 넣으면 해당 질문과 함께 yes , no 메뉴창이 나온다.
		if(confirm("정말 탈퇴하시겠습니까?")){
			$("#dropout").attr( {
				"method" : "post" ,
				"action" : "/SourceMeister/user/doDelete?userId=${param.userId}"
	 		});
			$("#dropout").submit();

			
		}else{
			return;
		}
		
			});
		
		
});	
											
</script>
</head>
<body>
		<h1>회원정보수정 페이지</h1>
		
		<hr/>
	<form id="modifySignUpForm">
		<div class="login">
		<c:if test="${ empty sessionScope._USER_}">
			<jsp:include page="/WEB-INF/view/user/signIn.jsp" />
		</c:if>
		<c:if test="${ not empty sessionScope._USER_}">
			${sessionScope._USER_.userName}님, 환영합니다!
		</c:if>
	</div> <br/>

		<span>유저이름</span> <br /> 
		<input type="text" name="userName" value="${userVO.userName} " />  <br/>
		
		<span>이메일</span> <br /> 
		<input type="text" name="email" value="${userVO.email }" id="email" />  <br/>
		
		<span>닉네임 </span><br /> 
		<input type="text" name="userNickName" id="nickName" value="${userVO.nickName}" /><br /> 
		
		<span>비밀번호</span> <br />
		<input type="password" name="userPassword" id="userPassword" value="${userVO.password }" /><br /><br/>  
		
		<input type="button" value="정보수정"  /> <br />
		
	</form> <br/>
	
	<form id="dropout">
	
	<input type="button" value="회원탈퇴" id="drop_out"/> 
	
	</form>
	

</body>
</html>