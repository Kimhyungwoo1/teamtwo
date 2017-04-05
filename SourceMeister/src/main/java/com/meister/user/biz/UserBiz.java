package com.meister.user.biz;

import java.util.List;

import com.meister.user.vo.UserVO;

public interface UserBiz {
	public boolean registNewUser(UserVO newUserVO);

	public List<UserVO> getAllUser();

	public UserVO getOneUser(String userId);

	public UserVO getOneUser(UserVO userVO);

	public boolean updateUser(UserVO user);
	
	public boolean updateUsers(UserVO user);

	public boolean deleteOneUser(String userId);

	public boolean chagerUser(String beforeAuthorization, String afterAuthorization);

	public boolean isDuplicatedUserId(String userId );
	public UserVO loginUser(UserVO user);
}
