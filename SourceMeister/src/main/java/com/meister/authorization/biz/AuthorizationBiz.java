package com.meister.authorization.biz;

import java.util.List;

import com.meister.authorization.vo.AuthorizationVO;

public interface AuthorizationBiz {
	
	public boolean addAuthrization(AuthorizationVO authorizationVO);
	
	public List<AuthorizationVO> allAuthList();
	
	public boolean removeAuthorization(String authorizationId);

}
