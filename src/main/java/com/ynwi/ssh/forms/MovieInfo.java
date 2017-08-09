package com.ynwi.ssh.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovieInfo {
	private String title;
	private Integer releaseYear;
	private int doubanId;
	private String createdPlace;
	private Integer length;//时长，暂时未获取
	private Integer type; //类型，暂时未获取 
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}
	public int getDoubanId() {
		return doubanId;
	}
	public void setDoubanId(int doubanID) {
		this.doubanId = doubanID;
	}
	public String getCreatedPlace() {
		return createdPlace;
	}
	public void setCreatedPlace(String createdPlace) {
		this.createdPlace = createdPlace;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
 // 构造方法初始化数据
 public MovieInfo(int doubanId,int type,String name,int releaseYear,String createdPlace,int length) 
 {
	 this.doubanId=doubanId;
	 this.title=name;
	 this.releaseYear=releaseYear;
	 this.createdPlace=createdPlace;
	 this.length=length;
	 this.type=type;
 }
 
 public MovieInfo()
 {
	 
 }
// 
// @Override
// public String toString() {
//  return "片名："+ Name+"\n";
// }
}