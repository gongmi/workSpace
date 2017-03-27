<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>查看自己的非正常出勤</title>
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
								当前用户：
								<s:property value="#session.user" />
							</div>
						</td>
					</tr>
					<tr>
						<s:form action="processApp.action">
							<!-- 必须这样写 不能写成 value="attid" 会被当做是字符串  会出现input错误-->
							<input type="hidden" name="attId" value="${attId}" />
							<s:select name="typeId" label="申请改成的出勤类别" labelposition="left"
								list="types" listKey="id" listValue="name"></s:select>
							<!-- <s:hidden name="attId" value="attId"></s:hidden> -->
							<s:textarea name="reason" label="申请理由"></s:textarea>
							<s:submit value="提交"></s:submit>
						</s:form>
						<s:debug></s:debug>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</body>
</html>