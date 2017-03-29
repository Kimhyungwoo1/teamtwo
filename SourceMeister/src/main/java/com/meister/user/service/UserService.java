package com.meister.user.service;

import java.util.List;
import java.util.Map;

import com.meister.user.vo.UserSearchVO;
import com.meister.user.vo.UserVO;

public interface UserService {

	public boolean registNewUser(UserVO newUserVO);

	// public List<UserVO> getAllUsers(UserSearchVO userSearchVO);
	//public UserVO loginUser(UserVO user);

	public UserVO getOneUser(String userId);

	public UserVO getOneUser(UserVO userVO);

	public boolean updateUser(UserVO user);

	public boolean deleteOneUser(String userId);

	public boolean changeUser(String beforeAuthorization, String afterAuthorization);

	public Map<String, Object> getOneUserWithAuthorizations(String userId);

	public boolean isDuplicatedUserId(String userId);

}
