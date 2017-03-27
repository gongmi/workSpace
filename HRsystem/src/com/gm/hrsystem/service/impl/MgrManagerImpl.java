package com.gm.hrsystem.service.impl;

import com.gm.hrsystem.dao.*;
import com.gm.hrsystem.domain.*;
import com.gm.hrsystem.service.MgrManager;

import java.util.*;

import javax.annotation.Resource;

import com.gm.hrsystem.exception.HrException;
import org.springframework.stereotype.Component;

@Component(value = "mgrManager")
public class MgrManagerImpl implements MgrManager {
	private ManagerDao mgrDao;
	private EmployeeDao empDao;
	private AttendTypeDao typeDao;
	private AttendDao attendDao;
	private ApplicationDao appDao;
	private PaymentDao payDao;
	private CheckBackDao checkDao;

	@Override
	public void addEmp(Employee emp, String mgrName) {
		Manager Mgr = mgrDao.findByName(mgrName);
		emp.setManager(Mgr);
		empDao.save(emp);

	}

	@Override
	public List<Payment> getSalaryByMgr(String mgrName) {
		List<Employee> emps = this.getEmpsByMgr(mgrName);
		List<Payment> pays = new ArrayList<Payment>();
		for (Employee e : emps) {
			pays.addAll(payDao.findByEmp(e));
		}
		return pays;
	}

	@Override
	public List<Employee> getEmpsByMgr(String mgrName) {
		Manager Mgr = mgrDao.findByName(mgrName);

		Set<Employee> es = Mgr.getEmployees();
		List<Employee> emps = new ArrayList<Employee>();
		for (Employee e : es) {
			emps.add(e);
		}
		return emps;
	}
	//这里可以用vo/DTO/JavaBean AppBean 将应用底层的状态信息Application封装成javabean AppBean
	@Override
	public List<Application> getAppsByMgr(String mgrName) throws HrException {
		List<Employee> emps = this.getEmpsByMgr(mgrName);
		List<Application> apps = new ArrayList<Application>();
		for (Employee e : emps) {
			apps.addAll(appDao.findByEmp(e));
		}

		List<Application> appsundealed = new ArrayList<Application>();
		for (Application a : apps) {
			if (!a.getResult())
				appsundealed.add(a);
		}
		if (appsundealed == null || appsundealed.size() == 0)
			throw new HrException("没有员工发来的申请~");

		return appsundealed;

	}

	@Override
	public void check(int appid, String mgrName, boolean result) {
		Manager Mgr = mgrDao.findByName(mgrName);
		Application app = appDao.get(Application.class, appid);
		app.setResult(true);
		appDao.update(app);
		CheckBack checkback = new CheckBack();
		checkback.setApp(app);
		checkback.setResult(result);
		checkback.setManager(Mgr);
		if (result)// 如果同意申请 则要修改数据库中原有的attend的type
		{
			Attend att = app.getAttend();
			att.setType(app.getType());
			attendDao.update(att);
		}

		checkDao.save(checkback);

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
	}

	public ApplicationDao getAppDao() {
		return appDao;
	}

	@Resource
	public void setAppDao(ApplicationDao appDao) {
		this.appDao = appDao;
	}

	public PaymentDao getPayDao() {
		return payDao;

	}

	@Resource
	public void setPayDao(PaymentDao payDao) {
		this.payDao = payDao;
	}

	public CheckBackDao getCheckDao() {
		return checkDao;
	}

	@Resource
	public void setCheckDao(CheckBackDao checkDao) {
		this.checkDao = checkDao;
	}

}