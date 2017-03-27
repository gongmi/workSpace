package com.gm.hrsystem.service.impl;

import com.gm.hrsystem.dao.*;
import com.gm.hrsystem.domain.*;
import com.gm.hrsystem.service.EmpManager;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component(value = "empManager")
public class EmpManagerImpl implements EmpManager {
	//业务逻辑层service/manager面向Dao接口编程 通用的接口 实现了service与Dao的分离
	//比如我可以写用JDBC实现的ManagerDaoJDBC 然后给这个Dao @Component(value="mgrDao")
	//这样我的EmpManagerImpl根本无需改变 
	//这样各层以松耦合的方式组织
	private ManagerDao mgrDao;
	private EmployeeDao empDao;
	private AttendTypeDao typeDao;
	private AttendDao attendDao;
	private ApplicationDao appDao;
	private PaymentDao payDao;
	
	public EmpManagerImpl() {
		System.out.println(this.getClass().getSimpleName()+"无参数的构造器");
	}

	@Override
	public int validLogin(Manager mgr) {
		if (mgrDao.findByNameAndPass(mgr) != null)
			return LOGIN_MGR;
		else if (empDao.findByNameAndPass(mgr) != null)
			return LOGIN_EMP;
		else
			return LOGIN_FAIL;

	}

	@Override
	public void autoPunch() {
		System.out.println("+++++++++++自动插入旷工记录+++++++++");
		List<Employee> emps = empDao.findAll(Employee.class);
		Date date = new java.sql.Date(System.currentTimeMillis());
		String dutyday = date.toString();
		AttendType type = typeDao.get(AttendType.class, 6);
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

		for (Employee e : emps) {
			Attend a = new Attend();
			a.setType(type);
			a.setDutyDay(dutyday);
			a.setEmployee(e);
			if (hour < AM_LIMIT)
				a.setIsCome(true);
			else
				a.setIsCome(false);
			attendDao.save(a);
		}
	}

	@Override
	public void autoPay() {
		System.out.println("+++++++++++自动插入工资结算+++++++++");
		List<Employee> emps = empDao.findAll(Employee.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);

		String month = sdf.format(c.getTime());
 
		for (Employee e : emps) {
			double amount = e.getSalary();
			List<Attend> attends = attendDao.findByEmpAndMonth(e, month);
			System.out.println(month);
			System.out.println(attends.size());
			for (Attend att : attends) {
				amount -= att.getType().getAmerce();
				System.out.println(amount);
			}
			Payment pay = new Payment();

			pay.setEmployee(e);
			pay.setPayMonth(month);
			pay.setAmount(amount);

			payDao.save(pay);
		}

	}

	@Override
	public int validPunch(String user, String dutyDay) {
		Employee emp = empDao.findByName(user);//也能一并查到manager的
		List<Attend> attends = attendDao.findByEmpAndDutyDay(emp, dutyDay);
		if (attends == null || attends.size() == 0)
			return NO_PUNCH;
		else if (attends.size() == 1 && attends.get(0).getIsCome()
				&& attends.get(0).getPunchTime() == null)
			return COME_PUNCH;
		else if (attends.size() == 1 && attends.get(0).getPunchTime() == null)
			return LEAVE_PUNCH;
		else if (attends.size() == 2) {
			if (attends.get(0).getPunchTime() == null
					&& attends.get(1).getPunchTime() == null)
				return BOTH_PUNCH;
			else if (attends.get(1).getPunchTime() == null)
				return LEAVE_PUNCH;
			else if (attends.get(0).getPunchTime() == null)
				return COME_PUNCH;
			else
				return NO_PUNCH;
		} else
			return NO_PUNCH;

	}

	@Override
	public int punch(String user, String dutyDay, boolean isCome) {
		Employee emp = empDao.findByName(user);
		Attend attend = attendDao.findByEmpAndDutyDayAndCome(emp, dutyDay,
				isCome);
		attend.setPunchTime(new Date());
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		System.out.println(hour);


		if (isCome) {
			if (hour < COME_LIMIT)
				attend.setType(typeDao.get(AttendType.class, 1));
			else if (hour < LATE_LIMIT)
				attend.setType(typeDao.get(AttendType.class, 4));
		} else {
			if (hour >= LEAVE_LIMIT)
				attend.setType(typeDao.get(AttendType.class, 1));
			else if (hour >= EARLY_LIMIT)
				attend.setType(typeDao.get(AttendType.class, 5));
		}
		attendDao.save(attend);
		return PUNCH_SUCC;

	}

	@Override
	public List<Payment> empSalary(String empName) {
		Employee emp = empDao.findByName(empName);
		return payDao.findByEmp(emp);

	}

	@Override
	public List<Attend> unAttend(String empName) {
		Employee emp = empDao.findByName(empName);
		AttendType type = typeDao.get(AttendType.class, 1);
		return attendDao.findByEmpUnAttend(emp, type);
	}

	@Override
	public List<AttendType> getAllType() {
		return typeDao.findAll(AttendType.class);

	}

	@Override
	public boolean addApplication(int attId, int typeId, String reason) {
		Application app = new Application();
		AttendType type = typeDao.get(AttendType.class, typeId);
		Attend att = attendDao.get(Attend.class, attId);
		app.setReason(reason);
		app.setAttend(att);
		app.setType(type);
		// app.setResult(false);默认就是false
		appDao.save(app);
		return true;
	}

	public ManagerDao getMgrDao() {
		return mgrDao;
	}

	@Resource
	public void setMgrDao(ManagerDao mgrDao) {
		this.mgrDao = mgrDao;
	}

	public EmployeeDao getEmpDao() {
		return empDao;
	}

	@Resource
	public void setEmpDao(EmployeeDao empDao) {
		this.empDao = empDao;
	}

	public AttendTypeDao getTypeDao() {
		return typeDao;
	}

	@Resource
	public void setTypeDao(AttendTypeDao typeDao) {
		this.typeDao = typeDao;
	}

	public AttendDao getAttendDao() {
		return attendDao;
	}

	@Resource
	public void setAttendDao(AttendDao attendDao) {
		this.attendDao = attendDao;
		System.out.println( "attendDao正在注入"+this.getClass().getSimpleName()+"中");
	}

	public ApplicationDao getAppDao() {
		return appDao;
	}

	@Resource
	public void setAppDao(ApplicationDao appDao) {
		this.appDao = appDao;
		System.out.println( "appDao正在注入"+this.getClass().getSimpleName()+"中");
	}

	public PaymentDao getPayDao() {
		return payDao;
	}

	@Resource
	public void setPayDao(PaymentDao payDao) {
		this.payDao = payDao;
	}

}