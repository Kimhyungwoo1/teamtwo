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
		$("#fileTree").load();
	});
</script>
</head>
<body>

	<nav class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			This is a navbar!
		</div>
	</nav>


	<div class="container">

		<div class="search-options">
			<form action="searchForm">
				<div class="form-inline">
					<input size="50" placeholder="Search Expression" type="text" name="search" value=""/>
					<input type="submit" value="search"/>
				</div>
			</form>
		</div>
		
		<div class="row">
			<div class="title">
				<h4 class="codepath">
					<a href="${sourceUrl}">${repoName}</a>
					${location}/${fileName}
				</h4>
			</div>

			<div class="header">
				<table class="table">
					<tbody>
						<tr>
							<th>MD5 Hash</th>
							<td>${md5hash}</td>
						</tr>
						<tr>
							<th>Repository</th>
							<td><a href="/${sourceUrl}">${sourceUrl}</a></td>
						</tr>
						<!-- <tr>
							<td colspan="5">
								<a href="#" id="file-tree-link"> 
									<span id="file-tree-button" data-id="9911">View File Tree</span>
								</a>
							</td>
						</tr> -->
					</tbody>
				</table>
			</div><br/>

			<div class="body">
				<textarea readonly="readonly" style="resize: none; wrap:hard;" cols="110" rows="20">${code}</textarea>
			</div><br/>
			
			<div class="fileTree">
				${fileTree}
			</div>

			<div class="footer">
			</div>
			
		</div>

	</div>
</body>
</html>
