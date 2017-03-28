package com.meister.user.biz;

import java.util.ArrayList;
import java.util.List;

import com.meister.user.dao.UserDao;
import com.meister.user.dao.UserDaoImpl;
import com.meister.user.vo.UserSearchVO;
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
	public List<UserVO> getAllUser(UserSearchVO userSearchVO) {
		int totalCount = userDao.selectAllUserCount(userSearchVO);
		userSearchVO.getPager().setTotalArticleCount(totalCount);

		System.out.println("yes=" + userSearchVO.getPager().getEndArticleNumber());

		if (totalCount == 0) {
			return new ArrayList<UserVO>();
		}
		return userDao.selectAllUser(userSearchVO);
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

	@Override
	public boolean isDuplicatedUserId(String userId) {

		return userDao.selectCountByUserId(userId) > 0;
	}

}
