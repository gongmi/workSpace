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

	public void autoPunch() {// ����

		// ���ǲ��Եģ� Ҫ��spring���õĲű�ע����sessionfactory��

		// ApplicationContext ctx = new
		// ClassPathXmlApplicationContext("beans.xml");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/WEB-INF/applicationContext.xml");
//	 WebApplicationContext ctx=WebApplicationContextUtils.getWebApplicationContext(servletContext);
		
		
		// ���������ᱨ�� ��Ϊspring�ǶԽӿڽ���������� ����˵ �� EmpBaseAction ��
		// ��protected EmpManager empManager;
		// ���empManager ��ʵ����EmpManagerImpl��ʵ�ֵ� ����������ӿڱ�̵�
		// ���Բ������Ҿ���ʵ���� �����ҽӿ�

// EmpManagerImpl empmgr=(EmpManagerImpl)ctx.getBean("empManager",EmpManagerImpl.class);
		EmpManager empmgr = (EmpManager) ctx.getBean("empManager", EmpManager.class);
		empmgr.autoPunch();
	}

	// ����������������ᱨ��
	// Exception in thread "main" org.hibernate.HibernateException: No Session
	// found for current thread
	// at
	// org.springframework.orm.hibernate4.SpringSessionContext.currentSession(SpringSessionContext.java:106)
	// at
	// org.hibernate.internal.SessionFactoryImpl.getCurrentSession(SessionFactoryImpl.java:1014)
	// at
	// com.gm.common.dao.impl.BaseDaoHibernate4.find(BaseDaoHibernate4.java:97)
	// ��Ϊempdaoû����������ʽ������ ��ֻ��empManager�е�һ��field ���ķ���û�н���������ǿ���� ����find�����е�
	// Query query = getSessionFactory().getCurrentSession().createQuery(hql);
	// û��currentsession ��Ϊû������
	// ���ǰ�empDao��Ϊ��������㣺
	// <aop:pointcut id="myPointcut" expression="bean(empDao)" />
	public void load() {// ����

		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeDao empdao = (EmployeeDao) ctx.getBean("empDao", EmployeeDao.class);
		Employee e = empdao.findByName("gongmi2");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(empdao.getClass());
		System.out.println(e.getSalary());
	}

}
