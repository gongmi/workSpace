<%@ page language="java"
	import="java.sql.*,java.io.*,com.gm.*,java.util.*"
	pageEncoding="GB18030"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
Object login=session.getAttribute("Login");//先看这个会话有没有管理员的身份登录 没有的话
if(login==null|| !login.equals("true"))
	 response.sendRedirect("login.jsp"); 
else{
	request.setCharacterEncoding("GBK");
	int id = Integer.parseInt(request.getParameter("ID"));//拿到我要删除的帖ID
	int Pid = Integer.parseInt(request.getParameter("PID"));
	boolean leaf = Boolean.parseBoolean(request.getParameter("Leaf"));//拿到我要删除的帖是不是叶子

	Tree.delete(id, leaf);
	Tree.update(Pid);
}
	
%>



<!-- Place this in the 'body' section -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>回复成功</title>

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
	删除成功
	<br>
	<div id="time">5</div>
	秒钟后回到主题列表
	<br>

	<script type="text/javascript">
		delayURL("article.jsp");

		function delayURL(url) {
			var delay = document.getElementById("time").innerHTML;
			if (delay > 0) {
				delay--;
				document.getElementById("time").innerHTML = delay;
			} else
				window.top.location.href = url;
			setTimeout("delayURL('" + url + "')", 1000);
		}
	</script>


</body>
</html>
