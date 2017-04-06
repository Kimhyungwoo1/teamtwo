package com.meister.user.service;

import java.util.List;
import java.util.Map;

import com.meister.user.vo.UserVO;

public interface UserService {

	public boolean registNewUser(UserVO newUserVO);

	public List<UserVO> getAllUsers();

	public UserVO loginUser(UserVO user);

	public UserVO getOneUser(String userId);

	public UserVO getOneUser(UserVO user);

	public boolean updateUser(UserVO user);

	public boolean deleteOneUser(String userId);
	
	public boolean deleteCheckUser(String[] userIdCheck);
	
	public boolean changeUser(String beforeAuthorization, String afterAuthorization);

	public boolean changeCheckUser(String [] userId, String beforeAuthorization, String afterAuthorization);

	public Map<String, Object> getOneUserWithAuthorizations(String userId);

	public boolean isDuplicatedUserId(String userId);


}
