package com.gm.hrsystem.action;

import com.gm.hrsystem.action.base.MgrBaseAction;
import com.gm.hrsystem.domain.*;
import com.opensymphony.xwork2.ActionContext;

public class processAddAction extends MgrBaseAction {
	private Employee emp;

	public String execute() {
		ActionContext ctx = ActionContext.getContext();
		String name = (String) ctx.getSession().get(WebConstant.USER);
		mgrManager.addEmp(emp, name);
		this.addActionMessage("新员工添加成功");
		return SUCCESS;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

}
