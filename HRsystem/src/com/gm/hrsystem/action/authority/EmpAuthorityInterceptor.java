package com.gm.hrsystem.action.authority;

import com.opensymphony.xwork2.*;

import com.opensymphony.xwork2.interceptor.*;

import com.gm.hrsystem.action.WebConstant;

public class EmpAuthorityInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = ActionContext.getContext();
		String level = (String) ctx.getSession().get(WebConstant.LEVEL);
		String res;
		System.out.println(this.getClass().getName()+"ִ��invocation.invoke()֮ǰ");
		// ���level��Ϊnull����levelΪemp��mgr
		if (level != null && (level.equals(WebConstant.EMP) || level.equals(WebConstant.MGR))) {
			res= invocation.invoke();
		}
		res= Action.LOGIN;
		System.out.println(this.getClass().getName()+"ִ��invocation.invoke()֮��");
		return res;
	}
}