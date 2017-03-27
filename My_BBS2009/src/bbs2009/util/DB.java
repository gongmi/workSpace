package bbs2009.util;

import java.sql.*;
 


public class DB {
public static Connection getConn(){
	Connection c=null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbs2009","root","");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
return c;

}

public static Statement getStat(Connection c){
	Statement s=null;
	try {
		s=c.createStatement();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return s;
}
public static PreparedStatement getPreStat(Connection c,String sql){
	PreparedStatement s=null;
	try {
		s=c.prepareStatement(sql);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return s;
}

public static ResultSet getRS(Statement s,String sql){
	ResultSet rs=null;
	try {
		rs = s.executeQuery(sql);//Statement的查询语句方法
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return rs;
}
 


 
public static void Close(Connection conn) {
	try {
		if(conn != null) {
			conn.close();
			conn = null;//这样通知垃圾回收器赶紧回收
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
//因为preparedstatement是statement的子接口 所以这个close preparedstatement也可以用
public static void Close(Statement stmt) {
	try {
		if(stmt != null) {
			stmt.close();
			stmt = null;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public static void Close(ResultSet rs) {
	try {
		if(rs != null) {
			rs.close();
			rs = null;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}
