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
	request.setCharacterEncoding("GBK");
	String title = request.getParameter("title");
	String cont = request.getParameter("cont");
	int pid = Integer.parseInt(request.getParameter("ID"));//�õ��һظ��ķ���ID
	int rootid = Integer.parseInt(request.getParameter("RootID"));//�õ��һظ��� �����ĸ�ID

	Connection conn = DB.getConn();

	String sql = "insert into article values(null,?,?,?,?,now(),?)"; //�õ�����������Ҫ�ظ������ӵ�id 
    PreparedStatement preStat = DB.getPreStat(conn, sql);

	preStat.setInt(1, pid);
	preStat.setInt(2, rootid);
	preStat.setString(3, title);
	preStat.setString(4, cont);
	preStat.setInt(5, 0);

	preStat.executeUpdate();

	sql = "update article set isleaf=1 where id=" + pid;
	preStat = conn.prepareStatement(sql);
	preStat.executeUpdate();

	DB.Close(conn);
	DB.Close(preStat);
%>



<!-- Place this in the 'body' section -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>�ظ��ɹ�</title>

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
	�ظ��ɹ�
	<br>
	<div id="time">5</div>
	���Ӻ�ص������б�
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
