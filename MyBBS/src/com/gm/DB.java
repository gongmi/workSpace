package com.gm;

import java.sql.*;
 

public class DB {
public static Connection getConn(){
	Connection c=null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbs","root","");
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
public static void Close(Connection c){
	try { 
		c.close();
		c=null;
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
public static void Close(Statement s){
	try { 
		s.close();
		s=null;
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
public static void Close(ResultSet RS){
	try { 
		RS.close();
		RS=null;
	} catch (SQLException e) {
		e.printStackTrace();
	}
} 
}
