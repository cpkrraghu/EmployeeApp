package com.imcs.raghu.bonus.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnectionFactory {
	final static Logger logger=Logger.getLogger(ConnectionFactory.class);
	//comment from 1170
	private Connection connection;
	private static ConnectionFactory cf ;
	private ConnectionFactory(){
		
	}
	private static ConnectionFactory getInstance(){
		if(cf==null)
			cf=new ConnectionFactory();
		return cf;
	}
	public static Connection getConnection(){
		return getInstance().createConnection();
	}
	private Connection createConnection() {
		Properties credentialsProps = null;
		boolean error = true;
		try {
			credentialsProps = new Properties();
			InputStream stream = getClass().getClassLoader().getResourceAsStream("credentials.properties");
			if (stream == null) {
				logger.error("Error in loading the credentials for JDBC, "
						+ "credentials.properties file with jdbc credentials in the following foramt is required \n"
						+ "userName=userName\npassword=password");
				return null;
			}
			credentialsProps.load(stream);
			error = false;
		} catch (FileNotFoundException e1) {
			logger.error("Error in loading the credentials for JDBC, "
					+ "credentials.properties file with jdbc credentials in the following foramt is required \n"
					+ "userName=userName\npassword=password");
		} catch (IOException e1) {
			logger.error("Failed to load the file credentials.properties");
		}

		if (error) {
			return null;
		}

		try {
			logger.info("loading driver");
			Class.forName(credentialsProps.getProperty("driver.name"));
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		}

		String url = credentialsProps.getProperty("url");
		String user =credentialsProps.getProperty("user");
		String password = credentialsProps.getProperty("pwd");

		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return connection;
	}
	
}
