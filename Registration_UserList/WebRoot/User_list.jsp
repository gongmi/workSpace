<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Category_input.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	User
	<s:debug></s:debug>
	<br />
 
	
	<table border="1" width="300">
		<s:iterator value="Users" var="u" status="st">
			<tr
				<s:if test="#st.odd">
    style="background-color:#bbbbbb"</s:if>>
<!--      这里必须加#  -->
				<td><s:property value="#u.id" />
				<td><s:property value="#u.username" />
				<td><s:property value="#u.password" />
				<td><a href="admin/Category_delete?Id=<s:property value="#c.id"/>">delete </a>
		    	<td><a href="admin/Category_updateinput?Id=<s:property value="#c.id"/>">update </a>
			</tr>
		</s:iterator>
	</table>
	
	<br />
	<a href="admin/Category_addinput">add </a>
 
 
 
</body>
</html>
