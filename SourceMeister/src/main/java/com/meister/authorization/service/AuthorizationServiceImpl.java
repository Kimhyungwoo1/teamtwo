package com.meister.authorization.service;

import java.util.List;

import com.meister.authorization.biz.AuthorizationBiz;
import com.meister.authorization.biz.AuthorizationBizImpl;
import com.meister.authorization.vo.AuthorizationVO;

public class AuthorizationServiceImpl implements AuthorizationService{
	
	private AuthorizationBiz authorizationBiz;
	
	public AuthorizationServiceImpl() {
		authorizationBiz = new AuthorizationBizImpl();
	}

	@Override
	public boolean addAuthrization(AuthorizationVO authorizationVO) {
		return authorizationBiz.addAuthrization(authorizationVO);
	}

	@Override
	public List<AuthorizationVO> allAuthList() {
		return authorizationBiz.allAuthList();
	}

	@Override
	public boolean removeAuthorization(String authorizationId) {
		return authorizationBiz.removeAuthorization(authorizationId);
	}

}
