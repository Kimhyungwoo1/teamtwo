<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/SourceMeister/static/css/reply_layout.css" />
<script type="text/javascript">
	$().ready(function(){
		//삭제
		$(".delete").click(function(){
			
			//validation check
			if($("#replyDiv").data("user") ==  ""){
				alert("로그인 후 사용할수 있습니다.");
				$("#comment").focus();
				return;
	    	} 
			
			$.post("/SourceMeister/reply/delete",{
				"replyId" :$(this).data("replyid")
			}, function(response){
				if (response == 'OK') {
					$("#replyMain").load("/SourceMeister/reply/list?opensourceId="+ $("#openSourceId").val());
				} else {
					alert("댓글 삭제를 실패 했습니다.\n관리자에게 문의하세요");
				}
			});
		});	

		//등록
			$("#writeReplyBtn").click(function(){
				//validation check
				if ( $("#comment").val() == ""){
					alert("댓글 내용을 입력하지 않았습니다.");
					$("#comment").focus();
					return;
				}
				 if($("#replyDiv").data("user") ==  ""){
					alert("로그인 후 사용할수 있습니다.");
					$("#comment").focus();
					return;
				} 
				$.post("/SourceMeister/reply/write",{
					'openSourceId' : $("#openSourceId").val(),
					'parentReplyId' : $("#parentReplyId").val(),
					'comment' : $("#comment").val()
				}, function(response){
					if (response == 'OK') {
						$("#replyMain").load("/SourceMeister/reply/list?opensourceId="+ $("#openSourceId").val());
					} else {
						alert("댓글 삭제를 실패 했습니다.\n관리자에게 문의하세요");
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
			
			if($("#replyDiv").data("user") ==  ""){
					alert("로그인 후 사용할수 있습니다.");
					$("#comment").focus();
					return;
		    } 
			$.post("/SourceMeister/reply/write",{
				'openSourceId' : thisForm.find('#openSourceId').val(),
				'parentReplyId' : thisForm.find('#parentReplyId').val(),
				'comment' : thisForm.find('#comment').val()
			}, function(response){

				if (response == 'OK') {
					$("#replyMain").load("/SourceMeister/reply/list?opensourceId="+ $("#openSourceId").val());
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
			thisDiv.children("form").attr({style:"padding-left: 40px; text-align: left;"});
			thisDiv.toggle();
		 	
			$(".reply").each(function (index, data) {
				
				if( $(this).data("parent") == replyId ) {
					$(this).toggle();
					//대댓글에는 글 등록 불가
					$(this).find("td[class=ReReply]").remove();
				}
			}); 
		});
    });
</script>
<span id="replyTxt" >댓글</span>
<div id="replyDiv" data-user="${sessionScope._USER_.userId}">
	<div id="formWrapper" >
		<form class="writeReplyForm" >
			<!-- XX01 수정할것 -->
			<input type="hidden" id="openSourceId" name="openSourceId"value="${opensourceId}" /> 
			<input type="hidden" id="parentReplyId" name="parentReplyId" value="" />
			<textarea id="comment" name="comment" style="width: 500px; text-align: center;"></textarea>
			<input type="button" id="writeReplyBtn" value="등록"  style="position: relative; top: -15px"/>
		</form>
	</div>
	<hr>
	<c:forEach items="${replyList}" var="reply" varStatus="index">
		<div class="reply" data-parent="${reply.parentReplyId }" data-click="0" align="left" 
			<c:if test="${reply.level==2}"> style="margin-left: ${reply.level * 4 * 10 }px; display:none;" </c:if>
		>

			<table id="replyTable" > 

				<tr >
					<td class="nickName"><p style="font-weight: bold;">${reply.user.nickName}</p></td>
				</tr>
				<tr>
					<td class="comment">${reply.comment}</td>
				</tr>
				<tr >
					<td class="writeDate" ><p style="color: #acadaf ">${reply.writeDate}</p></td>
				</tr>
				<tr>
					<td class="ReReply" data-replyid="${reply.replyId }">답글${reply.childCnt}</td>
					<c:if test="${reply.user.userId eq sessionScope._USER_.userId}">
						<td class="delete" data-replyid="${reply.replyId }">삭제</td>
					</c:if>	
				</tr>
			</table>
			<div class="formAppender" style="margin-left: ${reply.level * 4 * 10 }px; display:none;"></div>
		</div>
	</c:forEach>
</div>