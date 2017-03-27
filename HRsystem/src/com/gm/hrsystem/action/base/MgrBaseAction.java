package com.gm.hrsystem.action.base;


import com.gm.hrsystem.service.MgrManager;
import com.opensymphony.xwork2.ActionSupport;

 public class MgrBaseAction extends ActionSupport
 {
	 protected MgrManager mgrManager;

	public MgrManager getMgrManager() {
		return mgrManager;
	}
	public void setMgrManager(MgrManager mgrManager) {
		this.mgrManager = mgrManager;
	}

 
 
 }