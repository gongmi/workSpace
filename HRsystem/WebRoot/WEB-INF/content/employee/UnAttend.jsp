
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
	<%@include file="empheader.jsp"%>
	<table width="960" align="center"
		background="${pageContext.request.contextPath}/images/bodybg.jpg">
		<tr>
			<td>
				<table width="80%" border="0" align="center" bgcolor="#cccccc">
					<tr bgcolor="#e1e1e1">
						<td align="center" colspan="5">��ǰ�û���<s:property value="#session.user" /></td>
					</tr>
					<tr bgcolor="#e1e1e1">
						<td align="center" colspan="5">��ֻ�ܲ鿴�������ķ������򿨣��������飬��������������</td>
					</tr>
					<tr class="pt11" height="45">
						<td width="25%" align="center"><b>������</b></td>
						<td width="20%" align="center"><b>�춯����</b></td>
						<td width="10%" align="center"><b>�ϰ��°�</b></td>
						<td width="25%" align="center"><b>��ʱ��</b></td>
						<td width="20%" align="center">&nbsp;</td>
					</tr>
					<s:iterator value="UnAttends" status="index">
						<s:if test="#index.odd == true">
							<tr style="background-color:#dddddd" class="pt11" height="32">
						</s:if>
						<s:else>
							<tr class="pt11" height="32">
						</s:else>
						<td align="center"><s:property value="dutyDay" /></td>
						<td align="center"><s:property value="type.name" /></td>
						<td align="center"><s:if test="isCome">�ϰ�</s:if> <s:else>�°�</s:else></td>
						<td align="center"><s:property value="punchTime" /></td>

						<td align="center"><s:if test="type.id!=1">
								<a href='AppChange.action?attId=<s:property value="id"/>'>����ı�</a>
							</s:if></td>
					</s:iterator>
				</table>
			</td>
		</tr>
	</table>
	<s:debug></s:debug>
</body>
</html>