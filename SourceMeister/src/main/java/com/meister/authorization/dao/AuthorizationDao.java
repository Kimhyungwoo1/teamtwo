package com.meister.authorization.dao;

import java.util.List;

import com.meister.authorization.vo.AuthorizationVO;

public interface AuthorizationDao {

	public int insertAuthorization(AuthorizationVO authorizationVO);
	
	public List<AuthorizationVO> allAuthorization();
	
	public int deleteAuthorization(String authorizationId);
	
	
}
