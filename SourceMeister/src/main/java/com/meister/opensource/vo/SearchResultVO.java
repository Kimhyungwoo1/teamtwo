package com.meister.opensource.vo;

import java.util.List;
import java.util.TreeMap;

public class SearchResultVO {

	private String repo;
	private String linescount;
	private String location;
	private String name;
	private String language;
	private String url;
	private String md5hash;
	private TreeMap<Integer, String> lines;
	private String id;
	private String filename;

	private List<LanguageVO> langArr;
	private List<SourceVO> sourceArr;
	
	public String getRepo() {
		return repo;
	}
	public void setRepo(String repo) {
		this.repo = repo;
	}
	public String getLinescount() {
		return linescount;
	}
	public void setLinescount(String linescount) {
		this.linescount = linescount;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMd5hash() {
		return md5hash;
	}
	public void setMd5hash(String md5hash) {
		this.md5hash = md5hash;
	}
	public TreeMap<Integer, String> getLines() {
		return lines;
	}
	public void setLines(TreeMap<Integer, String> lines) {
		this.lines = lines;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public List<LanguageVO> getLangArr() {
		return langArr;
	}
	public void setLangArr(List<LanguageVO> langArr) {
		this.langArr = langArr;
	}
	public List<SourceVO> getSourceArr() {
		return sourceArr;
	}
	public void setSourceArr(List<SourceVO> sourceArr) {
		this.sourceArr = sourceArr;
	}



}
