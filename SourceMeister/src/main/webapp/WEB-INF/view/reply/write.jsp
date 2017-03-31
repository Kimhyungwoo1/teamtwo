<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/SourceMeister/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" >

	$().ready(function(){
		
	      $(".hide").hide();
	        
	      /*   $("#increaseRecommends").click( function(){
	            
	            var form = $("#increaseRecommendsForm");
	            
	            form.attr("method", "POST");
	            form.attr("action", "<c:url value="/increaseRecommends" />");
	 
	            form.submit(); 
	        });
	        
	         */
	        $("#writeReplyBtn").click( function(){
	            var form = $("#writeReplyForm");
	            
	            form.attr("method", "POST");
	            form.attr("action", "<c:url value="/doWriteReply" />");
	 
	            form.submit(); 
	            
	        });
	        
	        /*새로 생긴 엘리먼트에 접근하는 방법*/
	        $("body").on("click", "#writeReplyBtn", function(){
	            var form = $("#writeReplyForm");
	            
	            form.attr("method", "POST");
	            form.attr("action", "<c:url value="/doWriteReply" />");
	 
	            form.submit(); 
	 
	        });
	        
	        $(".writeReReply").click(function() {
	            // 현재 클릭한 것(this)의 부모, 부모, 부모 하면은 table이 나온다.
	            var table = $(this).parent().parent().parent();
	            
	            var groupId= table.children(":eq(1)").children(":eq(0)").html();
	            var parentReplyId = table.children(":eq(1)").children(":eq(1)").html();
	            var depth = table.children(":eq(2)").children(":eq(0)").html();
	            var orderNo = table.children(":eq(2)").children(":eq(1)").html();
	            var replyId = table.children(":eq(3)").children(":eq(0)").html();
	            
	            $("#depth").val(parseInt(depth) + 1);
	            $("#parentReplyId").val(replyId);
	            $("#groupId").val(groupId);
	            $("#orderNo").val(orderNo);
	            $("#replyId").val(replyId);
	            
	            var form = $("#formWrapper").html();
	            $("#formWrapper").detach();
	            
	            /*댓글달기 눌렀다가 다른 댓글달기 눌렀을 때 활성화*/
	            if( form == undefined ) {
	                $(".formAppender").each(function(index, data) {
	                    if( data.innerHTML != "" ) {
	                        form = data.innerHTML;
	                    }
	                });
	                $(".formAppender").html("");
	            }
	            
	            var formAppender = table.parent().parent().children(":eq(1)");
	            formAppender.html(form);
	            formAppender.show();
	        });
	        
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