package com.gm.hrsystem.action;

import java.util.Date;
import java.util.List;

import com.gm.hrsystem.action.base.EmpBaseAction;
import com.gm.hrsystem.domain.*;
import com.opensymphony.xwork2.ActionContext;


public class ViewUnAttendAction extends EmpBaseAction {
	List<Attend> UnAttends; //���������vo ��һ��Ҫ��domain object ��Ϊ������Ҫ�Ķ���û��ô��
	
	
	public String execute(){
		ActionContext ctx = ActionContext.getContext();
		String name = (String) ctx.getSession().get(WebConstant.USER);
		UnAttends=empManager.unAttend(name);
		return SUCCESS;
	}


	public List<Attend> getUnAttends() {
		return UnAttends;
	}


	public void setUnAttends(List<Attend> unAttends) {
		UnAttends = unAttends;
	}


 
}
