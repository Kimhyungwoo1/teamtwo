package com.meister.authorization.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meister.authorization.service.AuthorizationService;
import com.meister.authorization.service.AuthorizationServiceImpl;
import com.meister.authorization.vo.AuthorizationVO;
import com.meister.commom.constants.AuthConst;
import com.meister.user.service.UserService;
import com.meister.user.service.UserServiceImpl;
import com.meister.user.vo.UserVO;

public class ViewMainAuthorizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AuthorizationService authorizationService;
	private UserService userService;
	
	public ViewMainAuthorizationServlet() {
		authorizationService = new AuthorizationServiceImpl();
		userService = new UserServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("_USER_");
		
		List<AuthorizationVO> authList = authorizationService.allAuthList();
		List<UserVO> userList = userService.getAllUsers();
		
		request.setAttribute("userList", userList);
		request.setAttribute("authList", authList);
		
		//if ( userVO.getAuthorizationId().equals(AuthConst.ADMIN_USER)){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/main.jsp");
			dispatcher.forward(request, response);
		//} else if ( userVO.getAuthorizationId().equals(AuthConst.NOMAL_USER)){
		//	response.sendError(404);
		//}
		
	}

}
