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
	    //����һ������
	    rs.updateString("name","�Ұ���");
	    rs.updateRow();

	    //��������
	    rs.moveToInsertRow();
	    rs.updateInt(1, 9999);
	    rs.updateString("name","AAAA");
	    rs.updateString("sex", "Ů");
	    
	    rs.insertRow();
	    //������ƶ����½�����
	    rs.moveToCurrentRow();

	    //ɾ����
	    rs.absolute(5);
	    rs.deleteRow();

	    //ȡ������
	    //rs.cancelRowUpdates();
	}catch (ClassNotFoundException e) {
		e.printStackTrace();
	  }catch(SQLException e){
	    e.printStackTrace();
	  }
    }
}
