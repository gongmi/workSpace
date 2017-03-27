package com.gm;
//这个程序是我用来做测试用的 因为在jsp中写脚本没有提示 所以我在这里写了然后粘贴过去
import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.Date;
public class tree1 {
private static void tree(List<Article> articles, int id,int grade)
{
	Connection conn = DB.getConn();
	Statement Stat = DB.getStat(conn);
	String sql = "Select * from article where pid=" + id;
	ResultSet RS = DB.getRS(Stat, sql);
 
	try {
		while (RS.next()) {
			Article a = new Article();
			a.initFromRS(RS);
		
			a.setGrade(grade);
			articles.add(a);
			if (!a.isIsleaf()) {
				tree(articles, a.getId(), grade + 1);
				//把数据库里的数据按照树的层次的方式放进list中
				//递归
			}

		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		DB.Close(conn);
		DB.Close(Stat);
		DB.Close(RS);
	}

}
public static void main(String[] args) {
	 
	String title= "title" ;
	String cont= "cont" ;
	int  pid=1;//拿到我回复的伏帖ID
	int  rootid=1;//拿到我回复的伏帖ID
	Connection conn;
	PreparedStatement preStat;
	try {
		conn = DB.getConn();

		String sql = "insert into article values(null,?,?,?,?,now(),?)";  //得到传过来的我要回复的帖子的id 
		 preStat=conn.prepareStatement(sql);
		preStat.setInt(1,pid );
		preStat.setInt(2,rootid );
		preStat.setString(3, title);
		preStat.setString(4, cont);
		preStat.setInt(5, 0);
	
		
	preStat.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

 
}
}
