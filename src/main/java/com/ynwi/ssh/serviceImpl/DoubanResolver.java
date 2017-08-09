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
		Pattern LengthPattern = Pattern.compile("Ƭ��: (.+?)����");
		  Matcher LengthMatcher = LengthPattern.matcher(content);
		  boolean isFind = LengthMatcher.find();
		  if (isFind)
			  return Integer.parseInt(LengthMatcher.group(1).trim());
		  else return 0;
	}
	
	private String GetName(String content)
	{
		Pattern LengthPattern = Pattern.compile("(.+?) \\(����\\)");
		  Matcher LengthMatcher = LengthPattern.matcher(content);
		  boolean isFind = LengthMatcher.find();
		  if (isFind)
			  return LengthMatcher.group(1);
		  else return null;
	}
	 
	private int GetReleaseYear(String content)
	{
		Pattern ReleaseYearPattern = Pattern.compile("��ӳ����: (.+?)-");
		  Matcher ReleaseYearMatcher = ReleaseYearPattern.matcher(content);
		  boolean isFind = ReleaseYearMatcher.find();
		  if (isFind)
			  return Integer.parseInt(ReleaseYearMatcher.group(1));
		  else
		  { 
			   ReleaseYearPattern = Pattern.compile("�ײ�: (.+?)-");
			   ReleaseYearMatcher = ReleaseYearPattern.matcher(content);
			   isFind = ReleaseYearMatcher.find();
				  if (isFind)
					  return Integer.parseInt(ReleaseYearMatcher.group(1));
				  else return 0;
		  }
	}
	
	private String GetCreatedPlace(String content)
	{
		Pattern ReleaseYearPattern = Pattern.compile("��Ƭ����/����: (.+?)\\r");
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
  
    // 1 ����JS  
    webClient.getOptions().setJavaScriptEnabled(true);  
    // 2 ����Css���ɱ����Զ���������CSS������Ⱦ  
    webClient.getOptions().setCssEnabled(false);  
    // 3 �����ͻ����ض���  
    webClient.getOptions().setRedirectEnabled(true);  
    // 4 js���д���ʱ���Ƿ��׳��쳣  
    webClient.getOptions().setThrowExceptionOnScriptError(false);  
    // 5 ���ó�ʱ  
    webClient.getOptions().setTimeout(5000);  
    webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
    webClient.getOptions().setThrowExceptionOnScriptError(false);
    webClient.getOptions().setActiveXNative(false);
    HtmlPage htmlPage = webClient.getPage(url);  
    // �ȴ�JS����dom��ɻ�û�ԭ�����ҳ  
    webClient.waitForBackgroundJavaScript(1000);  
    // ��ҳ����   
    webClient.close();
    String content=htmlPage.asText();
    return content;
}  
}