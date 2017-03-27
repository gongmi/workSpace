package com.bjsxt.service;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bjsxt.dao.LogDAO;
import com.bjsxt.dao.UserDAO;
import com.bjsxt.model.Log;
import com.bjsxt.model.User;


@Component("userService")
public class UserService {
	
	private UserDAO userDAO;  
	private LogDAO logDAO;
	
 @Transactional
 //required:在调用 add这个方法时 如果已经有一个transaction了 那我就用原来的transaction
	public void add(User user) {
		userDAO.save(user);
		Log log=new Log();
		log.setMsg("a user saved");
		logDAO.save(log);//如果在这里遇到异常
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	@Resource(name="u")
	public void setUserDAO( UserDAO userDAO) {
		this.userDAO = userDAO;
	}
 
	public LogDAO getLogDAO() {
		return logDAO;
	}
	@Resource 
	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}
}
