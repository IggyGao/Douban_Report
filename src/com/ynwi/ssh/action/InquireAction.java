package com.ynwi.ssh.action;  

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ynwi.ssh.forms.MovieInfo;
import com.ynwi.ssh.forms.Report;
import com.ynwi.ssh.service.BaseManager;
import com.ynwi.ssh.serviceImpl.Manager;  
  
public class InquireAction extends ActionSupport {  
  
    private static final long serialVersionUID = 1L;  
  
    private int userName; 
    private int year;
  
    private Manager userManager;  
  
    public int getUserName() {  
        return userName;  
    }  
    public void setUserName(int user) {  
        this.userName = user;  
    }  	
    public int getYear() {
		return year;
	}
    public void setYear(int year) {
		this.year = year;
	}  
  
    public void setUserManager(Manager userManager) {  
        this.userManager = userManager;  
    }  
  
    public String execute() {  
        try 
        {  
            Report report=userManager.getMovies(String.valueOf(userName),year); 
            Map request = (Map) ActionContext.getContext().get("request");
    		request.put("list", report);
            return SUCCESS;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return ERROR;  
        }  
    }


  
}  