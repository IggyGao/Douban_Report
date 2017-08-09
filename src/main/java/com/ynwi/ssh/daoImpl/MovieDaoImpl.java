package com.ynwi.ssh.daoImpl;  

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ynwi.ssh.beans.Movie;
import com.ynwi.ssh.dao.MovieDao;  
  
public class MovieDaoImpl extends HibernateDaoSupport implements MovieDao {  
  
    @Override 
    public void saveMovie(Movie obj) throws HibernateException{  
        getHibernateTemplate().save(obj);  
    }

	@Override
    public Movie getMovieByDoubanId(int doubanId) throws HibernateException {
		String hql = "from Movie m where m.doubanId=:doubanId";//£¡£¡£¡
		Query query=getSession().createQuery(hql);
		query.setInteger("doubanId", doubanId);
	    Movie list =(Movie)query.uniqueResult();
		return list;
	}  
  
}