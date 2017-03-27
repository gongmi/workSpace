package com.gm.hrsystem.action;

import java.util.List;

import com.gm.hrsystem.action.base.MgrBaseAction;
import com.gm.hrsystem.domain.*;
import com.opensymphony.xwork2.ActionContext;


public class AddAppAction extends MgrBaseAction {
	List<Application> apps;
	//	List<AppBean> apps;
	//这里可以用vo/DTO/JavaBean AppBean 
	//service 将应用底层的状态信息Application封装成javabean AppBean
  
 
	public String execute(){
		ActionContext ctx = ActionContext.getContext();
		String name = (String) ctx.getSession().get(WebConstant.USER);
		apps=mgrManager.getAppsByMgr(name);
		return SUCCESS;
	}


 


	 

 
}
