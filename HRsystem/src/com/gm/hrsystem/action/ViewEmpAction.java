package com.gm.hrsystem.action;

import java.util.List;

import com.gm.hrsystem.action.base.MgrBaseAction;
import com.gm.hrsystem.domain.*;
import com.opensymphony.xwork2.ActionContext;


public class ViewEmpAction extends MgrBaseAction {
	List<Employee> emps; //这里可以用vo 不一定要用domain object 因为这里需要的东西没那么多
 
	
	public String execute(){
		ActionContext ctx = ActionContext.getContext();
		String name = (String) ctx.getSession().get(WebConstant.USER);
		emps=mgrManager.getEmpsByMgr(name);
		 
		return SUCCESS;
	}


	public List<Employee> getEmps() {
		return emps;
	}


	public void setEmps(List<Employee> emps) {
		this.emps = emps;
	}


 


	 

 
}
