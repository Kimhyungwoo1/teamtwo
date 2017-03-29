package com.meister.user.biz;

import java.util.List;

import com.meister.user.vo.UserVO;

public interface UserBiz {
	
	public boolean registNewUser(UserVO newUserVO);

	public List<UserVO> getAllUser();

	public UserVO getOneUser(String userId);

	public UserVO getOneUser(UserVO userVO);

	public boolean updateUser(UserVO user);

	public boolean deleteOneUser(String userId);

	public boolean chagerUser(String beforeAuthorization, String afterAuthorization);
<<<<<<< HEAD
	
	public boolean isDuplicatedUserId(String userId);
=======
>>>>>>> 3a4247aa481e0491c076340a8833dd32814a9466

}
