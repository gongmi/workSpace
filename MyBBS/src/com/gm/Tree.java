package com.gm;
import java.util.List;
import java.sql.*;
public class Tree {
	//这个类里面有 个函数 这些函数连接数据库 对数据库里的article和做一些操作 拿出来或者修改 或者删除等
	public static  void tree(List<Article> articles, int id, int grade) {
		//这是article.jsp所需要的递归树的list
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
	
	
	//这是article_flat.jsp所需要的 list生成函数
	public static  void flat(List<Article> articles) {
	Connection conn = DB.getConn();
	Statement Stat = DB.getStat(conn);
	String sql = "Select * from article where rootid=id";
	ResultSet RS = DB.getRS(Stat, sql);

	try {
while (RS.next()) {
	Article a = new Article();
	a.initFromRS(RS);
	articles.add(a);
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
	//这是flat_detail.jsp所需要的 list生成函数
	public static  Article flat_detail(List<Article> articles,int id) {
		Connection conn = DB.getConn();
		Statement Stat = DB.getStat(conn);
		String sql = "Select * from article where rootid=" + id;
		ResultSet RS = DB.getRS(Stat, sql);

		Article A = new Article();
		try {
			while (RS.next()){

			Article a = new Article();

			a.initFromRS(RS);
				articles.add(a);
				if(a.getId()==a.getRootid())
					A=a;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.Close(conn);
			DB.Close(Stat);
			DB.Close(RS);
			return A;
	}
 public static  void delete(int id, boolean leaf) {

		PreparedStatement PreStat = null;
		Connection conn = DB.getConn();
		try {
			if (!leaf) {
				String sql = "Select * from article where pid=" + id;
		 
				PreStat=DB.getPreStat(conn,sql);
				ResultSet RS = DB.getRS(PreStat, sql);

				while (RS.next()) {
					delete(RS.getInt("id"), RS.getInt("isleaf")==0);

				}

			}

				String sql = "delete from article where id=" + id;
				PreStat=conn.prepareStatement(sql);
				PreStat.executeUpdate();
		 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.Close(conn);
		 DB.Close(PreStat);
		}
	} 
 public static  void update(int  Pid) {
	Connection conn = DB.getConn();
	
	try {
		String sql="Select count(*) from article where pid="+Pid;
		PreparedStatement PreStat=conn.prepareStatement(sql);
		ResultSet RS=DB.getRS(PreStat, sql);
		RS.next();
		sql=("Update article set isleaf=0 where id="+Pid);
		if(RS.getInt(1)==0){
			PreStat=conn.prepareStatement(sql);;
			PreStat.executeUpdate();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
}
