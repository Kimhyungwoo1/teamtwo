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


public class ViewSignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	public ViewSignInServlet() {
		userService = new UserServiceImpl();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserVO users = (UserVO) session.getAttribute("_USER_");
		if(users != null){
			request.setAttribute("isAdminUser", users.getAuthorizationId().equals(AuthConst.ADMIN_USER));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/user/signIn.jsp");
			dispatcher.forward(request, response);
		} 

		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/user/signIn.jsp");
			dispatcher.forward(request, response);
		}

	}
}

