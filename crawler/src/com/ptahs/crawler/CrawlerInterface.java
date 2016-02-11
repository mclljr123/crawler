package com.ptahs.crawler;

import java.util.List;

interface CrawlerInterface {
	enum REQTYPE {
		GET,
		POST
	}
	String getUrl();
	String getQuery();
	String getSource();
	String getHost();
	void execute();
	void setQuery(String query);
	int getStartIndex();
	List<?> getList();
	List<?> getData(String url);
	REQTYPE getRequestType();
}
