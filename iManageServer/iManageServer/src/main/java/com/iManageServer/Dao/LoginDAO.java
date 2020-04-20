package com.iManageServer.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginDAO {
	private static final Logger log = LogManager.getLogger("mainLogger");

	public String[] checkuserinDB(String user, String pass) {

		log.trace("Executing checkuserinDB method");

		String response[] = new String[2];
		String query = "SELECT user_type,team from userlogin_details where username = ? and password = ?";

		System.out.println(response[0] + " " + response[1]);

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
