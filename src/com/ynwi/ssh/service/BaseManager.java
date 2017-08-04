package com.ynwi.ssh.service;  
  
import java.util.List;

import com.ynwi.ssh.forms.MovieInfo;
  
public interface BaseManager 
{  
    public void saveMovie(MovieInfo user);  
    public MovieInfo getMovieByDoubanId(int doubanId);
}  