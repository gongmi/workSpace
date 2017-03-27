package com.gm.hrsystem.action;

import javax.servlet.http.*;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

//p197 获得真是的servlet api 实例
public class LogoutAction extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest request;

	public String execute() {
		HttpSession session = request.getSession();
		session.invalidate();
		this.addActionMessage("您已经成功退出");
		return SUCCESS;

	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;

	}
}