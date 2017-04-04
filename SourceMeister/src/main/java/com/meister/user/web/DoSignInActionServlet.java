package com.meister.user.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meister.user.service.UserService;
import com.meister.user.service.UserServiceImpl;
import com.meister.user.vo.UserVO;

public class DoSignInActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	public DoSignInActionServlet() {
		userService = new UserServiceImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		
		
		UserVO user = new UserVO();
		user.setUserId(userId);
		user.setPassword(userPassword);

		UserVO userVO = null;

		userVO = userService.getOneUser(user);

		if (userVO != null) {

			HttpSession session = request.getSession();
			session.setAttribute("_USER_", userVO);
			System.out.println("User ID : " + userVO.getUserId());
			response.sendRedirect("/SourceMeister/opensource");

		} else {

			
			StringBuffer script = new StringBuffer();
			
			script.append(" <script type='text/javascript'>    ");
			script.append(" alert('아이디 및 비번이 올바르게 입력되지 않았거나 없는정보 입니다!');   ");
			script.append(" </script>    ");
			
			
			PrintWriter writer = response.getWriter();
			writer.write(script.toString());
			writer.flush();
			writer.close();
		}

	}

}
