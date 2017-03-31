package com.meister.reply.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meister.common.web.pager.ClassicPageExplorer;
import com.meister.common.web.pager.PageExplorer;
import com.meister.reply.service.ReplyService;
import com.meister.reply.service.ReplyServiceImpl;
import com.meister.reply.vo.ReplySearchVO;
import com.meister.reply.vo.ReplyVO;
import com.meister.user.vo.UserVO;

public class ViewReplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReplyService replyService;

	public ViewReplyListServlet() {
		replyService = new ReplyServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//TODO
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageNumber = request.getParameter("pageNumber");
		String openSourceId = request.getParameter("openSourceId");
		//TODO 
		openSourceId =  "XX01";
		
		ReplySearchVO replySearchVO = new ReplySearchVO();

		replySearchVO.getPager().setPageNumber(pageNumber);
		replySearchVO.setOpenSourceId(openSourceId);
		
		List<ReplyVO> replyList = replyService.selectAllReplies(replySearchVO);
		int totalcnt = replyService.selectAllRepliesCount(replySearchVO);
		request.setAttribute("totalcnt", totalcnt);
	
		PageExplorer pageExplorer = new ClassicPageExplorer(replySearchVO.getPager());
		String pages = pageExplorer.getPagingList("pageNo", "[@]", "PREV", "NEXT", "searchForm");
	
		request.setAttribute("pager", pages);
		request.setAttribute("replyList", replyList);

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/reply/list.jsp");
		dispatcher.forward(request, response);

	}

}
