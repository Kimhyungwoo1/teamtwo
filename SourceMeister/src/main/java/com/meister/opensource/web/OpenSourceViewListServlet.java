package com.meister.opensource.web;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.util.Arrays;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meister.commom.constants.AuthConst;
import com.meister.opensource.vo.LanguageVO;
import com.meister.opensource.vo.SearchResultVO;
import com.meister.user.vo.UserVO;


public class OpenSourceViewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OpenSourceViewListServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/opensource/search.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String search = request.getParameter("search");

		System.out.println(search);

		StringBuilder urlBuilder = new StringBuilder("https://searchcode.com/api/codesearch_I/");
		urlBuilder.append("?" + URLEncoder.encode("q", "UTF-8") + "=" + search + "+ext:md");

		URL url = new URL(urlBuilder.toString());
		//System.out.println("first address = " + urlBuilder.toString());
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");

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

		rd.close();
		conn.disconnect();
		
		//System.out.println("first parsing = " + sb.toString());
		
		JSONObject object = new JSONObject(sb.toString());
		JSONArray arr = object.getJSONArray("results"); 
		String total = object.get("total").toString(); 

		Gson gson = new Gson();

		TypeToken<List<SearchResultVO>> token = new TypeToken<List<SearchResultVO>>() {
		};
		List<SearchResultVO> resultList = gson.fromJson(arr.toString(), token.getType());
		
		
		request.setAttribute("results", resultList);
		request.setAttribute("count", total);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/opensource/search.jsp");
		dispatcher.forward(request, response);
		

		}

}	


