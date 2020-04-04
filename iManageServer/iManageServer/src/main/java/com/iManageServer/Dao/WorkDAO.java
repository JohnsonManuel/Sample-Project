package com.iManageServer.Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iManageServer.Pojo.WorkRequestPojo;

public class WorkDAO {
	
	
	public String checkuserinDB(String user,String pass) {
		
		String user_type = null;
		String query = "SELECT user_type from userlogin_details where username = ? and password = ?";
		
		System.out.println(user_type);

		System.out.println("Connecting to Database");
		Connection conn = null;
		 
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://DESKTOP-JVD9T66\\HOMEDB:1433;databaseName=iManageDB";
            String DBuser = "sa";
            String DBpass = "Jmred1234";
            conn = DriverManager.getConnection(dbURL, DBuser, DBpass);
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, user);
                pstmt.setString(2, pass);
                ResultSet rs= pstmt.executeQuery();
                while(rs.next()) {
                	user_type = rs.getString("user_type");
                }
            }
 
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
		System.out.println(user_type);
		
		return user_type;
	}
	
	public boolean addWorkRequestToDB(String name, String type, String description, String status) {
		int queryexecuted = 0;
		String query = "INSERT INTO work_requests (request_name,request_type,request_description,request_status) values (?,?,?,?)";
	
		System.out.println("Connecting to Database");
		Connection conn = null;
		 
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://DESKTOP-JVD9T66\\HOMEDB:1433;databaseName=iManageDB";
            String DBuser = "sa";
            String DBpass = "Jmred1234";
            conn = DriverManager.getConnection(dbURL, DBuser, DBpass);
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, name);
                pstmt.setString(2, type);
                pstmt.setString(3, description);
                pstmt.setString(4, status);
                queryexecuted = pstmt.executeUpdate();
            }
 
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
		
		
		return (queryexecuted == 1)?true:false;
	}

	public String getcaptcha(int length) {
        String elegibleChars = "ABCDEFGHJKLMNPQRSTUVWXY"+"abcdefghjkmnpqrstuvwxy"+"123456789";
        char[] chars = elegibleChars.toCharArray();
        StringBuffer finalString = new StringBuffer();
        for (int i = 0; i < length; i++) {
        	finalString.append(chars[(int) Math.round(Math.random()*(chars.length - 1))]);
        }
        
		return finalString.toString();
	}
	

	
	public List<WorkRequestPojo> getAllWorkRequests(){
		
		List<WorkRequestPojo> temp = new ArrayList<WorkRequestPojo>();
		String query = "SELECT request_name,request_type,request_description,request_status from work_requests ";
		

		System.out.println("Connecting to Database");
		Connection conn = null;
		 
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://DESKTOP-JVD9T66\\HOMEDB:1433;databaseName=iManageDB";
            String DBuser = "sa";
            String DBpass = "Jmred1234";
            conn = DriverManager.getConnection(dbURL, DBuser, DBpass);
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(query);
                
                ResultSet rs= pstmt.executeQuery();
                while(rs.next()) {
            		temp.add(new WorkRequestPojo( rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4) ));
                }
            }
 
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
	
		return temp;
	}
	
	public List<WorkRequestPojo> getInprogressWorkRequests(){	
		List<WorkRequestPojo> temp = new ArrayList<WorkRequestPojo>();
		String query = "SELECT request_name,request_type,request_description,request_status from work_requests where request_status = 'In Progress' ";
		
		System.out.println("Connecting to Database");
		Connection conn = null;
		 
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://DESKTOP-JVD9T66\\HOMEDB:1433;databaseName=iManageDB";
            String DBuser = "sa";
            String DBpass = "Jmred1234";
            conn = DriverManager.getConnection(dbURL, DBuser, DBpass);
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(query);
                
                ResultSet rs= pstmt.executeQuery();
                while(rs.next()) {
            		temp.add(new WorkRequestPojo( rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4) ));
                }
            }
 
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
	
		return temp;
	}
	
	public List<WorkRequestPojo> getCompletedWorkRequests(){		
		List<WorkRequestPojo> temp = new ArrayList<WorkRequestPojo>();
		String query = "SELECT request_name,request_type,request_description,request_status from work_requests where request_status = 'Completed' ";
		
		System.out.println("Connecting to Database");
		Connection conn = null;
		 
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://DESKTOP-JVD9T66\\HOMEDB:1433;databaseName=iManageDB";
            String DBuser = "sa";
            String DBpass = "Jmred1234";
            conn = DriverManager.getConnection(dbURL, DBuser, DBpass);
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(query);
                
                ResultSet rs= pstmt.executeQuery();
                while(rs.next()) {
            		temp.add(new WorkRequestPojo( rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4) ));
                }
            }
 
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
	
		return temp;
	}
}
