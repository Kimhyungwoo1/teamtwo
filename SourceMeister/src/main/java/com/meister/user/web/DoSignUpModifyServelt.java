package com.meister.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meister.user.service.UserService;
import com.meister.user.service.UserServiceImpl;
import com.meister.user.vo.UserVO;


public class DoSignUpModifyServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService; 
       
   
    public DoSignUpModifyServelt() {
    		userService = new UserServiceImpl();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String userNickName = request.getParameter("userNickName");
		String userEmail =  request.getParameter("email");
		
		System.out.println(userPassword);
		System.out.println(userNickName);
		System.out.println(userName);
		System.out.println(userEmail);
		UserVO user = new UserVO();
		 
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassword(userPassword);
		user.setNickName(userNickName);
		user.setEmail(userEmail);
		
		System.out.println("test:" + user.getEmail());
		
		
		if(userService.updateUser(user)){
			System.out.println("회원정보수정 완료");
			HttpSession session = request.getSession();
			session.setAttribute("_USER_", user);
		    response.sendRedirect("/SourceMeister/opensource");		
			
		 }else{
		 System.out.println("회원정보수정 실패");
		 response.sendRedirect("/SourceMeister/user/signUpModify?="+userId);
		
		}
	}
}

