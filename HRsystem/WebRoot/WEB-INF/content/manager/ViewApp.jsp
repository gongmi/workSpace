
<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�鿴�Լ��ķ���������</title>
</head>
<body>
	<%@include file="mgrheader.jsp"%>
	<table width="960" align="center"
		background="${pageContext.request.contextPath}/images/bodybg.jpg">
		<tr>
			<td>
				<table width="80%" border="0" align="center" bgcolor="#cccccc">
					<tr bgcolor="#e1e1e1">
						<td colspan="5"><div class="mytitle">
								��ǰ����
								<s:property value="#session.user" />
							</div></td>
					</tr>
					<tr>
						<td><s:if test="actionMessages.size()>0">
								<div class="error">
									<s:actionmessage />
								</div>
							</s:if></td>
					</tr>
					<tr bgcolor="#e1e1e1">
						<td colspan="5">����Ա�������޸�����</td>
					</tr>
					<tr class="pt11" height="45">
						<td width="15%"><b>Ա��</b></td>
						<td width="20%"><b>ԭ����������</b></td>
						<td width="20%"><b>�������</b></td>
						<td width="35%"><b>��������</b></td>
						<td width="10%">&nbsp;</td>
					</tr>
					 
					<s:iterator value="apps" status="index">
						<s:if test="!result">
							<s:if test="#index.odd == true">
								<tr style="background-color:#dddddd" class="pt11" height="32">
							</s:if>
							<s:else>
								<tr class="pt11" height="32">
							</s:else>
							<td><s:property value="attend.employee.name" /></td>
							<td><s:property value="attend.type.name" /></td>
							<td><s:property value="type.name" /></td>
							<td><s:property value="reason" /></td>


							<td><s:if test="!result">
									<a
										href='CheckBack.action?appid=<s:property value="id"/>&result=true'>ͬ��</a>
									<a
										href='CheckBack.action?appid=<s:property value="id"/>&result=false'>����</a>
								</s:if></td>
						</s:if>
					</s:iterator>
				</table>
			</td>
		</tr>
		<s:debug></s:debug>
	</table>
</body>
</html>