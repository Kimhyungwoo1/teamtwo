package com.meister.reply.biz;

import java.util.List;

import com.meister.reply.dao.ReplyDao;
import com.meister.reply.dao.ReplyDaoImpl;
import com.meister.reply.vo.ReplySearchVO;
import com.meister.reply.vo.ReplyVO;

public class ReplyBizImpl implements ReplyBiz {
	private ReplyDao replyDao;
	
	public ReplyBizImpl() {
		replyDao = new ReplyDaoImpl();
	}

	@Override
	public int selectAllRepliesCount(ReplySearchVO replySearchVO) {
		return replyDao.selectAllRepliesCount(replySearchVO);
	}

	@Override
	public List<ReplyVO> selectAllReplies(ReplySearchVO replySearchVO) {
		return replyDao.selectAllReplies(replySearchVO);
	}

	@Override
	public ReplyVO selectOneReply(String replyId) {
		return replyDao.selectOneReply(replyId);
	}

	@Override
	public boolean insertReply(ReplyVO replyVO) {
		return replyDao.insertReply(replyVO) > 0;
	}

	@Override
	public boolean deleteReply(String replyId) {
		return replyDao.deleteReply(replyId) > 0;
	}

	@Override
	public boolean updateReply(ReplyVO replyVO) {
		return replyDao.updateReply(replyVO) > 0;
	}

}
