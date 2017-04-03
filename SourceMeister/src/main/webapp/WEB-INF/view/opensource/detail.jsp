<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="/SourceMeister/static/css/detail_layout.css"/>
<title>${fileName} in ${repoName} | source code search engine</title>
<script type="text/javascript" src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		
		$("#likeBtn").click(function() {
			$.post("/SourceMeister/opensource/detail/likeCount", {
				"opensourceId" : $("#likeBtn").data("opensourceid"),
				"likeCount" : $("#likeCount").text()
			}, function(response) {
				var jsonObj = JSON.parse(response);
				console.log(jsonObj);

				if (jsonObj.success) {
					$("#likeCount").text(jsonObj.likeCount);
				} 
			});
		});
		
		$("#fileTreeBtn").click(function() {
			if ($(".fileTree").css("display") == "none") {
				$('.fileTree').css("display", "block");
			} else {
				$('.fileTree').css("display", "none");
			}
		});
	});
</script>
</head>
<body>

	<div class="container">
		
		<div class="row">
			<div class="title">
				<h4 class="codepath">
					<a href="${sourceUrl}">${repoName}</a>
					${location}/${fileName}
				</h4>
			</div>

			<input type="hidden" id="sourceUrl" value="${sourceUrl}">
			<input type="hidden" id="repoName" value="${repoName}">
			<div class="header">
				<div style="display:inline-block;">
					<table class="table">
						<tbody>
							<tr>
								<th>MD5 Hash</th>
								<td>${md5hash}</td>
							</tr>
							<tr>
								<th>Repository</th>
								<td><a href="${sourceUrl}">${sourceUrl}</a></td>
							</tr>
						</tbody>
					</table>
				</div><!-- 
			 --><div id="likeBtn" style="display:inline-block;" data-opensourceid="${opensourceId}">
			 		<img style="vertical-align:middle; width:50px; height:50px;" src="http://branding.daegu.com/images/icon--thumb.png"/>
			 		<p id="likeCount">${likeCount}</p>
			 	</div>
			</div><br/>

			<div class="body">
				<textarea readonly="readonly" style="resize: none; wrap:hard;" cols="110" rows="20">${code}</textarea>
			</div><br/>
			
			<a id="fileTreeBtn">Show File Tree</a><br/><br/>
			<div class="fileTree" style="display:none;">
				${fileTree}
			</div><br/><br/>
			
		</div>

	</div>
</body>
</html>
