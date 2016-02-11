package com.ptahs.crawler.ewg;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ptahs.crawler.CrawlerExecutor;

public class Ewg extends CrawlerExecutor{
	private	String	query;
	
	@Override
	public String getUrl() {
		return "/skindeep/search.php?search_group=ingredients&atatime=9999&query=";
	}
	@Override
	public REQTYPE getRequestType() {
		return REQTYPE.GET;
	}
	@Override
	public String getQuery() {
		return this.query.replaceAll("\\s", "\\+");
	}
	@Override
	public String getSource() {
		return super.getLoadedData().toString();
	}
	@Override
	public String getHost() {
		return "http://www.ewg.org";
	}
	@Override
	public int getStartIndex() {
		return 1;
	}
	@Override
	public List<?> getList() {
		
		Document				doc;
		Elements				ele;
		List<EwgListDataSource>	rows;
		
		try{
			doc		= Jsoup.parse(this.getSource());
			ele		= doc.select("table#table-browse tbody tr");
			rows	= new ArrayList<>();
			
			int	startIndex	= this.getStartIndex();
			
			for(; startIndex < ele.size() ; startIndex++){
				Element	row		= ele.get(startIndex);
				
				Element	image	= row.select("td").get(0).select("img").first();
				Element	url		= row.select("td").get(1).select("a").first();
				Element	score	= row.select("td").get(2).select("img").first();
				
				EwgListDataSource	ds	= new EwgListDataSource();
				ds.setName(url.text());
				ds.setUrl(this.getHost() + url.attr("href"));
				ds.setImage(image.attr("src").contains("static.ewg.org") ? "" : image.attr("src"));
				ds.setScore(score.attr("src"));
				
				rows.add(ds);
			}
			return rows;
		}catch(Exception e){
			throw e;
		}
	}
	@Override
	public List<?> getData(String url) {
		return null;
	}
	@Override
	public void execute() {
		super.execte();
	}
	@Override
	public void setQuery(String query) {
		this.query	= query;
	}
}
