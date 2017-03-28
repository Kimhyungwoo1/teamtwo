package com.meister.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		if (userService.registNewUser(user)) { 
			System.out.println("유저등록 성공");
			response.sendRedirect("/SourceMeister/user/signIn");
		} else { 
			System.out.println("유저등록 실패");
			response.sendRedirect("/SourceMeister/user/signUp");
		}
	}

	}

