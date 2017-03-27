package com.gm.hrsystem.action;

import com.gm.hrsystem.action.base.EmpBaseAction;
import com.gm.hrsystem.domain.*;
import com.opensymphony.xwork2.ActionContext;

import static com.gm.hrsystem.service.EmpManager.*; 
//��̬���� ��������������д�static��field�ͷ���������

public class LoginAction extends EmpBaseAction {
	private static final String EMP = "emp";
	private static final String MGR = "mgr";
	private Manager manager;

	public String execute() {
		ActionContext ctx=ActionContext.getContext();
		
		if (empManager.validLogin(manager) == LOGIN_MGR) {
			this.addActionMessage("������� ���Ѿ���½�ɹ�");
			ctx.getSession().put(WebConstant.USER, manager.getName());
			ctx.getSession().put(WebConstant.LEVEL,WebConstant.MGR  );
			return MGR;
		} else if (empManager.validLogin(manager) == LOGIN_EMP) {
			this.addActionMessage("��ͨԱ����� ���Ѿ���½�ɹ�");
			ctx.getSession().put(WebConstant.USER, manager.getName());
			ctx.getSession().put(WebConstant.LEVEL,WebConstant.EMP  );
			return EMP;
		} else {
			this.addActionMessage("�û���/�����������");
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