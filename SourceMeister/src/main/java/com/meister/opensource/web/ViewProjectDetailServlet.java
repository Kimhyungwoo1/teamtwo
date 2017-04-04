package com.meister.opensource.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.meister.opensource.service.OpensourceService;
import com.meister.opensource.service.OpensourceServiceImpl;
import com.meister.opensource.vo.OpensourceVO;

public class ViewProjectDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OpensourceService opensourceService;

	public ViewProjectDetailServlet() {
		opensourceService = new OpensourceServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opensourceId = request.getParameter("opensourceId");

		if (opensourceService.getOneOpensource(opensourceId) == null) {
			if (opensourceService.addOneOpensource(opensourceId)) {
				showDetail(request, response, opensourceId);
			} else {
				response.sendError(404);
			}
		} else {
			showDetail(request, response, opensourceId);
		}

	}

	private void showDetail(HttpServletRequest request, HttpServletResponse response, String opensourceId)
			throws MalformedURLException, IOException, ProtocolException, UnsupportedEncodingException,
			ServletException {
		BufferedReader rd;
	

		StringBuilder urlBuilder = new StringBuilder(
				"https://searchcode.com/api/related_results/" + opensourceId + "/");

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = getUrlConnection(url);

		System.out.println("1) Response code: " + conn.getResponseCode());

		rd = isResponseSuccess(conn);

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}

		rd.close();
		conn.disconnect();

		String attribute = sb.toString().replace("[", "").replace("]", "");
		System.out.println(attribute);


		JSONObject jsonObject = new JSONObject(attribute);

		String sourceUrl = getAttribute(request, jsonObject);

		/*
		 * �뙆�씪 �듃由� �뙆�떛
		 */
		getFileTree(request, sourceUrl);

		/*
		 * likeCount
		 */

		OpensourceVO opensourceVO = opensourceService.getOneOpensource(opensourceId);

		request.setAttribute("likeCount", opensourceVO.getLikeCount());

		/*
		 * 寃곌낵(readme �궡�슜)
		 */

		StringBuilder codeUrlBuilder = new StringBuilder("https://searchcode.com/api/result/" + opensourceId + "/");

		URL codeUrl = new URL(codeUrlBuilder.toString());
		conn = getUrlConnection(codeUrl);

		System.out.println("2) Response code: " + conn.getResponseCode());

		rd = isResponseSuccess(conn);

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

		request.setAttribute("includeUrl", "/WEB-INF/view/opensource/detail.jsp");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/opensource/search.jsp");
		dispatcher.forward(request, response);
	}

	private String getAttribute(HttpServletRequest request, JSONObject jsonObject) {
		String reponame = jsonObject.getString("reponame");
		String source = jsonObject.getString("source");
		String sourceUrl = jsonObject.getString("sourceurl");
		int linesCount = jsonObject.getInt("linescount");
		String location = jsonObject.getString("location");
		String language = jsonObject.getString("language");
		String md5hash = jsonObject.getString("md5hash");
		int id = jsonObject.getInt("id");
		String fileName = jsonObject.getString("filename");

		request.setAttribute("repoName", reponame);
		request.setAttribute("source", source);
		request.setAttribute("sourceUrl", sourceUrl);
		request.setAttribute("linesCount", linesCount);
		request.setAttribute("location", location);
		request.setAttribute("language", language);
		request.setAttribute("md5hash", md5hash);
		request.setAttribute("opensourceId", id);
		request.setAttribute("fileName", fileName);
		return sourceUrl;
	}

	private HttpURLConnection getUrlConnection(URL url) throws IOException, ProtocolException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		return conn;
	}

	private BufferedReader isResponseSuccess(HttpURLConnection conn) throws IOException, UnsupportedEncodingException {
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
		}
		return rd;
	}

	private void getFileTree(HttpServletRequest request, String sourceUrl) throws IOException {

		Document doc = null;

		try {
			doc = Jsoup.connect(sourceUrl).get();
		} catch (HttpStatusException e) {

			return;

		}

		Elements fileTree = doc.select(".file-wrap");

		request.setAttribute("fileTree", fileTree);
	}

}

