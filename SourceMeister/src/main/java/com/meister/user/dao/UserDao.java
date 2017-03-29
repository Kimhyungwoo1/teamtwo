package com.meister.user.dao;

import java.util.List;

import com.meister.user.vo.UserVO;

public interface UserDao {

	public int insertNewUser(UserVO newUserVO);

	public List<UserVO> selectAllUser();

	public UserVO selectOneUser(String userId);

	public UserVO selectOneUser(UserVO userVO);

	public int updateUserInfo(UserVO userVO);

	public int deleteOneUser(String userId);

	public int changeUser(String beforeAuthriztion, String afterAuthriztion);
<<<<<<< HEAD
	
	public int selectCountByUserId(String userId);
=======
>>>>>>> 3a4247aa481e0491c076340a8833dd32814a9466

}
