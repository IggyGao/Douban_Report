package com.ynwi.ssh.forms;

import java.util.HashMap;

public class Report 
{
	public Report(int year)
	{
		this.place=new HashMap<String,Integer>();
		this.year=year;
		this.numPerMonth=new int[13];
	}
	
	public int year;
	public int num;
	public int newNum;
	//平均几天看一部，最多的一天
	public int[] numPerMonth;
	public int len;
	
	//最晚标记时间
	//观影地区
	public HashMap<String, Integer> place;
	//类别
	//打星情况
}
