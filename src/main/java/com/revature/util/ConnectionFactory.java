package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:postgresql://database-1.ccmfvntumpjm.us-east-1.rds.amazonaws.com:5432/postgres",
				"postgres",
				"6880Excelsior!"
				);
	}

	public static ConnectionFactory getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
