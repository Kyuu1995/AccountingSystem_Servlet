import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {
	// -- Connection Parameter --
	static private String url = "jdbc:mysql://localhost:3306/accountingsystem";
	static private String username = "root";
	static private String password = "root";
	static Connection conn;

	// -- Connection Setting --
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// System.out.println("Driver is OK!!");
		} catch (ClassNotFoundException e) {
			System.err.println("Driver is Error....");
			// e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, username, password);
			// System.out.println("Connection is OK!!");
		} catch (SQLException e) {
			System.err.println("Connection is Error....");
			// e.printStackTrace();
		}
		return conn;
	}
}
