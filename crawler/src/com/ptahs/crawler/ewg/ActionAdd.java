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

@WebServlet(name = "add",	urlPatterns = "/ewg/web/add")
public class ActionAdd extends HttpServlet {
	private static final	long serialVersionUID = 2270282707172794497L;
	final					ObjectMapper	mapper	= new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAdd(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAdd(req, resp);
	}
	private void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		int				indexPrefix	= 11;
		String			name		= req.getParameter("name");
		String			score		= req.getParameter("score");
		PrintWriter		writer		= resp.getWriter();
		
		try{
			
			//score_image1_1_1_.png
			/*           ^ ^
			 *           |  \_
			 *           N2   \
			 *                 N1
			 */
			int		indexStart	= score.lastIndexOf("/") + 1;
			String	onlyScore	= score.substring(indexStart).substring(indexPrefix, indexPrefix + 5).replaceAll("_", "");
			
			int		N1			= Integer.valueOf(onlyScore.substring(1, 2));
			int		N2			= Integer.valueOf(onlyScore.substring(0, 1));
			
			
			System.out.println(N1);
			System.out.println(N2);
			
			//add new serial master when not exists!
			//
			//create master
			
			StringBuffer	sbQuery	= new StringBuffer();
			sbQuery.append("insert into ");
			
			//insert ingredient(name and score)
			//database insert
			
			writer.write(name + " has been saved!");
		}catch(Exception e){
			writer.write(e.getLocalizedMessage());
		}
	}
}
