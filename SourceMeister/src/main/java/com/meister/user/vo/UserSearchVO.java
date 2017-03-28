package com.meister.user.vo;

import com.meister.common.web.pager.Pager;
import com.meister.common.web.pager.PagerFactory;

public class UserSearchVO {
	private Pager pager;
	private String userId;
	private String authorizationId;

	public Pager getPager() {
		if (pager == null) {
			pager = PagerFactory.getPager(Pager.ORACLE, 50, 10);
		}
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAuthorizationId() {
		return authorizationId;
	}

	public void setAuthorizationId(String authorizationId) {
		this.authorizationId = authorizationId;
	}

}
