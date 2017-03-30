package com.meister.opensource.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meister.opensource.service.OpensourceService;
import com.meister.opensource.service.OpensourceServiceImpl;

public class DoLikeCountActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OpensourceService opensourceService;
       
    public DoLikeCountActionServlet() {
    	opensourceService = new OpensourceServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opensourceId = request.getParameter("opensourceId");
		String likeCountStr = request.getParameter("likeCount");
		
		int likeCount = 0;
		try {
			likeCount = Integer.parseInt(likeCountStr);
		}
		catch (NumberFormatException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		boolean isSuccess = opensourceService.updateLikeCount(opensourceId);
		
		StringBuffer json = new StringBuffer(); // json format String (not json, yet)
		json.append(" { ");
		json.append(" \"status\" : \"success\", "); // \" = ""�ȿ��� " ���� ǥ���
		json.append(" \"success\" : " + isSuccess + ", ");
		if (isSuccess) {
			json.append(" \"likeCount\" : \"" + (likeCount+1) + "\"");
		}
		else {
			json.append(" \"likeCount\" : \"" + (likeCount) + "\"");
		}
		json.append(" } ");
		
		PrintWriter writer = response.getWriter();
		writer.write(json.toString());
		writer.flush();
		writer.close();
	}

}
