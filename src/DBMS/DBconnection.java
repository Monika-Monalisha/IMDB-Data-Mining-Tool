package DBMS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

	Connection con;

	Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String host = "localhost";
			String port = "49161";
			String dbName = "XE";					//dbName needs to be corrected
			String OracleURL = "jdbc:oracle:thin:@" + host + ":" + port + ":" + dbName;
			con = DriverManager.getConnection(OracleURL, "Scott", "Tiger");

		} catch (Exception e) {
			System.out.println("Exception in DB connection ");
			e.printStackTrace();
		}
		return con;
	}

	void closeConnection() {
		try {
			con.close();
		} catch (SQLException ex) {
			System.err.println("Can't close connection"+ ex.getMessage());
			ex.printStackTrace(System.out);
		}
	}

}
