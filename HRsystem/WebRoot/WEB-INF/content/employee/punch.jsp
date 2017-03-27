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
<base href="<%=basePath%>">

<title>普通员工打卡</title>

</head>

<body>
	<s:debug></s:debug>

	<s:if test="punchIsValid==1 || punchIsValid==3 ">
		<div align="center">
			<s:form action="employeeCome.action">
				<s:submit value="上班打卡"></s:submit>
			</s:form>
		</div>
	</s:if>

	<s:if test="punchIsValid==2 || punchIsValid==3 ">
		<div align="center">
			<s:form action="employeeLeave.action">
				<s:submit value="下班打卡"></s:submit>
			</s:form>
		</div>
	</s:if>

	<s:if test="punchIsValid==0">
		<div align="center">不能打卡！</div>
	</s:if>
</body>
</html>
