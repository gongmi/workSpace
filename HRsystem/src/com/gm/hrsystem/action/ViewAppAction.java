package com.gm.hrsystem.action;

import java.util.List;

import com.gm.hrsystem.action.base.MgrBaseAction;
import com.gm.hrsystem.domain.*;
import com.gm.hrsystem.exception.HrException;
import com.opensymphony.xwork2.ActionContext;


public class ViewAppAction extends MgrBaseAction {
	List<Application> apps; //这里可以用vo 不一定要用domain object 因为这里需要的东西没那么多
	
	
	public String execute()throws HrException{
		ActionContext ctx = ActionContext.getContext();
		String name = (String) ctx.getSession().get(WebConstant.USER);
		apps=mgrManager.getAppsByMgr(name);
		return SUCCESS;
	}


	public List<Application> getApps() {
		return apps;
	}


	public void setApps(List<Application> apps) {
		this.apps = apps;
	}


 


	 

 
}
