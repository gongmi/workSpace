package com.gm.hrsystem.test;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.*;

import org.hibernate.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gm.common.dao.impl.BaseDaoHibernate4;
import com.gm.hrsystem.dao.EmployeeDao;
import com.gm.hrsystem.dao.impl.ApplicationDaoH4;
import com.gm.hrsystem.dao.impl.EmployeeDaoH4;
import com.gm.hrsystem.domain.Employee;
import com.gm.hrsystem.service.EmpManager;
import com.gm.hrsystem.service.impl.EmpManagerImpl;
import com.opensymphony.xwork2.ActionSupport;

public class Test {

	public static void main(String[] args) {
		 Test t = new Test();
		 t.autoPunch();
	}

	public void autoPunch() {// 建表

		// 这是不对的！ 要从spring中拿的才被注入了sessionfactory了

		// ApplicationContext ctx = new
		// ClassPathXmlApplicationContext("beans.xml");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/WEB-INF/applicationContext.xml");
//	 WebApplicationContext ctx=WebApplicationContextUtils.getWebApplicationContext(servletContext);
		
		
		// 下面这样会报错 因为spring是对接口进行事务代理 比如说 在 EmpBaseAction 中
		// 有protected EmpManager empManager;
		// 这个empManager 其实是由EmpManagerImpl来实现的 所以是面向接口编程的
		// 所以不能是找具体实现类 而是找接口

// EmpManagerImpl empmgr=(EmpManagerImpl)ctx.getBean("empManager",EmpManagerImpl.class);
		EmpManager empmgr = (EmpManager) ctx.getBean("empManager", EmpManager.class);
		empmgr.autoPunch();
	}

	// 调用下面这个函数会报错：
	// Exception in thread "main" org.hibernate.HibernateException: No Session
	// found for current thread
	// at
	// org.springframework.orm.hibernate4.SpringSessionContext.currentSession(SpringSessionContext.java:106)
	// at
	// org.hibernate.internal.SessionFactoryImpl.getCurrentSession(SessionFactoryImpl.java:1014)
	// at
	// com.gm.common.dao.impl.BaseDaoHibernate4.find(BaseDaoHibernate4.java:97)
	// 因为empdao没有配置声明式事务处理 它只是empManager中的一个field 它的方法没有进行事务增强处理 所以find方法中的
	// Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	// 没有currentsession 因为没有事务！
	// 除非把empDao作为事务切入点：
	// <aop:pointcut id="myPointcut" expression="bean(empDao)" />
	public void load() {// 建表

		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeDao empdao = (EmployeeDao) ctx.getBean("empDao", EmployeeDao.class);
		Employee e = empdao.findByName("gongmi2");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(empdao.getClass());
		System.out.println(e.getSalary());
	}

}
