package com.gm.hrsystem.action;
//接口里的变量默认是 public final static 的 静态常量
public interface WebConstant
{    //放在session的属性值必须是可序列化的
	
	// HttpSession里代表经理的level值
	String MGR = "mgr";
	// HttpSession里代表员工的level值
	String EMP = "emp";
	// HttpSession里代表用户级别的属性名
	String LEVEL = "level";
	// HttpSession里代表用户名的属性名
	String USER = "user";
}
