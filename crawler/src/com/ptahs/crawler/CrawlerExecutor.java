package com.ptahs.crawler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

public abstract class CrawlerExecutor implements CrawlerInterface{
	
	final	static	private	String	USER_AGENT	= "Mozilla/5.0";
	
	private	StringBuffer	data;
	
	public CrawlerExecutor(){
		System.out.println("Crawler Executed!");
	}
	protected void exec(){
		URL					url;
		HttpURLConnection	con;
		BufferedReader		br;
		StringBuffer		response;
		
		try{
			
			System.out.println("Sending '" + getRequestType() + "' request to URL : " + getHost() + getUrl() + getQuery());
			
			if(getRequestType().equals(REQTYPE.GET)){
				url	= new URL(getHost() + getUrl() + getQuery());
			}else{
				url	= new URL(getHost() + getUrl());
			}
			
			con	= (HttpURLConnection)url.openConnection();
			con.setRequestProperty("User-Agent", USER_AGENT);
			
			//send get
			if(getRequestType().equals(REQTYPE.GET)){
				con.setRequestMethod("GET");
			}
			//send post
			if(getRequestType().equals(REQTYPE.POST)){
				con.setRequestMethod("POST");
				con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				
				con.setDoOutput(true);
				DataOutputStream	wr	= new DataOutputStream(con.getOutputStream());
				wr.writeBytes(getQuery());
				wr.flush();
				wr.close();
				
			}
			if(con.getResponseCode() != HttpServletResponse.SC_OK){
				throw new IOException();
			}
			
			br	= new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String	inputLine;
			response	= new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine).append("\r\n");
			}
			br.close();
			this.data	= response;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	protected StringBuffer getLoadedData(){
		return this.data;
	}
}
