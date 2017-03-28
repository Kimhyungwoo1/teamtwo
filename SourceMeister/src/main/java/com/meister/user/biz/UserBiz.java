package com.meister.user.biz;

<<<<<<< HEAD
public interface UserBiz {

=======
import java.util.List;

import com.meister.user.vo.UserSearchVO;
import com.meister.user.vo.UserVO;

public interface UserBiz {
	public boolean registNewUser(UserVO newUserVO);

	public List<UserVO> getAllUser(UserSearchVO userSearchVO);

	public UserVO getOneUser(String userId);

	public UserVO getOneUser(UserVO userVO);

	public boolean updateUser(UserVO user);

	public boolean deleteOneUser(String userId);

	public boolean chagerUser(String beforeAuthorization, String afterAuthorization);

	public boolean isDuplicatedUserId(String userId);
>>>>>>> jun
}
