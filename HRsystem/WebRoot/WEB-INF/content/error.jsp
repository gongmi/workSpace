<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>出错页面</title>
</head>
<body>
	出错了
	<br />
	<s:if test="actionMessages.size()>0">
		<div class="error">
			<s:actionmessage />
		</div>
	</s:if>
	<s:actionerror cssClass="error" />
	<!--  <s:property value="exception.message"/> -->
	${exception}
	<br />
	<s:debug></s:debug>
</body>
</html>
