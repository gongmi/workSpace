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
		//manager�̳���emp ����managerҲ��һ��emp ����hql��������empʱҲ���ҵ�mgr ����Ҳ��������mgr��
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