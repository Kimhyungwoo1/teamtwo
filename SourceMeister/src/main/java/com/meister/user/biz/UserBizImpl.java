package com.meister.user.biz;

import java.util.List;

import com.meister.user.dao.UserDao;
import com.meister.user.dao.UserDaoImpl;
import com.meister.user.vo.UserVO;

public class UserBizImpl implements UserBiz {
	
	private UserDao userDao;

	public UserBizImpl() {
		userDao = new UserDaoImpl();
	}

	@Override
	public boolean registNewUser(UserVO newUserVO) {
		return userDao.insertNewUser(newUserVO) > 0;
	}

	@Override
	public List<UserVO> getAllUser() {
		return userDao.selectAllUser();
	}

	@Override
	public UserVO getOneUser(String userId) {
		return userDao.selectOneUser(userId);
	}

	@Override
	public UserVO getOneUser(UserVO userVO) {
		return userDao.selectOneUser(userVO);
	}

	@Override
	public boolean updateUser(UserVO user) {
		return userDao.updateUserInfo(user) > 0;
	}

	@Override
	public boolean deleteOneUser(String userId) {
		return userDao.deleteOneUser(userId) > 0;
	}

	@Override
	public boolean chagerUser(String beforeAuthorization, String afterAuthorization) {
		return userDao.changeUser(beforeAuthorization, afterAuthorization) > 0;
	}
<<<<<<< HEAD
	
	@Override
	public boolean isDuplicatedUserId(String userId) {
		return userDao.selectCountByUserId(userId) > 0;
	}
=======
>>>>>>> 3a4247aa481e0491c076340a8833dd32814a9466

}
