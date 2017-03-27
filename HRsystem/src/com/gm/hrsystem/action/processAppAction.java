package com.gm.hrsystem.action;


import com.gm.hrsystem.action.base.EmpBaseAction;


public class processAppAction extends EmpBaseAction {
	 private int typeId;
	 private int attId;
	 private String reason;
	 public String execute(){
		 empManager.addApplication(attId, typeId, reason);
		 this.addActionMessage("申请成功！等待经理审阅");
		 return SUCCESS;
	 }
	 
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getAttId() {
		return attId;
	}
	public void setAttId(int attId) {
		this.attId = attId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

 
}
