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
	//ƽ�����쿴һ��������һ��
	public int[] numPerMonth;
	public int len;
	
	//������ʱ��
	//��Ӱ����
	public HashMap<String, Integer> place;
	//���
	//�������
}
