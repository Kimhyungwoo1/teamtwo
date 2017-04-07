package com.meister.user.vo;

import com.meister.authorization.vo.AuthorizationVO;

public class UserVO {
	private int index;
	private String userId;
	private String userName;
	private String password;
	private String gender;
	private String email;
	private String nickName;
	private String authorizationId;
	
	private AuthorizationVO authorizationVO;

	public AuthorizationVO getAuthorizationVO() {
		if (authorizationVO == null) {
			authorizationVO = new AuthorizationVO();
		}
		return authorizationVO;
	}

	public void setAuthorizationVO(AuthorizationVO authorizationVO) {
		this.authorizationVO = authorizationVO;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAuthorizationId() {

		return authorizationId;
	}

	public void setAuthorizationId(String authorizationId) {
		this.authorizationId = authorizationId;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
