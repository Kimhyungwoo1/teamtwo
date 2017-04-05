package com.meister.user.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meister.user.service.UserService;
import com.meister.user.service.UserServiceImpl;
import com.meister.user.vo.UserVO;


public class DoDeleteUserActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
   
	public DoDeleteUserActionServlet() {
    	
    	userService = new UserServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		request.getSession().invalidate();
		
		if(	userService.deleteOneUser(userId)){
			System.out.println("회원탈퇴 성공!");
			response.sendRedirect("/SourceMeister/opensource");
		}else{
				System.out.println("회원탈퇴 실패!");
				response.sendRedirect("/SourceMeister/user/signUpModify?userId=${param.userId}");
		}
		
	
	}

}
