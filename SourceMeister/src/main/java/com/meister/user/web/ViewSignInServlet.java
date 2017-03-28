package com.meister.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meister.user.vo.UserVO;

public class ViewSignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ViewSignInServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/view/user/signIn.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		
		UserVO user = new UserVO();
		
		user.setUserId(userId);
		user.setPassword(userPassword);
		
		//user = userService.loginUser(user);
		
		if(user == null){
			response.sendRedirect("/SourceMeister/user/signIn");
		} 
		else {
			HttpSession session = request.getSession();
			
			session.setAttribute("_USER_", user);
			
			response.sendRedirect("/SourceMeister/opensource");
			
		}
	}

	}


