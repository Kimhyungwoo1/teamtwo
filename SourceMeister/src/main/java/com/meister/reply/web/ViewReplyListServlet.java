package com.meister.reply.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meister.common.web.pager.ClassicPageExplorer;
import com.meister.common.web.pager.PageExplorer;
import com.meister.reply.service.ReplyService;
import com.meister.reply.service.ReplyServiceImpl;
import com.meister.reply.vo.ReplySearchVO;
import com.meister.reply.vo.ReplyVO;

public class ViewReplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReplyService replyService;

	public ViewReplyListServlet() {
		replyService = new ReplyServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//TODO
/*		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");*/
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageNumber = request.getParameter("pageNumber");
		String opensourceId = request.getParameter("opensourceId");
		//TODO 
		System.out.println("[[openSourceId]]" + opensourceId);

		ReplySearchVO replySearchVO = new ReplySearchVO();

		replySearchVO.getPager().setPageNumber(pageNumber);
		replySearchVO.setOpenSourceId(opensourceId);
		
		List<ReplyVO> replyList = replyService.selectAllReplies(replySearchVO);
		int totalcnt = replyService.selectAllRepliesCount(replySearchVO);
		request.setAttribute("totalcnt", totalcnt);
	
		PageExplorer pageExplorer = new ClassicPageExplorer(replySearchVO.getPager());
		String pages = pageExplorer.getPagingList("pageNo", "[@]", "PREV", "NEXT", "searchForm");
	
		request.setAttribute("pager", pages);
		request.setAttribute("replyList", replyList);


		if (totalcnt > 0) {
			PrintWriter out = response.getWriter();
			out.write("OK");
			out.flush();
			out.close();
		}
		else {
			PrintWriter out = response.getWriter();
			out.write("FAIL");
			out.flush();
			out.close();
		}
		

	/*	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/reply/list.jsp");
		dispatcher.forward(request, response);*/

	}

}
