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
		//这不能显示在viewapp上 因为chain 到 viewapp 已经是另一个action了 
//		if (result==true)
//		this.addActionMessage("已通过");
//		else
//		this.addActionMessage("已驳回");
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
