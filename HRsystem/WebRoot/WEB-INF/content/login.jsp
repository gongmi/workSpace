<%@ page language="java" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>登陆页面</title>
</head>

<body>

	<s:actionmessage />
	<s:actionerror />
	<s:fielderror />

	<div align="center">
		<s:form action="processLogin" validate="true">
 欢迎进入龚宓第一个SSH：HRSystem<br />
			<br />
			<br />
			<br />
 
 请输入用户名和密码来登录<br />
			<s:textfield name="manager.name" key="loginForm.username.displayname" />
			<s:textfield name="manager.pass" label="密码" />
			<tr>
				<td />
				<s:submit key="button.submit" theme="simple" />
				<td />
				<s:reset key="button.reset" theme="simple" />
			</tr>
		</s:form>
	</div>

	<s:debug></s:debug>
</body>
</html>
