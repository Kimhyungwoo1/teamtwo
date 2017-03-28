<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>댓글</title>
<script type="text/javascript" src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" >

	$().ready(function(){
		
		
		
		
	});


</script>
</head>
<body>

 <div align="center" id="replyDiv">
        <%-- 
        <c:forEach items="${article.replyList}" var="reply">
        
            <div style="padding-left: ${reply.depth * 40 }px;">
                <table id="replyTable">
                
                    <tr>
                        <td style="width:50px;">${reply.nickName}</td>
                        <td>
                            ${reply.description}
                            <span class="writeReReply" style="cursor: pointer;">댓글 달기</span>
                        </td>
                    </tr>
                    <tr class="hide">
                        <td class="groupId">${reply.groupId}</td>
                        <td class="parentReplyId">${reply.parentReplyId}</td>
                    </tr>
                    <tr class="hide">
                        <td class="depth">${reply.depth}</td>
                        <td class="orderNo">${reply.orderNo}</td>
                    </tr>        
                    <tr class="hide">
                        <td colspan="2" class="replyId">${reply.replyId}</td>
                    </tr>                        
                </table>
                <div class="hide formAppender"></div>
            </div>
        </c:forEach>
         --%>
        
        <div id="formWrapper">            
            <form id="writeReplyForm">
           <!--  
            String openSourceId = request.getParameter("openSourceId");
		String comment = request.getParameter("comment");
		String writeDate = request.getParameter("writeDate");
		String userId = request.getParameter("UserId");
		String parentReplyId = request.getParameter("ParentReplyId");
            value="${opnsrc.openSourceId}"
             -->
            
            
                <input type="hidden" id="opnsrcId" name="opnsrcId" />
                <input type="hidden" id="depth" name="depth" value="0" />
                <input type="hidden" id="parentReplyId" name="parentReplyId" value="0" />
                <input type="hidden" id="groupId" name="groupId" value="0" />
                <input type="hidden" id="orderNo" name="orderNo" value="0" />
                <input type="hidden" id="replyId" name="replyId" value="0" />
                
                <textarea id="comment" name="comment" style="width:800px;"></textarea>
                <input type="button" id="writeReplyBtn" value="등록" />    
            </form>
        </div>
    </div>



</body>
</html>