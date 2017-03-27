<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
//如果是从delete.jsp过来的  action=null 那么就直接进入表单 
//如果是从表单过来的  action=login 那么就表单验证进入下面的if
if(request.getParameter("action")!= null && request.getParameter("action").equals("login"))
{
request.setCharacterEncoding("GBK");
String name=request.getParameter("name");
String password=request.getParameter("password");
 if ( password.equals("295783") && name.equals("gongmi"))
 
 {session.setAttribute("Login", "true");
response.sendRedirect("article.jsp");
}
 else
 {//如果用户名密码不对 那么就再填一次
	 response.sendRedirect("login.jsp"); 
 }
}
%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
</head>

<body>



<!--这个表单里面提交了一个action 如果这个页面检测到的是这个表单提交过去的 那么就表单验证！-->
<!--如果是从delete。jsp过来的 那么就直接进入这个表单！-->
 <form action="login.jsp" method="post">
 用户名：<input  type="text" name="name"    /><br />
    密码：<input  type="password" name="password"  /><br />
    <input type="hidden" name="action" value="login"/>
    
 <input name="" type="submit" />
 </form>



</body>
</html>