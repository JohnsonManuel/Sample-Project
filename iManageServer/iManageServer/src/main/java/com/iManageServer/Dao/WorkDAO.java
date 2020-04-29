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

	public boolean addWorkRequestToDB(WorkRequestPojo pojo) {

		log.trace("Executing addWorkRequestToDB method");

		int queryexecuted = 0;
		String query = "INSERT INTO work_requests (assigned_by,request_name,request_type,request_description,request_status,team,requester_team) values (?,?,?,?,?,?,?)";

		log.trace("Connecting to Database");
		Connection conn = null;

		try {
			conn = ConnectDB.getConnection();
			if (conn != null) {
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, pojo.getRequester());
				pstmt.setString(2, pojo.getName());
				pstmt.setString(3, pojo.getRequestType());
				pstmt.setString(4, pojo.getDescription());
				pstmt.setString(5, pojo.getStatus());
				pstmt.setString(6, pojo.getTeam());
				pstmt.setString(7, pojo.getRequesterTeam());

				log.trace("Executing query " + query);

				queryexecuted = pstmt.executeUpdate();
			}

		} catch (SQLException ex) {
			log.error("Connection error");
			log.error(ex.getMessage());
			return false;
		} finally {
			ConnectDB.close(conn);
		}

		return (queryexecuted == 1) ? true : false;
	}

	public List<WorkRequestPojo> getAllWorkRequestsAdmin(String team) {

		log.trace("Executing getAllWorkRequestsAdmin method");

		List<WorkRequestPojo> temp = new ArrayList<WorkRequestPojo>();
		String query = "SELECT id,request_name,assigned_by,request_type,request_description,request_status,comments,team,requester_team from work_requests where team = ?";

		log.trace("Connecting to Database");
		Connection conn = null;

		try {
			conn = ConnectDB.getConnection();
			if (conn != null) {
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, team);

				log.trace("Executing query " + query);

				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					temp.add(new WorkRequestPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getString(9) ));
				}
			}

		} catch (SQLException ex) {
			log.error("Connection error");
			log.error(ex.getMessage());
			return null;
		} finally {
			ConnectDB.close(conn);
		}

		return temp;
	}

	public List<WorkRequestPojo> getAllWorkRequestsUser(String user) {

		log.trace("Executing getAllWorkRequestsUser method");

		List<WorkRequestPojo> temp = new ArrayList<WorkRequestPojo>();
		String query = "SELECT id,request_name,assigned_by,request_type,request_description,request_status,comments,team,requester_team from work_requests WHERE assigned_by = ? ";

		log.trace("Connecting to Database");
		Connection conn = null;

		try {
			conn = ConnectDB.getConnection();
			if (conn != null) {
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, user);

				log.trace("Executing query " + query);

				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					temp.add(new WorkRequestPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
				}
			}

		} catch (SQLException ex) {
			log.error("Connection error");
			log.error(ex.getMessage());
			return null;
		} finally {
			ConnectDB.close(conn);
		}

		return temp;
	}

	public boolean updateRequestToDB(WorkRequestPojo bean) {

		log.trace("Executing updateRequestToDB method");

		int queryexecuted = 0;
		String query = "UPDATE work_requests  SET request_status = ? , request_name = ? , request_description = ? ,comments = ? WHERE id= ?";

		log.trace("Connecting to Database");
		Connection conn = null;

		try {
			conn = ConnectDB.getConnection();
			if (conn != null) {
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, bean.getStatus());
				pstmt.setString(2, bean.getName());
				pstmt.setString(3, bean.getDescription());
				pstmt.setString(4, bean.getComment());
				pstmt.setInt(5, bean.getRequestID());

				log.trace("Executing query " + query);

				queryexecuted = pstmt.executeUpdate();
			}

		} catch (SQLException ex) {
			log.error("Connection error");
			log.error(ex.getMessage());
			return false;
		} finally {
			ConnectDB.close(conn);
		}

		return (queryexecuted == 1) ? true : false;
	}

	public boolean deleteWorkRequests(int id) {

		log.trace("Executing deleteWorkRequests method");

		int queryexecuted = 0;
		String query = "DELETE FROM work_requests WHERE id = ? ";

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
			log.error(ex.getMessage());
			return false;
		} finally {
			ConnectDB.close(conn);
		}

		return (queryexecuted == 1) ? true : false;
	}

}
