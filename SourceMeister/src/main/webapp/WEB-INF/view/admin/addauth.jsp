<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="/SourceMester/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function () {
		$("#insertAuth").find("input[type=button]").click(function() {
			$("#insertAuth").attr({
				"method" : "post",
				"action" : "/SourceMeister/auth/add"
			})
			$("#insertAuth").submit;
		});
		
	});
</script>
</head>
<body>

	<div>
		<form id=insertAuth>
			<input type="text" name="authName" palceholder="권한이름을 입력하세요." />
			<input type="button" value="확인" />
		</form>
	</div>

</body>
</html>