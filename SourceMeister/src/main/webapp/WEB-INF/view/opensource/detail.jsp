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
		var loginCheck = "${ sessionScope._USER_}" ;
		$("#likeBtn").click(function() {

			$.post("/SourceMeister/opensource/detail/likeCount", {
				"opensourceId" : $("#likeBtn").data("opensourceid"),
				"likeCount" : $("#likeCount").text()
			}, function(response) {
				var jsonObj = JSON.parse(response);
				console.log(jsonObj);
				if (loginCheck) {
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
				}
				else {
					alert("로그인해주세요.");
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
		 
	//////////////////////////[ReplyStart]/////////////////////////////
		//삭제
		$(".delete").click(function(){
			/*  alert("삭제" + $(this).data("replyid")); */
			$.post("/SourceMeister/reply/delete",{
				"replyId" :$(this).data("replyid")
			}, function(response){
				if (response == 'OK') {
					//화면 새로고침
					location.reload();
				} else {
					alert("아티스트 등록을 실패 했습니다.\n관리자에게 문의하세요");
				}
			});
		});	

		//댓글
		$("#writeReplyBtn").click(function(){
			//validation check
			if ( $("#comment").val() == ""){
				alert("댓글 내용을 입력하지 않았습니다.");
				$("#comment").focus();
				return;
			}
			
			$.post("/SourceMeister/reply/write",{
				"opensourceId" : $("#openSourceId").val(),
				"comment" : $("#comment").text(),
				"parentReplyId" : $("#parentReplyId").val()
			},function(response){
				if (response == 'OK') {
					//화면 새로고침
					location.reload();
				} else {
					alert("댓글 등록을 실패 했습니다.\n관리자에게 문의하세요");
				}
			});
		});	
		//대댓글
		$(".formAppender").on("click","#writeReplyBtn",function(){
			//validation check
			var thisForm = $(this).parent().parent();
			
			if ( thisForm.find("textarea").val() == ""){
					alert("대댓글 내용을 입력하지 않았습니다.");
					thisForm.find("textarea").focus();
					return;
			} 
			
			
			$.post("/SourceMeister/reply/write",{
				"opensourceId" : $("#writeReplyBtn").val(),
				"comment" : $("#writeReplyBtn").data(),
				"parentReplyId" : $("#writeReplyBtn").val()
			},function(response){
				if (response == 'OK') {
					//화면 새로고침
					location.reload();
				} else {
					alert("대댓글 등록을 실패 했습니다.\n관리자에게 문의하세요");
				}
			});
			
			
		});
		
		$(".ReReply").click(function(){
			var replyId = $(this).data("replyid");	
			var form = $("#formWrapper").html();
			var thisDiv = $(this).closest(".reply").children("div");
			
			thisDiv.children("form").remove();	
			thisDiv.append(form);
			thisDiv.children("form").find("input[id=parentReplyId]").val(replyId);
			thisDiv.children("form").attr({id:"writeReReply"});
			thisDiv.children("form").attr({class:"writeReReplyForm"});
			thisDiv.toggle();
		 	
			$(".reply").each(function (index, data) {
				
				if( $(this).data("parent") == replyId ) {
					$(this).toggle();
					//대댓글에는 글 등록 불가
					$(this).find("td[class=ReReply]").remove();
				}
			}); 
		});
		
		//////////////////////////[ReplyEND]/////////////////////////////
		
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
							<!-- <tr>
								<td colspan="5">
									<a href="#" id="file-tree-link"> 
										<span id="file-tree-button" data-id="9911">View File Tree</span>
									</a>
								</td>
							</tr> -->
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
			
			<div class="fileTree">
				${fileTree}
			</div>

			<div class="footer">
			</div>
			
		</div>

	</div>
	<div id="replyDiv" style="width:220px;"  ><!-- align="center" -->
       
	<%-- 총 ${totalcnt}건 --%>
       	<c:forEach items="${replyList}" var="reply" varStatus="index">
      
            <div class="reply"  data-parent="${reply.parentReplyId }" data-click="0" 
               <c:if test="${reply.level==2}"> style="margin-left: ${reply.level * 10 }px; display:none;" </c:if> 
			>
			    <table id="replyTable" border="1"><!--  border="1" -->
                	<tr style="color: fuchsia;">
                		<td style="width:170px;">${reply.user.nickName}</td>
                	</tr>
                    <tr>
                        <td>
                            ${reply.comment}
                        </td>
                        <!-- TODO -->
						<c:if test="${reply.user.userId eq 'TEST'}">
						<td class="delete" style="cursor: pointer; background:lime;" data-replyid="${reply.replyId }">삭제</td>
						</c:if>
                   
                    </tr>
                    <tr>
                	 	<td style="width:30px;">${reply.writeDate}</td>
                	 	<td class="ReReply" style="cursor: pointer; background:lime; " data-replyid="${reply.replyId }">답글${reply.childCnt}</td>
                	</tr>
                  </table>
				  <div class="formAppender" style="margin-left: ${reply.level * 2 * 10 }px; display:none;" ></div> 
            </div>
        </c:forEach>
        
      <%--  <div>
			<form id="searchForm">
				${pager}
			</form>
		</div>  --%>
      
        <div id="formWrapper" style="width:220px; position : relative; bottom :-20px; left:5px;" >            
            <form class="writeReplyForm">
            <!-- XX01 수정할것 -->
                <input type="hidden" id="openSourceId" name="openSourceId" value="${opensourceId}"/>
                <input type="hidden" id="parentReplyId" name="parentReplyId" value="" />
                <textarea id="comment" name="comment" style="width:150px;"></textarea>
                <input type="button" id="writeReplyBtn" value="등록" />    
            </form>
        </div>
         
    </div>
	
	
</body>
</html>
