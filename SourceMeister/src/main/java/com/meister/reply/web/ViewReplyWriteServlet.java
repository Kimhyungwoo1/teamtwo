package com.meister.reply.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meister.reply.service.ReplyService;
import com.meister.reply.vo.ReplyVO;

public class ViewReplyWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ReplyService replyService;
	
    public ViewReplyWriteServlet() {
    	System.out.println("init1");
    	//replyBiz = new ReplyBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/reply/list.jsp");
		dispatcher.forward(request, response);

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String openSourceId = request.getParameter("openSourceId");
		String comment = request.getParameter("comment");
		String writeDate = request.getParameter("writeDate");
		String userId = request.getParameter("UserId");
		String parentReplyId = request.getParameter("ParentReplyId");
		
		ReplyVO replyVO = new ReplyVO();
		
		replyVO.setOpenSourceId(openSourceId);
		replyVO.setComment(comment);
		replyVO.setWriteDate(writeDate);
		replyVO.setUserId(userId);
		replyVO.setParentReplyId(parentReplyId);
		
		replyService.insertReply(replyVO);
	}

}
