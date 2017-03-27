package com.gm.hrsystem.action;

import java.util.List;

import com.gm.hrsystem.action.base.EmpBaseAction;
import com.gm.hrsystem.domain.*;

public class AppChangeAction extends EmpBaseAction {
	private int attId;
	private List<AttendType> types;

	public String execute() {
		types = empManager.getAllType();
		return SUCCESS;
	}

	public List<AttendType> getTypes() {
		return types;
	}

	public void setTypes(List<AttendType> types) {
		this.types = types;
	}

	public int getAttId() {
		return attId;
	}

	public void setAttId(int attId) {
		this.attId = attId;
	}

}
