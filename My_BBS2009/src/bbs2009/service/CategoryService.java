package bbs2009.service;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import bbs2009.model.Category;
import bbs2009.util.DB;

public class CategoryService {

	// add 用pstmt
	public void add(Category c) throws SQLException {
		Connection conn = DB.getConn();
		String sql = "insert into category values(null,?,?)";
		PreparedStatement pstmt = DB.getPreStat(conn, sql);

		pstmt.setString(1, c.getName());
		pstmt.setString(2, c.getDescr());
		System.out.println(pstmt.executeUpdate());

		DB.Close(pstmt);
		DB.Close(conn);
	}

	public void delete(Category c) throws SQLException {// 其实这个没用到
		deleteId(c.getId());
	}

	// delete 用pstmt或stmt都可以
	public void deleteId(int Id) throws SQLException {
		Connection conn = DB.getConn();
		String sql = "delete from category where id=" + Id;
		Statement stmt = DB.getStat(conn);

		stmt.executeUpdate(sql);

		DB.Close(stmt);
		DB.Close(conn);
	}

	// update 用pstmt
	public void update(Category c) throws SQLException {

		Connection conn = DB.getConn();
		String sql = "update category set name = ? , descr = ? where id = ?";
		PreparedStatement pstmt = DB.getPreStat(conn, sql);

		pstmt.setString(1, c.getName());
		pstmt.setString(2, c.getDescr());
		pstmt.setInt(3, c.getId());
		pstmt.executeUpdate();

		DB.Close(pstmt);
		DB.Close(conn);

	}

	// list 用 stmt
	public List<Category> list() throws SQLException {
		List<Category> Categories = new ArrayList<Category>();

		Connection conn = DB.getConn();
		String sql = "select * from category";
		Statement stmt = DB.getStat(conn);
		ResultSet rs = DB.getRS(stmt, sql);

		Category c = null;
		while (rs.next()) {
			c = new Category();
			c.setId(rs.getInt("id"));
			c.setName(rs.getString("name"));
			c.setDescr(rs.getString("descr"));
			Categories.add(c);
		}

		DB.Close(rs);
		DB.Close(stmt);
		DB.Close(conn);
		return Categories;
	}

	// 和上面的list（）有点像
	public Category loadById(int id) throws SQLException {
		Category c = null;
		Connection conn = DB.getConn();
		String sql = "select * from category where id =" + id;
		Statement stmt = DB.getStat(conn);
		ResultSet rs = DB.getRS(stmt, sql);

		if (rs.next()) {
			c = new Category();
			c.setId(rs.getInt("id"));
			c.setName(rs.getString("name"));
			c.setDescr(rs.getString("descr"));
		}

		DB.Close(rs);
		DB.Close(stmt);
		DB.Close(conn);

		return c;
	}
}
