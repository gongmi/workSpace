package com.gm.hrsystem.action;

import java.util.List;

import javax.annotation.Resource;

import com.gm.hrsystem.action.base.MgrBaseAction;
import com.gm.hrsystem.domain.*;
import com.opensymphony.xwork2.ActionContext;


public class DeptSalaryAction extends MgrBaseAction {
	List<Payment> payments;//���������vo ��һ��Ҫ��domain object ��Ϊ������Ҫ�Ķ���û��ô��
 
	
	public String execute(){
		ActionContext ctx = ActionContext.getContext();
		String name = (String) ctx.getSession().get(WebConstant.USER);
		payments=mgrManager.getSalaryByMgr(name);
		 
		return SUCCESS;
	}


	public List<Payment> getPayments() {
		return payments;
	}


	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}


 

 


	 

 
}
