package com.meister.user.service;

import java.util.HashMap;
import java.util.Map;

import com.meister.authorization.biz.AuthorizationBiz;
import com.meister.authorization.biz.AuthorizationBizImpl;
import com.meister.user.biz.UserBiz;
import com.meister.user.biz.UserBizImpl;
import com.meister.user.vo.UserVO;

public class UserServiceImpl implements UserService {

	private UserBiz userBiz;
	private AuthorizationBiz authorizationBiz;

	
	  public UserServiceImpl(){ 
		  
		  userBiz = new UserBizImpl();
		  authorizationBiz = new AuthorizationBizImpl(); 
		  
	 }
	 

	@Override
	public boolean registNewUser(UserVO newUserVO) {

		return userBiz.registNewUser(newUserVO);
	}

	/*
	 * @Override public List<UserVO> getAllUsers(UserSearchVO userSearchVO) {
	 * 
	 * return userBiz.getAllUser(userSearchVO); }
	 */

	@Override
	public UserVO getOneUser(String userId) {

		return userBiz.getOneUser(userId);
	}

	@Override
	public UserVO getOneUser(UserVO user) {

		return userBiz.getOneUser(user);
	}

	@Override
	public boolean updateUser(UserVO user) {

		UserVO tempUserVo = getOneUser(user.getUserId());
		if (user.getAuthorizationId() != null && user.getAuthorizationId().length() > 0) {
			tempUserVo.setAuthorizationId(user.getAuthorizationId());
		}
		if (user.getPassword() != null && user.getPassword().length() > 0) {
			tempUserVo.setPassword(user.getPassword());
		}
		return userBiz.updateUser(tempUserVo);
	}

	@Override
	public boolean deleteOneUser(String userId) {

		return userBiz.deleteOneUser(userId);
	}

	@Override
	public boolean changeUser(String beforeAuthorization, String afterAuthorization) {

		return userBiz.chagerUser(beforeAuthorization, afterAuthorization);
	}

	@Override
	public Map<String, Object> getOneUserWithAuthorizations(String userId) {
		// AuthorizationSearchVO authorizationSearchVO = new
		// AuthorizationSearchVO();
		// authorizationSearchVO.getPager().setPageNumber(0);

		Map<String, Object> user = new HashMap<String, Object>();
		user.put("user", userBiz.getOneUser(userId));
		/// user.put("authorizations", authorizationBiz.)

		return null;
	}

	@Override
	public boolean isDuplicatedUserId(String userId) {
		return userBiz.isDuplicatedUserId(userId);
	}

	public UserVO loginUser(UserVO user) {
		return userBiz.loginUser(user);
	}

}
