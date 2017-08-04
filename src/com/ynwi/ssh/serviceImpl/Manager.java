package com.ynwi.ssh.serviceImpl;  
  
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.HibernateException;  
import org.springframework.beans.BeanUtils;

import com.ynwi.ssh.beans.Movie;
import com.ynwi.ssh.dao.MovieDao;
import com.ynwi.ssh.forms.Item;
import com.ynwi.ssh.forms.MovieInfo;
import com.ynwi.ssh.forms.Report;
import com.ynwi.ssh.service.BaseManager;  
  
public class Manager 
{ 
	private DbManager dbManager;
	private ReportManager reportManager;
	private Spider spider;
	private int year;
	private DoubanResolver resolver;
	
	public DoubanResolver getResolver() {
		return resolver;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setResolver(DoubanResolver resolver) {
		this.resolver = resolver;
	}
	public DbManager getDbManager() {
		return dbManager;
	}
	public void setDbManager(DbManager dbManager) {
		this.dbManager = dbManager;
	}
	public Spider getSpider() {
		return spider;
	}
	public void setSpider(Spider spider) {
		this.spider = spider;
	}

	
	public Report getMovies(String userId,int year) throws Exception
	{
		this.year=year;
		reportManager=new ReportManager(year);
		ArrayList<MovieInfo> res=new ArrayList<MovieInfo>();
		String url="https://movie.douban.com/people/"+userId+"/collect?start=0&sort=time&rating=all&filter=all&mode=grid";
		String content = spider.sendGet(url);
		int num=GetNum(content);
		int groupNum=Math.floorDiv(num, 15);
		for (int i=0;i<=groupNum;i++)
		{
			String aa=String.valueOf(i*15);
			url="https://movie.douban.com/people/"+userId
					+"/collect?start="+aa
					+ "&sort=time&rating=all&filter=all&mode=grid";
			content = spider.sendGet(url);
			ArrayList<Item> temp=GetMovies(content);
			reportManager.genRep(temp);
		}
		return reportManager.report;
	}
	
	
	public int GetNum(String content)
	{
		//看过的电影数目示例： <title>    高杲杲看过的电影(908)</title> 
		 Pattern numPattern = Pattern.compile(" <title>(.+?)看过的电影.(.+?).</title>");
		 Matcher numMatcher = numPattern.matcher(content);
		 if(numMatcher.find())
		 {
			 int num=Integer.parseInt(numMatcher.group(2));
			 return num;
		 }
		 else return 0;
	}
	
	public ArrayList<Item> GetMovies(String content) throws Exception{
		  // 预定义一个ArrayList来存储结果
		  ArrayList<Item> results = new ArrayList<Item>();
		  // 电影id，通过id可以生成url
		  //html示例：<a href="https://movie.douban.com/subject/3016187/" class="">                            <em>权力的游戏 第一季 / Game of Thrones Season 1</em>                             / 冰与火之歌：权力的游戏 第一季 / 王座游戏 第一季                        </a>  
		  Pattern idPattern = Pattern.compile("<a href=\"https://movie.douban.com/subject/(.+?)/\"");
		  Matcher idMatcher = idPattern.matcher(content);
		  //标记日期
		  //html示例：<span class="date">2017-03-02</span>
		  Pattern whatchedDatePattern = Pattern.compile("<span class=\"date\">(.+?)</span>");
		  Matcher whatchedMatcher = whatchedDatePattern.matcher(content);
		  //用户对次电影的评分
		  //html示例：<span class="rating4-t"></span> 
		  Pattern ScorePattern = Pattern.compile("<span class=\"rating(.+?)-t\"></span>");
		  Matcher scoreMatcher = ScorePattern.matcher(content);
		  while (idMatcher.find()&&whatchedMatcher.find()&&scoreMatcher.find()) 
		  {
			  int id=Integer.valueOf(idMatcher.group(1));
			  String whatchedDate=whatchedMatcher.group(1);
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			  Date date = sdf.parse(whatchedDate.toString()); 
			  Calendar cal=Calendar.getInstance();  
			  cal.setTime(date);  
			  int score=Integer.parseInt(scoreMatcher.group(1));
			  if(cal.get(Calendar.YEAR)>year)
				  continue;
			  else
			  {
				  if(cal.get(Calendar.YEAR)==year)
				  {
					  MovieInfo movie=dbManager.getMovieByDoubanId(id);
					  if(movie==null)
					  {
						  movie=getResolver().GetMovieFromSpecificPage(id);
						  dbManager.saveMovie(movie);
						  Item item=new Item(movie,score,cal);
						  results.add(item);
					  }
				  }
				  else
					  break;
			  }
		  }
		  return results;
	}



}  