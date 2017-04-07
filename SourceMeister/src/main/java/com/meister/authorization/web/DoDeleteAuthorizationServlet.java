package com.meister.authorization.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meister.authorization.service.AuthorizationService;
import com.meister.authorization.service.AuthorizationServiceImpl;

public class DoDeleteAuthorizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AuthorizationService authorizationService;
	
	public DoDeleteAuthorizationServlet() {
		authorizationService = new AuthorizationServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String authId = request.getParameter("authorizationId");
		
		System.out.println(authId);
		
		authorizationService.removeAuthorization(authId);
		
	}
	

}
