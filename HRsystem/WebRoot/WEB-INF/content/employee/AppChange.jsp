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
						<td>
							<div class="mytitle">
								��ǰ�û���
								<s:property value="#session.user" />
							</div>
						</td>
					</tr>
					<tr>
						<s:form action="processApp.action">
							<!-- ��������д ����д�� value="attid" �ᱻ�������ַ���  �����input����-->
							<input type="hidden" name="attId" value="${attId}" />
							<s:select name="typeId" label="����ĳɵĳ������" labelposition="left"
								list="types" listKey="id" listValue="name"></s:select>
							<!-- <s:hidden name="attId" value="attId"></s:hidden> -->
							<s:textarea name="reason" label="��������"></s:textarea>
							<s:submit value="�ύ"></s:submit>
						</s:form>
						<s:debug></s:debug>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</body>
</html>