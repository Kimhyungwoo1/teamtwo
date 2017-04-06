package com.meister.reply.biz;

import java.util.List;

import com.meister.reply.vo.ReplySearchVO;
import com.meister.reply.vo.ReplyVO;

public interface ReplyBiz {
	public int selectAllRepliesCount(ReplySearchVO replySearchVO);

	public List<ReplyVO> selectAllReplies(ReplySearchVO replySearchVO);

	public ReplyVO selectOneReply(String replyId);

	public boolean insertReply(ReplyVO replyVO);

	public boolean deleteReply(String replyId,int childCnt);

	public boolean updateReply(ReplyVO replyVO);
}
