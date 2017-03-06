package com.revature.ERS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Setting connection between Eclipse and SQL Developer
 * 
 * @author kevgu
 *
 */
public class ConnectionFactory {
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String user = "kevinguan";				//Oracle SQL Developer User-name
	private static final String password = "code07Work"; 	//Oracle SQL Developer Password

	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;	//If null is returned, connection failed, make sure to check return is not null.
	}
}
