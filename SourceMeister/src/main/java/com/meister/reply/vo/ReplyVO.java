package com.meister.reply.vo;

import com.meister.user.vo.UserVO;

public class ReplyVO {
	public String replyId;
	public String openSourceId;
	public String comment;
	public String writeDate;
	public String userId;
	public String parentReplyId;
	public int level;
	
	public UserVO user;

	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public UserVO getUser() {
		if(user == null) {
			user = new UserVO();
		}
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
	}
	public String getReplyId() {
		return replyId;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}
	public String getOpenSourceId() {
		return openSourceId;
	}
	public void setOpenSourceId(String openSourceId) {
		this.openSourceId = openSourceId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getParentReplyId() {
		return parentReplyId;
	}
	public void setParentReplyId(String parentReplyId) {
		this.parentReplyId = parentReplyId;
	}
	
}
