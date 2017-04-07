package com.meister.authorization.biz;

import java.util.List;

import com.meister.authorization.dao.AuthorizationDao;
import com.meister.authorization.dao.AuthorizationDaoImpl;
import com.meister.authorization.vo.AuthorizationVO;

public class AuthorizationBizImpl implements AuthorizationBiz{
	
	private AuthorizationDao authorizationDao;
	
	public AuthorizationBizImpl() {
		authorizationDao = new AuthorizationDaoImpl();
	}

	@Override
	public boolean addAuthrization(AuthorizationVO authorizationVO) {
		return authorizationDao.insertAuthorization(authorizationVO) > 0;
	}

	@Override
	public List<AuthorizationVO> allAuthList() {
		return authorizationDao.allAuthorization();
	}

	@Override
	public boolean removeAuthorization(String authorizationId) {
		return authorizationDao.deleteAuthorization(authorizationId) > 0;
	}

}
