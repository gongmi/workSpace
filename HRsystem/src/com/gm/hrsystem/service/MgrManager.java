package com.gm.hrsystem.service;

import com.gm.hrsystem.domain.*;

import java.util.*;
public interface MgrManager
{
	/**
	 * 新增员工
	 * @param emp 新增的员工
	 * @param mgr 员工所属的经理
	 */
	void addEmp(Employee emp , String mgrName);
	


	/**
	 * 根据经理返回部门所有的员工上个月工资
	 * @param mgr 经理名
	 * @return 部门上个月工资
	 */
	List<Payment> getSalaryByMgr(String mgrName);

	/**
	 * 根据经理返回该部门的全部员工
	 * @param mgr 经理名
	 * @return 经理的全部下属
	 */
	List<Employee> getEmpsByMgr(String mgrName);

	/**
	 * 根据经理返回该部门的没有批复的申请
	 * @param mgr 经理名
	 * @return 该部门的全部申请
	 */
	List<Application> getAppsByMgr(String mgrName);

	/**
	 * 处理申请
	 * @param appid 申请ID
	 * @param mgrName 经理名字
	 * @param result 是否通过
	 */
	void check(int appid, String mgrName, boolean result);
}