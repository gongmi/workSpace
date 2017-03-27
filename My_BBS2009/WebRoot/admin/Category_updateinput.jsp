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
	<s:debug></s:debug>
	<!--   <form action="admin/Category_update" method="post"> -->
	<!--   <input type="hidden" name="category.id" value="<s:property value="category.id"/>" /> -->
	<!--   	name:<input name="category.name" value="<s:property value="category.name"/>" /> -->
	<!--   	desc:<textarea name="category.descr"> <s:property value="category.descr"/> </textarea> -->
	<!--   	<input type="submit" value="update" />  -->
	<!--   </form> -->
	
<!-- 用表单标签更方便哦 ！！！ 比上面他讲的方便多了 -->
	<s:form action="admin/Category_update">

		<s:hidden name="category.id" />
		<s:textfield name="category.name" label="名称" />
		<s:textarea name="category.descr" label="描述" />
		<s:submit value="更新" />

	</s:form>

</body>
</html>
