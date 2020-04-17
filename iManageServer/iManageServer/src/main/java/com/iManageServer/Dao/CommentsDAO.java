package com.iManageServer.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iManageServer.Pojo.CommentsPojo;

public class CommentsDAO {

	
	
	

	public boolean addComment(int id, String comment,String time) {
		int queryexecuted = 0;
		String query = "INSERT INTO requests_comments (request_id,comment,time) values (?,?,?)";
	
		System.out.println("Connecting to Database");
		Connection conn = null;
		 
        try {
            conn = ConnectDB.getConnection();
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, id);
                pstmt.setString(2, comment);
                pstmt.setString(3, time);
                queryexecuted = pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
        	System.out.println("Login error -->" + ex.getMessage());
        	return false;
        } finally {
            ConnectDB.close(conn);
        }
		
		
		return (queryexecuted == 1)?true:false;
		
	}

	public List<CommentsPojo> getComments(int requestId) {
		
		System.out.println(requestId);
		List<CommentsPojo> temp = new ArrayList<CommentsPojo>();
		String query = "SELECT id,comment,time from requests_comments WHERE request_id = ? ";
		

		System.out.println("Connecting to Database");
		Connection conn = null;
		 
        try {
        	conn = ConnectDB.getConnection();
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, requestId);
                ResultSet rs= pstmt.executeQuery();
                while(rs.next()) {
            		temp.add(new CommentsPojo( rs.getInt(1),rs.getString(2),rs.getString(3)));
                }
            }
        } catch (SQLException ex) {
        	System.out.println("Login error -->" + ex.getMessage());
        	return null;
        } finally {
            ConnectDB.close(conn);
        }
	
		return temp;
	
	}

	public boolean deleteComment(int id) {
		int queryexecuted = 0;
		String query = "DELETE FROM requests_comments WHERE id = ? ";
	
		System.out.println("Connecting to Database");
		Connection conn = null;
		 
        try {
        	conn = ConnectDB.getConnection();
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, id);
                queryexecuted = pstmt.executeUpdate();
            }
 
        } catch (SQLException ex) {
        	System.out.println("Login error -->" + ex.getMessage());
        	return false;
        } finally {
            ConnectDB.close(conn);
        }
        
		return (queryexecuted == 1)?true:false;
	}
}
