package com.ptahs.crawler.ewg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(name = "search",	urlPatterns = "/ewg/web/search")
public class Search extends HttpServlet {
	private static final	long serialVersionUID = 2270282707172794497L;
	final					ObjectMapper	mapper	= new ObjectMapper();
	final					Ewg				ewg		= new Ewg();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doSearch(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doSearch(req, resp);
	}
	private void doSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		String					query	= req.getParameter("query");
		PrintWriter				writer	= resp.getWriter();
		List<EwgListDataSource>	rows	= null;
		try{
			ewg.setQuery(query);
			ewg.execute();
			rows	= (List<EwgListDataSource>)ewg.getList();
			Map<String,Object>	out	= new HashMap<>();
			out.put("rows", rows);
			writer.write(mapper.writeValueAsString(out));
		}catch(Exception e){
			writer.write(e.getLocalizedMessage());
		}
	}
}
