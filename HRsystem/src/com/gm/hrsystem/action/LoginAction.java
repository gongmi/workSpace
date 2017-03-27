package com.gm.hrsystem.action;

import com.gm.hrsystem.action.base.EmpBaseAction;
import com.gm.hrsystem.domain.*;
import com.opensymphony.xwork2.ActionContext;

import static com.gm.hrsystem.service.EmpManager.*; 
//静态导入 把这个类里面所有带static的field和方法都导入

public class LoginAction extends EmpBaseAction {
	private static final String EMP = "emp";
	private static final String MGR = "mgr";
	private Manager manager;

	public String execute() {
		ActionContext ctx=ActionContext.getContext();
		
		if (empManager.validLogin(manager) == LOGIN_MGR) {
			this.addActionMessage("经理你好 你已经登陆成功");
			ctx.getSession().put(WebConstant.USER, manager.getName());
			ctx.getSession().put(WebConstant.LEVEL,WebConstant.MGR  );
			return MGR;
		} else if (empManager.validLogin(manager) == LOGIN_EMP) {
			this.addActionMessage("普通员工你好 你已经登陆成功");
			ctx.getSession().put(WebConstant.USER, manager.getName());
			ctx.getSession().put(WebConstant.LEVEL,WebConstant.EMP  );
			return EMP;
		} else {
			this.addActionMessage("用户名/密码输入错误");
			return ERROR;
		}
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
}