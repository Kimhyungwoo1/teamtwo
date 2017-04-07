package com.meister.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meister.common.constants.AuthConst;
import com.meister.user.service.UserService;
import com.meister.user.service.UserServiceImpl;
import com.meister.user.vo.UserVO;

public class ViewSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService;

	public ViewSignUpServlet() {
		userService = new UserServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/user/signUp.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		String userName = request.getParameter("userName");
		String userNickName = request.getParameter("NickName");
		String userGender = request.getParameter("gender");
		String userEmail = request.getParameter("email");
		
		
		System.out.println(userId);
		System.out.println(userPassword);
		System.out.println(userName);
		System.out.println(userNickName);
		System.out.println(userGender);
		System.out.println(userEmail);
		
		
		if (userId == null || userId.length() == 0) {
			response.sendRedirect("/SourceMeister/user/signUp?errorCode=0");
			return;
		}
		if (userPassword == null || userPassword.length() == 0) {
			response.sendRedirect("/SourceMeister/user/signUp?errorCode=1");
			return;
		}
		if (userName == null || userName.length() == 0) {
			response.sendRedirect("/SourceMeister/user/signUp?errorCode=2");
			return;
		}
		if(userService.isDuplicatedUserId(userId)){
			response.sendRedirect("/SourceMeister/user/signUp?errorCode=3");
			return;
		}

		UserVO user = new UserVO();

		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassword(userPassword);
		user.setNickName(userNickName);
		user.setGender(userGender);
		user.setEmail(userEmail);
		user.setAuthorizationId(AuthConst.NOMAL_USER);

		if (userService.registNewUser(user)) { 
			HttpSession session = request.getSession();
			session.setAttribute("_USER_", user);
			System.out.println("�쉶�썝媛��엯 �꽦怨�");
			response.sendRedirect("/SourceMeister/opensource");
		} else { 
			System.out.println("�쉶�썝媛��엯 �떎�뙣");
			response.sendRedirect("/SourceMeister/user/signUp");
		}
	}

	}

