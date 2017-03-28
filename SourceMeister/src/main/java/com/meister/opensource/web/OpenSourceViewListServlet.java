package com.meister.opensource.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meister.opensource.vo.SearchResultVO;

public class OpenSourceViewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OpenSourceViewListServlet() {
	}

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

		// System.out.println(urlBuilder.toString());

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");

		// System.out.println("Response code: " + conn.getResponseCode());

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

		// System.out.println(sb.toString());
		rd.close();
		conn.disconnect();

		JSONObject object = new JSONObject(sb.toString());

		JSONArray arr = object.getJSONArray("results"); // 배열단위로 추출하고 싶을때
		String total = object.get("total").toString(); // Object로 추출하고 싶을때

		Gson gson = new Gson();

		TypeToken<List<SearchResultVO>> token = new TypeToken<List<SearchResultVO>>() {
		};
		List<SearchResultVO> resultList = gson.fromJson(arr.toString(), token.getType());

		request.setAttribute("results", resultList);
		request.setAttribute("count", total);

		// 2차 검색
		for (SearchResultVO results2 : resultList) {
			String tempUrl = results2.getRepo().replaceAll("[.]git", "");
			System.out.println(tempUrl);

			String[] parameters = tempUrl.split("/");
			/*System.out.println(parameters[2]);
			System.out.println(parameters[3]);*/

			StringBuilder urlBuilder2 = new StringBuilder("https://searchcode.com/api/codesearch_I/");
			urlBuilder2.append(
					"?" + URLEncoder.encode("q", "UTF-8") + "=<" + "+" + "repo:" + parameters[3] + "/" + parameters[4]);

			System.out.println(urlBuilder2.toString());

			URL url2 = new URL(urlBuilder2.toString());
			HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
			conn2.setRequestMethod("GET");
			conn2.setRequestProperty("Content-type", "application/json");

			// System.out.println("Response code: " + conn2.getResponseCode());

			BufferedReader rd2;

			if (conn2.getResponseCode() >= 200 && conn2.getResponseCode() <= 300) {
				rd2 = new BufferedReader(new InputStreamReader(conn2.getInputStream(), "UTF-8"));
			} else {
				rd2 = new BufferedReader(new InputStreamReader(conn2.getErrorStream(), "UTF-8"));
			}

			StringBuilder sb2 = new StringBuilder();
			String line2;
			while ((line2 = rd2.readLine()) != null) {
				sb2.append(line2);
			}

			System.out.println(sb2.toString());
			rd2.close();
			conn2.disconnect();

			JSONObject object2 = new JSONObject(sb2.toString());

			JSONArray langArr = object2.getJSONArray("language_filters");

			String langTotal = object2.get("total").toString();

			
			
			if (!langTotal.equals("0")) {

				TypeToken<List<Object>> token2 = new TypeToken<List<Object>>() {
				};
				List<Object> langList = gson.fromJson(langArr.toString(), token2.getType());
				
				
				results2.setLangArr(langList);
				
				
			}

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/opensource/search.jsp");
		dispatcher.forward(request, response);

	}

}
