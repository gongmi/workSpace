<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>员工首页</title>
</head>

<body>

	<!-- 静态包含 -->
	<%@include file="empheader.jsp"%>

	<table width="960" align="center">
		<tr height="60">
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><s:if test="actionMessages.size()>0">
					<div class="error">
						<s:actionmessage />
					</div>
				</s:if></td>
		</tr>

		<tr height="80">
			<td>&nbsp;</td>
		</tr>
		<tr>

			<td><s:property value="#session.user" /> ，欢迎您使用GongMi
				HRsystem系统，您是普通员工</td>

		</tr>
		<tr height="60">
			<td>&nbsp;</td>
		</tr>
	</table>
	${sessionScope.user}
	<!-- JSP2的表达式语言-->
	<s:debug />
</body>
</html>
