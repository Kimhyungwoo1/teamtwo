package com.meister.reply.service;

import java.util.List;

import com.meister.reply.vo.ReplySearchVO;
import com.meister.reply.vo.ReplyVO;

public interface ReplyService {
	public int selectAllRepliesCount(ReplySearchVO replySearchVO);

	public List<ReplyVO> selectAllReplies(ReplySearchVO replySearchVO);

	public ReplyVO selectOneReply(String replyId);

	public boolean insertReply(ReplyVO replyVO);

	public boolean deleteReply(String replyId);

	public boolean updateReply(ReplyVO replyVO);
}
