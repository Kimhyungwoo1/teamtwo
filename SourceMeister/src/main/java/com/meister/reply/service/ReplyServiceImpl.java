package com.meister.reply.service;

import java.util.List;

import com.meister.reply.biz.ReplyBiz;
import com.meister.reply.biz.ReplyBizImpl;
import com.meister.reply.vo.ReplySearchVO;
import com.meister.reply.vo.ReplyVO;

public class ReplyServiceImpl implements ReplyService {

	private ReplyBiz replyBiz;
	
	public ReplyServiceImpl() {
		replyBiz = new ReplyBizImpl();
	}

	@Override
	public int selectAllRepliesCount(ReplySearchVO replySearchVO) {
		return replyBiz.selectAllRepliesCount(replySearchVO);
	}

	@Override
	public List<ReplyVO> selectAllReplies(ReplySearchVO replySearchVO) {
		return replyBiz.selectAllReplies(replySearchVO);
	}

	@Override
	public ReplyVO selectOneReply(String replyId) {
		return replyBiz.selectOneReply(replyId);
	}

	@Override
	public boolean insertReply(ReplyVO replyVO) {
		return replyBiz.insertReply(replyVO);
	}

	@Override
	public boolean deleteReply(String replyId, int childCnt) {
		return replyBiz.deleteReply(replyId,childCnt);
	}

	@Override
	public boolean updateReply(ReplyVO replyVO) {
		return replyBiz.updateReply(replyVO);
	}

}
