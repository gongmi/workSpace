package com.gm.hrsystem.action;

import java.util.Date;

import com.gm.hrsystem.action.base.EmpBaseAction;
import com.opensymphony.xwork2.ActionContext;


public class PunchAction extends EmpBaseAction {
	private int punchIsValid;

	public String execute() {
		ActionContext ctx = ActionContext.getContext();
		String name = (String) ctx.getSession().get(WebConstant.USER);
		Date date = new java.sql.Date(System.currentTimeMillis());
		String dutyday = date.toString();
		//manager继承了emp 所以manager也是一种emp 在用hql查找所有emp时也会找到mgr 所以也可以用于mgr打卡
		int result = empManager.validPunch(name, dutyday);
		this.setPunchIsValid(result);
		return SUCCESS;
	}

	public int getPunchIsValid() {
		return punchIsValid;
	}

	public void setPunchIsValid(int punchIsValid) {
		this.punchIsValid = punchIsValid;
	}

}