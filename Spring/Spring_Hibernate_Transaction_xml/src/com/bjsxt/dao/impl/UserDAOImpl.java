package com.bjsxt.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.bjsxt.dao.UserDAO;
import com.bjsxt.model.User;
 

@Component("u") 
public class UserDAOImpl implements UserDAO {

//	private DataSource datasource;
	private SessionFactory sessionfactory;
	
	public void save(User user)  {
		 
//			Connection conn=datasource.getConnection();
//			conn.createStatement().executeUpdate("insert into user values (null,'gongmi')");
//			conn.close();
	                 
			Session s=sessionfactory.getCurrentSession();
			s.save(user);
		
		System.out.println("user saved!");


	}

	public SessionFactory getSessionfactory() {
		return sessionfactory;
	}
	@Resource
	public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}

//	public DataSource getDatasource() {
//		return datasource;
//	}
//@Resource
//	public void setDatasource(DataSource datasource) {
//		this.datasource = datasource;
//	}
	
	

}
