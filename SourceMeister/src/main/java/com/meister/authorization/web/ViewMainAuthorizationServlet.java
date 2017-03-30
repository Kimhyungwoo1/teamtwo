package com.meister.authorization.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meister.authorization.service.AuthorizationService;
import com.meister.authorization.service.AuthorizationServiceImpl;
import com.meister.authorization.vo.AuthorizationVO;
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
		
		List<AuthorizationVO> authList = authorizationService.allAuthList();
		//List<UserVO> userList = userService.getAllUsers();
		
		//request.setAttribute("userList", userList);
		request.setAttribute("authList", authList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/main.jsp");
		dispatcher.forward(request, response);
		
	}

}
