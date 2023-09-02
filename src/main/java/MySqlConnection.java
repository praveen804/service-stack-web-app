

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
	public static Connection initializedatabse() throws SQLException,ClassNotFoundException{
		String dbDriver="com.mysql.jdbc.Driver";
		String dburl="jdbc:mysql://localhost:3306/";
		String dbname="yukthi";
		String dbuser="root";
		String dbpass="";
		Class.forName(dbDriver);
		Connection con=DriverManager.getConnection(dburl+dbname,dbuser,dbpass);
		return con;
		}
}
