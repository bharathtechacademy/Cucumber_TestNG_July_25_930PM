package com.creatio.crm.framework.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	
	public static Properties prop = PropUtil.readData("Config.properties");
	
	
	// Prepare DB Connection and get the raw data
	public static ResultSet getData(String query) throws SQLException {
		ResultSet dataSet = null;
		String url = prop.getProperty("DB_URL");
		String username = prop.getProperty("DB_USERNAME");
		String password = prop.getProperty("DB_PASSWORD");		
		Connection connection = DriverManager.getConnection(url, username, password);
		dataSet =connection.createStatement().executeQuery(query);		
		return dataSet;
	}	

}
