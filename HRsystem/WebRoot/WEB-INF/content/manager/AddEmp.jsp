<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>添加员工</title>

</head>

<body>
	请输入新员工资料
	<br />
	<%@include file="mgrheader.jsp"%>
	<s:actionmessage />
	<s:actionerror />
	<s:fielderror />
	<div align="center">
		<s:form action="processAdd">
			<s:textfield name="emp.name" key="addForm.empName.displayname" />
			<s:textfield name="emp.pass" key="addForm.empPass.displayname" />
			<s:textfield name="emp.salary" key="addForm.empSal.displayname" />
			<tr>
				<td colspan="2"><s:submit value="添加新员工" theme="simple" /> <s:reset
						theme="simple" value="重填" /></td>
			</tr>
			<s:token></s:token>
		</s:form>
	</div>
	<s:debug></s:debug>
</body>
</html>
