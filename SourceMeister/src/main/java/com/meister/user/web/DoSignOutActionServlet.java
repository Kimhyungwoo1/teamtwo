package com.meister.user.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoSignOutActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String urlBeforeDetail = request.getParameter("urlBefore");
		String opensourceId = request.getParameter("opensourceId");
		String url = request.getHeader("referer");
		
		System.out.println("before url to logout" + urlBeforeDetail);
		System.out.println("before url to logout" + opensourceId);
		
		
		request.getSession().invalidate();

		if (!urlBeforeDetail.equals("undefined")) {

			
			response.sendRedirect(urlBeforeDetail+"&isDetail=ok"+ "&opensourceId="+opensourceId);

		} else {

			response.sendRedirect(url);
		}
	}

}
