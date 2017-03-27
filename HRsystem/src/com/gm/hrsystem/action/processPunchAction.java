package com.gm.hrsystem.action;

import java.util.Date;

import com.gm.hrsystem.action.base.EmpBaseAction;
import com.gm.hrsystem.dao.ManagerDao;
import com.gm.hrsystem.domain.*;
import com.opensymphony.xwork2.ActionContext;


public class processPunchAction extends EmpBaseAction {

	public String Come() {
		ActionContext ctx = ActionContext.getContext();
		String name = (String) ctx.getSession().get(WebConstant.USER);

		Date date = new java.sql.Date(System.currentTimeMillis());
		String dutyday = date.toString();
		empManager.punch(name, dutyday, true);
		this.addActionMessage("打卡成功");
		return SUCCESS;
	}

	public String Leave() {
		ActionContext ctx = ActionContext.getContext();
		String name = (String) ctx.getSession().get(WebConstant.USER);

		Date date = new java.sql.Date(System.currentTimeMillis());
		String dutyday = date.toString();
		empManager.punch(name, dutyday, false);
		this.addActionMessage("打卡成功");
		return SUCCESS;
	}
}
