package com.ynwi.ssh.serviceImpl;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;

import com.ynwi.ssh.beans.Movie;
import com.ynwi.ssh.dao.MovieDao;
import com.ynwi.ssh.forms.MovieInfo;
import com.ynwi.ssh.service.BaseManager;

public class DbManager implements BaseManager 
{
    private MovieDao dao;  
  
    public void setDao(MovieDao dao) {  
        this.dao = dao;  
    }  
  
    @Override  
    public void saveMovie(MovieInfo moviewForm) 
    		throws HibernateException 
    {  
        Movie movie = new Movie();  
        BeanUtils.copyProperties(moviewForm, movie);  
        dao.saveMovie(movie);  
    }  
    
    @Override  
    public MovieInfo getMovieByDoubanId(int doubanId)
    { 
    	 Movie Movie=dao.getMovieByDoubanId(doubanId);
    	 if(Movie==null)
    		 return null;
    	 MovieInfo movieInfo = new MovieInfo(); 
         BeanUtils.copyProperties(Movie, movieInfo); 
         return movieInfo;
    }
  
}
