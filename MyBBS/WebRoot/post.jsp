<%@ page pageEncoding="GB18030"%>
<%@page import="java.sql.*,java.io.*,com.gm.*,java.util.*"%>
<%request.setCharacterEncoding("GBK");
//֮ǰ�Ұ������������if���� ��Ϊ�Ҿ��� ����������������ֻ��Ҫ��� ����Ҫjava������ ��Ϊreply����Ҳû�м����Ȼ�󲻻����� ����
	if (request.getParameter("gm") != null
			&& request.getParameter("gm").equals("yqx")) {
		
		String title = request.getParameter("title");
		String cont = request.getParameter("cont");

		Connection conn = DB.getConn();

		String sql = "insert into article values(null,?,?,?,?,now(),?)"; //�õ�����������Ҫ�ظ������ӵ�id 

		PreparedStatement preStat = DB.getPreStat(conn, sql);

		preStat.setInt(1, 0);
		preStat.setInt(2, -1);//rootid���Լ���id ���ǵò����Լ���id ��ʱ�����һ��
		preStat.setString(3, title);
		preStat.setString(4, cont);
		preStat.setInt(5, 0);
	
		preStat.executeUpdate();
	//��������������������������������Ϊ�˵õ��Լ���id��������������������������������������������������������
		sql="select max(id)  from article";
		Statement Stat = DB.getStat(conn);
		ResultSet RS = DB.getRS(Stat, sql);
        RS.next();
        int rootId=RS.getInt(1);

   
		Stat.executeUpdate("update article set rootid = " + rootId + " where id = "	+ rootId);
	 //��������������������������������Ϊ�˵õ��Լ���id��������������������������������������������������������	
		response.sendRedirect("article.jsp");

		DB.Close(conn);
		DB.Close(preStat);
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title><%--=a.getTitle() --%></title>
<meta http-equiv="content-type" content="text/html; charset=GBK">
<link rel="stylesheet" type="text/css" href="images/style.css"
	title="Integrated Styles">
<script language="JavaScript" type="text/javascript"
	src="images/global.js"></script>
<link rel="alternate" type="application/rss+xml" title="RSS"
	href="http://bbs.chinajavaworld.com/rss/rssmessages.jspa?threadID=744236">
</head>
<body>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td width="140"><a
					href="http://bbs.chinajavaworld.com/index.jspa"><img
						src="images/header-left.gif"
						alt="Java|Java����_������̳|ChinaJavaWorld������̳" border="0"></a></td>
				<td><img src="images/header-stretch.gif" alt="" border="0"
					height="57" width="100%"></td>
				<td width="1%"><img src="images/header-right.gif" alt=""
					border="0"></td>
			</tr>
		</tbody>
	</table>
	<br>
	<div id="jive-flatpage">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr valign="top">
					<td width="99%">
						<p class="jive-page-title">�����µ�����</p>
					</td>
					<td width="1%"><div class="jive-accountbox"></div></td>
				</tr>
			</tbody>
		</table>

		<br>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr valign="top">
					<td width="99%"><div id="jive-message-holder">
							<div class="jive-message-list">
								<div class="jive-table">
									<div class="jive-messagebox">



										<form action="post.jsp" method="post">
											���⣺ <input type="textarea" name="title" /></br>
											 <input type="hidden" name="gm" value="yqx" />
											  ����:</br>
											<textarea cols="60" rows="10" name="cont"></textarea>
											</br> <input type="submit" value="����"></br>

										</form>


									</div>
								</div>
							</div>
							<div class="jive-message-list-footer">
								<table border="0" cellpadding="0" cellspacing="0" width="100%">
									<tbody>
										<tr>
											<td nowrap="nowrap" width="1%"></td>
											<td align="center" width="98%"><table border="0"
													cellpadding="0" cellspacing="0">
													<tbody>
														<tr>
															<td><a href=" "><img
																	src="images/arrow-left-16x16.gif" alt="���ص������б�"
																	border="0" height="16" hspace="6" width="16"></a></td>
															<td><a href=" ">���ص������б�</a></td>
														</tr>
													</tbody>
												</table></td>
											<td nowrap="nowrap" width="1%">&nbsp;</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div></td>
					<td width="1%"></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
