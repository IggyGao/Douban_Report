package com.ynwi.ssh.beans;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Movie {
	private String title;
	private Integer releaseYear;
	private int id;//�Զ�����
	private int doubanId;
	private String createdPlace;
	private Integer length;//ʱ������ʱδ��ȡ
	private Integer type; //���ͣ���ʱδ��ȡ 
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
// // ���췽����ʼ������
// public Movie(String doubanId,String id,String name,int releaseYear,String createdPlace,int length) {
//	 DoubanID=
//	 Id=id;
//	 //Url="https://movie.douban.com/subject/"+ID+"/";
//	 Name=name;
//	 ReleaseYear=releaseYear;
//	 CreatedPlace=createdPlace;
//	 Length=length;
// }
// 
// @Override
// public String toString() {
//  return "Ƭ����"+ Name+"\n";
// }
}