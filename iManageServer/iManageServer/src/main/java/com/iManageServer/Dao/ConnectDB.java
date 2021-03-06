package com.iManageServer.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectDB {

	private static final Logger log = LogManager.getLogger("mainLogger");

	public static Connection getConnection() {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String dbURL = PropertiesResource.getInstance().getProperty("db.url");
			String DBuser = PropertiesResource.getInstance().getProperty("db.user");
			String DBpass = PropertiesResource.getInstance().getProperty("db.pass");
			Connection conn = DriverManager.getConnection(dbURL, DBuser, DBpass);
			return conn;

		} catch (Exception ex) {
			log.trace("Database.getConnection() Error -->" + ex.getMessage());
			return null;
		}

	}

	public static void close(Connection con) {
		try {
			if (con != null && !con.isClosed()) {
				log.trace("Connection closed");
				con.close();

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
