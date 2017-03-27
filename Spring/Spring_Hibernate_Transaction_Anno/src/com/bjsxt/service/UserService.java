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
 //required:�ڵ��� add�������ʱ ����Ѿ���һ��transaction�� ���Ҿ���ԭ����transaction
	public void add(User user) {
		userDAO.save(user);
		Log log=new Log();
		log.setMsg("a user saved");
		logDAO.save(log);//��������������쳣
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
