package com.ynwi.ssh.serviceImpl;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.ynwi.ssh.forms.MovieInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoubanResolver { 
	private String path="https://movie.douban.com/subject/";
	
	public MovieInfo GetMovieFromSpecificPage(int id)
	{
		try{
		String content=GetContent(id);
		int length=GetLength(content);
		String name=GetName(content);
		int year=GetReleaseYear(content);
		String place=GetCreatedPlace(content);
		MovieInfo movie=new MovieInfo(id,0,name,year,place,length);
		return movie;
		}
		catch (Exception e) {
			return null;
			  }
	}
	
	private int GetLength(String content)
	{
		Pattern LengthPattern = Pattern.compile("片长: (.+?)分钟");
		  Matcher LengthMatcher = LengthPattern.matcher(content);
		  boolean isFind = LengthMatcher.find();
		  if (isFind)
			  return Integer.parseInt(LengthMatcher.group(1).trim());
		  else return 0;
	}
	
	private String GetName(String content)
	{
		Pattern LengthPattern = Pattern.compile("(.+?) \\(豆瓣\\)");
		  Matcher LengthMatcher = LengthPattern.matcher(content);
		  boolean isFind = LengthMatcher.find();
		  if (isFind)
			  return LengthMatcher.group(1);
		  else return null;
	}
	 
	private int GetReleaseYear(String content)
	{
		Pattern ReleaseYearPattern = Pattern.compile("上映日期: (.+?)-");
		  Matcher ReleaseYearMatcher = ReleaseYearPattern.matcher(content);
		  boolean isFind = ReleaseYearMatcher.find();
		  if (isFind)
			  return Integer.parseInt(ReleaseYearMatcher.group(1));
		  else
		  { 
			   ReleaseYearPattern = Pattern.compile("首播: (.+?)-");
			   ReleaseYearMatcher = ReleaseYearPattern.matcher(content);
			   isFind = ReleaseYearMatcher.find();
				  if (isFind)
					  return Integer.parseInt(ReleaseYearMatcher.group(1));
				  else return 0;
		  }
	}
	
	private String GetCreatedPlace(String content)
	{
		Pattern ReleaseYearPattern = Pattern.compile("制片国家/地区: (.+?)\\r");
		  Matcher ReleaseYearMatcher = ReleaseYearPattern.matcher(content);
		  boolean isFind = ReleaseYearMatcher.find();
		  if (isFind)
		  {String st=ReleaseYearMatcher.group(1);
			  return st;
		  }
		  else return null;
	}
	
	private String GetContent(int id) throws Exception {  

    String url = path+id+"/";  
  
    final WebClient webClient = new WebClient(BrowserVersion.CHROME);  
  
    // 1 启动JS  
    webClient.getOptions().setJavaScriptEnabled(true);  
    // 2 禁用Css，可避免自动二次请求CSS进行渲染  
    webClient.getOptions().setCssEnabled(false);  
    // 3 启动客户端重定向  
    webClient.getOptions().setRedirectEnabled(true);  
    // 4 js运行错误时，是否抛出异常  
    webClient.getOptions().setThrowExceptionOnScriptError(false);  
    // 5 设置超时  
    webClient.getOptions().setTimeout(5000);  
    webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
    webClient.getOptions().setThrowExceptionOnScriptError(false);
    webClient.getOptions().setActiveXNative(false);
    HtmlPage htmlPage = webClient.getPage(url);  
    // 等待JS驱动dom完成获得还原后的网页  
    webClient.waitForBackgroundJavaScript(1000);  
    // 网页内容   
    webClient.close();
    String content=htmlPage.asText();
    return content;
}  
}