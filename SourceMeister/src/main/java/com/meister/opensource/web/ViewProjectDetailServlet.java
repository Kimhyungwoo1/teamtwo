package com.meister.opensource.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.meister.opensource.vo.OpensourceVO;

public class ViewProjectDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ViewProjectDetailServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opensourceId = request.getParameter("opensourceId");
		/*
		 * https://searchcode.com/file/101589460/ 오픈 소스 id로 검색되게 만들기
		 */
		
		StringBuilder urlBuilder = new StringBuilder("https://searchcode.com/");
		urlBuilder.append(URLEncoder.encode("file", "UTF-8") + "/101589460");

		System.out.println(urlBuilder.toString());

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");

		System.out.println("Response code: " + conn.getResponseCode());

		BufferedReader rd;

		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
		}

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}

		System.out.println(sb.toString());
		rd.close();
		conn.disconnect();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/opensource/detail.jsp");
		dispatcher.forward(request, response);
	}

}
