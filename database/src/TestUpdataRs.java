import java.sql.*;
public class TestUpdataRs {
    public static void main(String args[]){
    	Connection conn = null;
	try{
		Class.forName("com.mysql.jdbc.Driver");

		conn = DriverManager
				.getConnection("jdbc:mysql://localhost/gm?user=root&password=&useSSL=false");
		 
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
	    ResultSet rs=stmt.executeQuery("select * from tb_stu order by id");
	    
	    rs.next();
	    //更新一行数据
	    rs.updateString("name","我爱你");
	    rs.updateRow();

	    //插入新行
	    rs.moveToInsertRow();
	    rs.updateInt(1, 9999);
	    rs.updateString("name","AAAA");
	    rs.updateString("sex", "女");
	    
	    rs.insertRow();
	    //将光标移动到新建的行
	    rs.moveToCurrentRow();

	    //删除行
	    rs.absolute(5);
	    rs.deleteRow();

	    //取消更新
	    //rs.cancelRowUpdates();
	}catch (ClassNotFoundException e) {
		e.printStackTrace();
	  }catch(SQLException e){
	    e.printStackTrace();
	  }
    }
}
