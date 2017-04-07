package com.meister.authorization.service;

import java.util.List;

import com.meister.authorization.vo.AuthorizationVO;

public interface AuthorizationService {

	public boolean addAuthrization(AuthorizationVO authorizationVO);

	public List<AuthorizationVO> allAuthList();

	public boolean removeAuthorization(String authorizationId);

}
