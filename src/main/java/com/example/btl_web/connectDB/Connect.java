package com.example.btl_web.connectDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	private static String dbUser="root";
	private static String dbPass="240211";
	private static String dbPrefix="jdbc:mysql://localhost";
	private static String dbPort="3306";
	private static String dbName="btl_web";
	public static Connection getConnection() {
		Connection conn = null;
		String dbURL = dbPrefix + ":" + dbPort + "/" + dbName;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}	
}
