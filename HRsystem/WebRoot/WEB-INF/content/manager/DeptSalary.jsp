
<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>查看旗下员工</title>
</head>
<body>
	<%@include file="mgrheader.jsp"%>
	<table width="960" align="center"
		background="${pageContext.request.contextPath}/images/bodybg.jpg">
		<tr>
			<td>
				<table width="80%" border="0" align="center" bgcolor="#cccccc">
					<tr bgcolor="#e1e1e1">
						<td colspan="4"><div class="mytitle">
								经理：
								<s:property value="#session.user" />
							</div></td>
					</tr>

					<tr bgcolor="#e1e1e1">
						<td colspan="4"><div class="mytitle">
								部门：
								<s:property value="payments[0].employee.manager.dept" />
							</div></td>
					</tr>

					<tr class="pt11" height="45">
						<td width="25%"><b>名称</b></td>
						<td width="25%"><b>月份</b></td>
						<td width="25%"><b>月薪</b></td>
						<td width="25%"><b>工资</b></td>

					</tr>
					<s:iterator value="payments" status="index">
						<s:if test="#index.odd == true">
							<tr style="background-color:#dddddd" class="pt11" height="32">
						</s:if>
						<s:else>
							<tr class="pt11" height="32">
						</s:else>
						<td><s:property value="employee.name" /></td>
						<td><s:property value="payMonth" /></td>
						<td><s:property value="employee.salary" /></td>
						<td><s:property value="amount" /></td>

					</s:iterator>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>