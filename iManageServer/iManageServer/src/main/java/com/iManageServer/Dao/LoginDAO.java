package com.iManageServer.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginDAO {

	private static final Logger log = LogManager.getLogger("mainLogger");

	public Map<String, String> checkuserinDB(String user, String pass) {

		log.trace("Executing checkuserinDB method");

		Map<String, String> response = new HashMap<String, String>();
		String query = "SELECT user_type,team from userlogin_details where username = ? and password = ?";

		log.trace("Connecting to Database");
		Connection conn = null;

		try {

			conn = ConnectDB.getConnection();
			if (conn != null) {
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, user);
				pstmt.setString(2, pass);

				log.trace("Executing query " + query);

				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					response.put("user_type", rs.getString("user_type"));
					response.put("team", rs.getString("team"));
				}

				log.trace("Response is  " + response.get("user_type") + " " + response.get("teams"));

			}

		} catch (SQLException ex) {
			log.error("Connection error");
			return null;
		} finally {
			ConnectDB.close(conn);
		}

		return response;
	}

	public String getcaptcha(int length) {

		log.trace("Executing getcaptcha method");

		String elegibleChars = "ABCDEFGHJKLMNPQRSTUVWXY" + "abcdefghjkmnpqrstuvwxy" + "123456789";
		char[] chars = elegibleChars.toCharArray();
		StringBuffer finalString = new StringBuffer();
		for (int i = 0; i < length; i++) {
			finalString.append(chars[(int) Math.round(Math.random() * (chars.length - 1))]);
		}
		return finalString.toString();
	}
}
