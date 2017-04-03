package com.meister.reply.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meister.reply.service.ReplyService;
import com.meister.reply.service.ReplyServiceImpl;
import com.meister.reply.vo.ReplyVO;
import com.meister.user.vo.UserVO;

public class ViewReplyWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ReplyService replyService;
	
    public ViewReplyWriteServlet() {
    	replyService = new ReplyServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/reply/list.jsp");
		dispatcher.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO
/*		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");*/
		
		String openSourceId = request.getParameter("openSourceId");
		String comment = request.getParameter("comment");
		String parentReplyId = request.getParameter("parentReplyId");
		
		comment = comment.replaceAll("\n", "<br/>");
		comment = comment.replaceAll("\r", "");
        
		System.out.println("[openSourceId]" + openSourceId + 
							"[comment]" + comment  +
							"[parentReplyId]" + parentReplyId);

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO)session.getAttribute("_USER_");
		//TODO
		String writer = userVO.getUserId();
		//String writer = "TEST";

		ReplyVO replyVO = new ReplyVO();
		
		replyVO.setOpenSourceId(openSourceId);
		replyVO.setComment(comment);
		replyVO.setParentReplyId(parentReplyId);
		replyVO.setUserId(writer);
		
		if ( replyService.insertReply(replyVO) ) {
			response.sendRedirect("/SourceMeister/reply/list");
		}
		else {
			response.sendRedirect("/SourceMeister/reply/write");
		}
		
	}

}
