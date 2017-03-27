 
<%@ page import="java.sql.*, com.bjsxt.shopping.util.DB, java.util.*" %>
 
<%
response.setContentType("text/xml");
response.setHeader("Cache-Control", "no-store"); //HTTP1.1
response.setHeader("Pragma", "no-cache"); //HTTP1.0
response.setDateHeader("Expires", 0); //prevents catching at proxy server
System.out.println(request.getParameter("id"));
String s=(String)request.getParameter("id");
//check the database
Connection conn = DB.getConn();
	 
		Statement stmtCount = DB.getStatement(conn);
		ResultSet rs = null;
		ResultSet rsCount = null;
		int totalRecords = 0;
		try {
			String ss="select count(*) from user where username='"+s+"'";
			rsCount = DB.getResultSet(stmtCount,ss);
			rsCount.next();
			totalRecords = rsCount.getInt(1);
			System.out.println(totalRecords);
			if (totalRecords==0)
response.getWriter().write("<msg>valid</msg>");
			else
response.getWriter().write("<msg>invalid</msg>");
			}catch (SQLException e) {
				e.printStackTrace();
			} finally {
			 
				DB.close(stmtCount);
				DB.close(conn);
			}
%>
