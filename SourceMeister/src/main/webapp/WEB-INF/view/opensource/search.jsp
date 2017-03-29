<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$()
			.ready(
					function() {

						$("#searchBtn")
								.click(
										function() {

											$
													.post(
															"/SourceMeister/opensource",
															{

																"search" : $(
																		"#keyword")
																		.val()

															},
															function(response) {

																var jsonObj = JSON
																		.parse(response);

																var sources = jsonObj.sourceList;

																var tempDiv = $("#temp");

																for ( var i in sources) {
																	console
																			.log(sources[i].id);

																	var eachSource = $("<div id='" + sources[i].id +"'></div>");
																	eachSource
																			.text(sources[i].name);

																	tempDiv
																			.append(eachSource);

																}

															});

										});

					});
</script>

<title>검색</title>
</head>
<body>






	<form id="searchForm">
		<input id="keyword" type="text" /> <input id="searchBtn"
			type="button" value="검색" />
	</form>





	<jsp:include page="/WEB-INF/view/opensource/list.jsp"></jsp:include>


</body>
</html>