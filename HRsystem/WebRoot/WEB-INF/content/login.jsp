<%@ page language="java" pageEncoding="GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>��½ҳ��</title>
</head>

<body>

	<s:actionmessage />
	<s:actionerror />
	<s:fielderror />

	<div align="center">
		<s:form action="processLogin" validate="true">
 ��ӭ���빨嵵�һ��SSH��HRSystem<br />
			<br />
			<br />
			<br />
 
 �������û�������������¼<br />
			<s:textfield name="manager.name" key="loginForm.username.displayname" />
			<s:textfield name="manager.pass" label="����" />
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
