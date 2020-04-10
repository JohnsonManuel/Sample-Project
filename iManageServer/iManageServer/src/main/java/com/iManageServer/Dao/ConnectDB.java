package com.iManageServer.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	public static Connection getConnection() {
		
		 
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://DESKTOP-JVD9T66\\HOMEDB:1433;databaseName=iManageDB";
            String DBuser = "sa";
            String DBpass = "Jmred1234";
            Connection conn = DriverManager.getConnection(dbURL, DBuser, DBpass);
            return conn;
 
        }
        catch (Exception ex) {
			System.out.println("Database.getConnection() Error -->"
					+ ex.getMessage());
			return null;
		}
      
	}

	public static void close(Connection con) {
		try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
}
