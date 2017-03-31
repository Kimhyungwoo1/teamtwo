package com.meister.user.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meister.user.service.UserService;
import com.meister.user.service.UserServiceImpl;

public class DoChangeAuthorizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;
	
	public DoChangeAuthorizationServlet() {
		userService = new UserServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String authBefore = request.getParameter("authBefore");
		String authAfter = request.getParameter("authAfter");
		String[] userId = request.getParameterValues("authCheck");
		
		boolean change = false;
		boolean changeCheck = false;
		
		if (userId == null){
			change = userService.changeUser(authBefore, authAfter);
		} else {
			changeCheck = userService.changeCheckUser(userId, authBefore, authAfter);
		}

		if( change || changeCheck ) {
			response.sendRedirect("/SourceMeister/admin");
		}
	}

}
