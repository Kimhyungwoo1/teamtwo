package com.meister.reply.dao;

import java.util.List;

import com.meister.reply.vo.ReplySearchVO;
import com.meister.reply.vo.ReplyVO;

public interface ReplyDao {

	public int selectAllRepliesCount(ReplySearchVO replySearchVO);

	public List<ReplyVO> selectAllReplies(ReplySearchVO replySearchVO);

	public ReplyVO selectOneReply(String replyId);

	public int insertReply(ReplyVO replyVO);

	public int deleteReply(String replyId);
	
	public int deleteReplyByParentId(String parentId);

	public int updateReply(ReplyVO replyVO);

}
