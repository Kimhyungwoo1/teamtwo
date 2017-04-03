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
import javax.swing.event.ListSelectionEvent;

import org.apache.catalina.tribes.util.Arrays;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meister.opensource.vo.LanguageVO;
import com.meister.opensource.vo.SearchResultVO;


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

		//System.out.println("first parsing = " + sb.toString());
		
		JSONObject object = new JSONObject(sb.toString());
		JSONArray arr = object.getJSONArray("results"); 
		String total = object.get("total").toString(); 

		
		Gson gson = new Gson();

		TypeToken<List<SearchResultVO>> token = new TypeToken<List<SearchResultVO>>() {};
		List<SearchResultVO> resultList = gson.fromJson(arr.toString(), token.getType());
		
		//List<SearchResultVO> languageList = new ArrayList<SearchResultVO>();
		HashMap<String, Integer> languageFilters = new HashMap();
		
		for (SearchResultVO result : resultList) {

			String tempUrl = result.getRepo().replaceAll("[.]git", "");
			String[] parameters = tempUrl.split("/");


			urlBuilder = new StringBuilder("https://searchcode.com/api/codesearch_I/");
			urlBuilder.append(
					"?" + URLEncoder.encode("q", "UTF-8") + "=<" + "+" + "repo:" + parameters[3] + "/" + parameters[4]);

			url = new URL(urlBuilder.toString());
			
			System.out.println("second address = " + urlBuilder.toString());
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");


			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
			}

			sb = new StringBuilder();
			
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}

			System.out.println("second parsing = " + sb.toString());
			
			
			rd.close();
			conn.disconnect();

			object = new JSONObject(sb.toString());
			
			
			JSONArray langArr = object.getJSONArray("language_filters");
		/*
			Map<String, String> tmp = new HashMap<String, String>();
			
			for(int i = 0; i < langArr.length(); i++) {
				String language = langArr.getString(2);
				
				if(!isLanguageExist(language)) {
					
					String count = langArr.getString(0); 
					String id = langArr.getString(1);
					tmp.put(count, id);
					languageFilters.put(language, tmp);
					
				}
			}
			*/
			

		}

		
		
		request.setAttribute("results", resultList);
		request.setAttribute("count", total);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/opensource/search.jsp");
		dispatcher.forward(request, response);

	}
/*
	private boolean isLanguageExist(String language) {
		
		for(int i = 0; i < )
		
		return false;
	}
*/
}
