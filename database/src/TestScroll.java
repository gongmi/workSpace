import java.sql.*;

public class TestScroll {
	public static void main(String args[]) throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
//		自动关闭资源的try 但是只是自动关闭而已 还是要处理exception
		try (
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gm?user=root&password=&useSSL=false");
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("select * from emp order by empno");) {
			
			rs.next();
			System.out.println(rs.getInt(1));
			rs.last();
			System.out.println(rs.getString(1));
			System.out.println(rs.isLast());
			System.out.println(rs.isAfterLast());
			System.out.println(rs.getRow());
			rs.previous();
			System.out.println(rs.getString(2));
			rs.absolute(6);
			System.out.println(rs.getString(2));

		} catch (SQLException e) {//还是要处理exception
			e.printStackTrace();
		}
	}
}
