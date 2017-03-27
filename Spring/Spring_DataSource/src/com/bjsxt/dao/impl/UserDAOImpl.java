package com.bjsxt.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.*;

import org.springframework.stereotype.Component;

import com.bjsxt.dao.UserDAO;
import com.bjsxt.model.User;
 

@Component("u") 
public class UserDAOImpl implements UserDAO {

	private DataSource datasource;
	
	public void save(User user)  {
		//Hibernate
		//JDBC
		//XML
		//NetWork
		try {
			Connection conn=datasource.getConnection();
			conn.createStatement().executeUpdate("insert into user values (null,'gongmi')");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("user saved!");
		//throw new RuntimeException("exeption!");
	}

	public DataSource getDatasource() {
		return datasource;
	}
@Resource
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

}
