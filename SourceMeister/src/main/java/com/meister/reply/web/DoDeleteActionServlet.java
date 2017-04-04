package com.meister.reply.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meister.reply.service.ReplyService;
import com.meister.reply.service.ReplyServiceImpl;
import com.meister.reply.vo.ReplyVO;
import com.meister.user.vo.UserVO;

public class DoDeleteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ReplyService replyService;
	
    public DoDeleteActionServlet() {
    	replyService = new ReplyServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        UserVO userVO = (UserVO)session.getAttribute("_USER_");
        
    	String replyId = request.getParameter("replyId");
		
    	ReplyVO replyVO = replyService.selectOneReply(replyId);

		if ( userVO.getUserId().equals(replyVO.getUserId()) ) {
			doPost(request, response);
		}
		else {
			response.sendError(404);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String replyId = request.getParameter("replyId");
	System.out.println("deletePost");
		if( replyService.deleteReply(replyId) ) {
			PrintWriter out = response.getWriter();
			out.write("OK");
			out.flush();
			out.close();
		}else {
			PrintWriter out = response.getWriter();
			out.write("FAIL");
			out.flush();
			out.close();
		}

	}
}
