<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>댓글</title>
<script type="text/javascript" src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" >

	$().ready(function(){
	
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
		/* 	$(".writeReplyForm").attr({
				"method" : "post",
				"action" : "/SourceMeister/reply/write"
			});
			$(".writeReplyForm").submit(); */
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
			
			/* 
			thisForm.find("form").attr({
				"method" : "post",
				"action" : "/SourceMeister/reply/write"
			});
			thisForm.find("form").submit(); */
			
			
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
    });
</script>
</head>
<body>

<!-- align="center" -->
 <div id="replyDiv" style="width:220px;"  >
       
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