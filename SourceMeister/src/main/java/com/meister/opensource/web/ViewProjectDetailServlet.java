package com.meister.opensource.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
		
		/*
		 * 프로젝트 내용 
		 */
		
		String opensourceId = request.getParameter("opensourceId");
		
		StringBuilder urlBuilder = new StringBuilder("https://searchcode.com/api/related_results/"+ 92215288 +"/");
		//urlBuilder.append("92215288" + "/"); // opensourceId 로 바꿔주기!

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");

		System.out.println("1) Response code: " + conn.getResponseCode());

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
		
		String attribute = sb.toString().replace("[", "").replace("]", "");
		JSONObject jsonObject = new JSONObject(attribute);
		
		String reponame = jsonObject.getString("reponame");
		String source = jsonObject.getString("source");
		String sourceUrl = jsonObject.getString("sourceurl");
		int linesCount = jsonObject.getInt("linescount");
		String location = jsonObject.getString("location");
		String language = jsonObject.getString("language");
		String md5hash = jsonObject.getString("md5hash");
		long id = jsonObject.getLong("id");
		String fileName = jsonObject.getString("filename");
		
		request.setAttribute("repoName", reponame);
		request.setAttribute("source", source);
		request.setAttribute("sourceUrl", sourceUrl);
		request.setAttribute("linesCount", linesCount);
		request.setAttribute("location", location);
		request.setAttribute("language", language);
		request.setAttribute("md5hash", md5hash);
		request.setAttribute("id", id);
		request.setAttribute("fileName", fileName);
		
		/*
		 * 파일 트리 파싱
		 */
		Document doc = Jsoup.connect(sourceUrl).get();
		Elements fileTree = doc.select(".file-wrap");
		
		request.setAttribute("fileTree", fileTree);
		
		/*
		 * 결과(readme 내용)
		 */
		
		StringBuilder codeUrlBuilder = new StringBuilder("https://searchcode.com/api/result/"+ 92215288 +"/");
		
		URL codeUrl = new URL(codeUrlBuilder.toString());
		conn = (HttpURLConnection) codeUrl.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");

		System.out.println("2) Response code: " + conn.getResponseCode());

		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
		}
		
		StringBuilder sb2 = new StringBuilder();
		
		while ((line = rd.readLine()) != null) {
			sb2.append(line);
		}
		
		String code = sb2.toString();
		rd.close();
		conn.disconnect();
		
		JSONObject jsonObject2 = new JSONObject(code);
		code = jsonObject2.getString("code");
		
		request.setAttribute("code", code);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/opensource/detail.jsp");
		dispatcher.forward(request, response);
		
	}

}
