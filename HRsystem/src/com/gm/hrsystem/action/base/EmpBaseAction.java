package com.gm.hrsystem.action.base;

import javax.annotation.Resource;

import com.gm.hrsystem.service.EmpManager;
import com.opensymphony.xwork2.ActionSupport;

 public class EmpBaseAction extends ActionSupport
 {
	 protected EmpManager empManager;//ֻ��EmpManager�ӿ����

	public EmpManager getEmpManager() {
		return empManager;
	}
 
	public void setEmpManager(EmpManager empManager) {
		this.empManager = empManager;
	}
 
 }