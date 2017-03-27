package com.gm.hrsystem.action;

import com.gm.hrsystem.action.base.MgrBaseAction;
import com.opensymphony.xwork2.ActionContext;

public class CheckBackAction extends MgrBaseAction {
	private int appid;
	private boolean result;

	public String execute() {
		ActionContext ctx = ActionContext.getContext();
		String name = (String) ctx.getSession().get(WebConstant.USER);
		mgrManager.check(appid, name, result);
		//�ⲻ����ʾ��viewapp�� ��Ϊchain �� viewapp �Ѿ�����һ��action�� 
//		if (result==true)
//		this.addActionMessage("��ͨ��");
//		else
//		this.addActionMessage("�Ѳ���");
		return SUCCESS;
	}

	public int getAppid() {
		return appid;
	}

	public void setAppid(int appid) {
		this.appid = appid;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}
