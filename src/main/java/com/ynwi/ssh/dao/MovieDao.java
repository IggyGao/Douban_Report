package com.ynwi.ssh.dao;  

import java.util.List;

import org.hibernate.HibernateException;

import com.ynwi.ssh.beans.Movie;  
  
public interface MovieDao {  
  
    public void saveMovie(Movie obj) throws HibernateException;
    public Movie getMovieByDoubanId(int doubanId) throws HibernateException;
}    