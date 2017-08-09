package com.ynwi.ssh.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;

import com.ynwi.ssh.forms.Item;
import com.ynwi.ssh.forms.Report;

public class ReportManager 
{
	public Report report;
	public ReportManager(int year)
	{
		report=new Report(year);
	}
	
	public void genRep(ArrayList<Item> list)
	{
		report.num+=list.size();
		for(Item item:list)
		{
			report.len+=item.movie.getLength();
			if(item.movie.getReleaseYear()==report.year)
				report.newNum++;
			report.numPerMonth[item.watchedDate.get(Calendar.MONTH)]++;
		}
	}

}
