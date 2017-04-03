package com.meister.user.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meister.user.service.UserService;
import com.meister.user.service.UserServiceImpl;

public class DoUserRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;
	
	public DoUserRemoveServlet() {
		userService = new UserServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		
		userService.deleteOneUser(userId);
		
	}

}
