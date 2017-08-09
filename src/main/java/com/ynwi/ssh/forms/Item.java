package com.ynwi.ssh.forms;

import java.util.Calendar;
import java.util.Date;

public class Item 
{
	public int score;
	public Calendar watchedDate;
	public MovieInfo movie;
	
	public Item(MovieInfo movie,int score,Calendar watchedDate)
	{
		this.movie=movie;
		this.score=score;
		this.watchedDate=watchedDate;
	}
}
