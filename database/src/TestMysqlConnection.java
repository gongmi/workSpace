import java.sql.*;
import java.util.*;

public class TestMysqlConnection {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/gm?user=root&password=&useSSL=false");
			stmt = conn.prepareStatement("select * from tb_stu");
			rs = stmt.executeQuery();

			while (rs.next()) {  
				String id = rs.getString("id");  
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				String birthday = rs.getString("birthday");
				System.out.print("编号：" + id);  
				System.out.print(" 姓名:" + name);
				System.out.print(" 性别:" + sex);
				System.out.println(" 生日：" + birthday);
			}

			stmt.close();
			rs.close();
			
			System.out.println("修改后的表：");
			// String n=args[0];
			// String s=args[1];
			// String b=args[2];

			stmt = conn.prepareStatement("insert into tb_stu(name,sex,birthday) values(?,?,?) ");

			stmt.setString(1, Integer.toString(new Random().nextInt(315)));
			stmt.setString(2, "男");
			stmt.setString(3, "1993-02-17");
			stmt.executeUpdate();

			stmt.setString(1, Integer.toString(new Random().nextInt(715)));
			stmt.setString(2, "男");
			stmt.setString(3, "1993-02-17");
			stmt.executeUpdate();

			stmt = conn.prepareStatement("select * from tb_stu");
			rs = stmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				java.sql.Date birthday = rs.getDate("birthday");
				System.out.print("编号：" + id); // 将列值输出
				System.out.print(" 姓名:" + name);
				System.out.print(" 性别:" + sex);
				System.out.println(" 生日：" + birthday);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
