package com.iManageServer.Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.iManageServer.Pojo.WorkRequestPojo;

public class WorkDAO {
	private static final Logger log = LogManager.getLogger("mainLogger");	

	
	public String[] checkuserinDB(String user,String pass) {
		
		String response[] = new String[2];
		String query = "SELECT user_type,team from userlogin_details where username = ? and password = ?";
		
		System.out.println(response[0]+" "+response[1]);

log.trace("Connecting to Database");
		Connection conn = null;
		 
        try {        	

        	conn = ConnectDB.getConnection();
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, user);
                pstmt.setString(2, pass);
                ResultSet rs= pstmt.executeQuery();
                while(rs.next()) {
                	response[0] = rs.getString("user_type");
                	response[1] = rs.getString("team");
                }
            }
 
        } catch (SQLException ex) {
        	log.error("Connection error");
        	return null;
        } finally {
            ConnectDB.close(conn);
        }
		
		
		return response;
	}
	
	public boolean addWorkRequestToDB(String assignedBy,String name, String type, String description, String status,String team) {
		int queryexecuted = 0;
		String query = "INSERT INTO work_requests (assigned_by,request_name,request_type,request_description,request_status,team) values (?,?,?,?,?,?)";
	
		log.trace("Connecting to Database");
		Connection conn = null;
		 
        try {
        	conn = ConnectDB.getConnection();
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, assignedBy);
                pstmt.setString(2, name);
                pstmt.setString(3, type);
                pstmt.setString(4, description);
                pstmt.setString(5, status);
                pstmt.setString(6, team);
                queryexecuted = pstmt.executeUpdate();
            }
 
        }  catch (SQLException ex) {
        	log.error("Connection error");
        	return false;
        } finally {
            ConnectDB.close(conn);
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
	
public List<WorkRequestPojo> getAllWorkRequestsAdmin(String team){
		
		List<WorkRequestPojo> temp = new ArrayList<WorkRequestPojo>();
		String query = "SELECT id,request_name,assigned_by,request_type,request_description,request_status,comments,team from work_requests where team = ?";
		
		log.trace("Connecting to Database");
		Connection conn = null;
		 
        try {
        	conn = ConnectDB.getConnection();
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, team);
                ResultSet rs= pstmt.executeQuery();
                while(rs.next()) {
            		temp.add(new WorkRequestPojo( rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8) ));
                }
            }
 
        } catch (SQLException ex) {
        	log.error("Connection error");
        	return null;
        } finally {
            ConnectDB.close(conn);
        }
	
		return temp;
	}
	public List<WorkRequestPojo> getAllWorkRequestsUser(String user){
		
		List<WorkRequestPojo> temp = new ArrayList<WorkRequestPojo>();
		String query = "SELECT id,request_name,assigned_by,request_type,request_description,request_status,comments,team from work_requests WHERE assigned_by = ? ";
		
		System.out.println(user);
		log.trace("Connecting to Database");
		Connection conn = null;
		 
        try {
        	conn = ConnectDB.getConnection();
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, user);
                ResultSet rs= pstmt.executeQuery();
                while(rs.next()) {
            		temp.add(new WorkRequestPojo( rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8) ));
                }
            }
 
        } catch (SQLException ex) {
        	log.error("Connection error");
        	return null;
        } finally {
            ConnectDB.close(conn);
        }
	
		return temp;
	}
	
	public boolean updateRequestToDB(int id,String name, String description, String status ,String comment) {
		int queryexecuted = 0;
		String query = "UPDATE work_requests  SET request_status = ? , request_name = ? , request_description = ? ,comments = ? WHERE id= ?";
	
		log.trace("Connecting to Database");
		Connection conn = null;
		 
        try {
        	conn = ConnectDB.getConnection();
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, status);
                pstmt.setString(2, name);
                pstmt.setString(3, description);
                pstmt.setString(4, comment);
                pstmt.setInt(5, id);
                queryexecuted = pstmt.executeUpdate();
            }
 
        } catch (SQLException ex) {
        	log.error("Connection error");
        	return false;
        } finally {
            ConnectDB.close(conn);	
        }
		
		
		return (queryexecuted == 1)?true:false;
	}

	public boolean deleteWorkRequests(int id) {
		int queryexecuted = 0;
		String query = "DELETE FROM work_requests WHERE id = ? ";
	
		log.trace("Connecting to Database");
		Connection conn = null;
		 
        try {
        	conn = ConnectDB.getConnection();
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, id);
                queryexecuted = pstmt.executeUpdate();
            }
 
        } catch (SQLException ex) {
        	
        	log.error("Connection error");
        	return false;
        } finally {
            ConnectDB.close(conn);
        }
		
		
		return (queryexecuted == 1)?true:false;
	}

	
	
	
}
