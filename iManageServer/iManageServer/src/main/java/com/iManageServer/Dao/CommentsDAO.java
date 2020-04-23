package com.iManageServer.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.iManageServer.Pojo.CommentsPojo;

public class CommentsDAO {
	private static final Logger log = LogManager.getLogger("mainLogger");

	public boolean addComment(int id, String comment, String time) {

		log.trace("Executing addComment method");

		int queryexecuted = 0;
		String query = "INSERT INTO requests_comments (request_id,comment,time) values (?,?,?)";

		log.trace("Connecting to Database");
		Connection conn = null;

		try {
			conn = ConnectDB.getConnection();
			if (conn != null) {
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, id);
				pstmt.setString(2, comment);
				pstmt.setString(3, time);

				log.trace("Executing query " + query);

				queryexecuted = pstmt.executeUpdate();
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {
			ConnectDB.close(conn);
		}

		return (queryexecuted == 1) ? true : false;

	}

	public List<CommentsPojo> getComments(int requestId) {

		log.trace("Executing getComments method");

		System.out.println(requestId);
		List<CommentsPojo> temp = new ArrayList<CommentsPojo>();
		String query = "SELECT id,comment,time from requests_comments WHERE request_id = ? ";

		log.trace("Connecting to Database");
		Connection conn = null;

		try {
			conn = ConnectDB.getConnection();
			if (conn != null) {
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, requestId);

				log.trace("Executing query " + query);

				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					temp.add(new CommentsPojo(rs.getInt(1), rs.getString(2), rs.getString(3)));
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

	public boolean deleteComment(int id) {

		log.trace("Executing deleteComment method");

		int queryexecuted = 0;
		String query = "DELETE FROM requests_comments WHERE id = ? ";

		log.trace("Connecting to Database");
		Connection conn = null;

		try {
			conn = ConnectDB.getConnection();
			if (conn != null) {
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, id);

				log.trace("Executing query " + query);

				queryexecuted = pstmt.executeUpdate();
			}

		} catch (SQLException ex) {
			log.error("Connection error");
			return false;
		} finally {
			ConnectDB.close(conn);
		}

		return (queryexecuted == 1) ? true : false;
	}
}
