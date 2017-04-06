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


public class ViewSignUpModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
  
    public ViewSignUpModifyServlet() {
    	userService = new UserServiceImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String userId = request.getParameter("userId");
			System.out.println("ddd="+userId);
			
			//String userNickName = request.getParameter("userNickName");
			UserVO userVO = userService.getOneUser(userId);
			
			System.out.println(userVO.getEmail());
			
			request.setAttribute("userVO", userVO);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/user/modify.jsp");
			dispatcher.forward(request, response);
	
	}

}
