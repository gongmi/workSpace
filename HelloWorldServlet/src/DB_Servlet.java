import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DB_Servlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		resp.setContentType("text/html");
		resp.setCharacterEncoding("gb2312");
		PrintWriter out = resp.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager
					.getConnection("jdbc:mysql://localhost/gm?user=root&password=&useSSL=false");
			stmt = conn.prepareStatement("select * from tb_stu");
			rs = stmt.executeQuery();

			while (rs.next()) {  
				String id = rs.getString("id");  
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				// String birthday = rs.getString("birthday");
				if (sex.equals("女")) {
					out.println("<br>"); // 将列值输出
					out.println("编号：" + id); // 将列值输出
					out.println(" 姓名:" + name);
					out.println(" 性别:" + sex);
				}
				// out.println(" 生日：" + birthday);
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
