package com.iManageServer.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.NameBinding;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.iManageServer.Dao.LoginDAO;

@Singleton
@Path("login")
public class LoginService {
	private static final Logger log = LogManager.getLogger("mainLogger");

	private List<String> tokens;

	LoginDAO login = new LoginDAO();

	public LoginService() {
		tokens = new ArrayList<String>();
	}

	@NameBinding
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.TYPE, ElementType.METHOD })
	public @interface Secured {
	}

	@POST
	@Path("user")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> validateUser(@FormParam("user") String user, @FormParam("pass") String pass) {
		log.trace("POST Request received with paramaeters " + user + " " + pass);

		Map<String, String> response = login.checkuserinDB(user, pass);

		if (response.get("user_type") != null) {
			System.out.println(tokens.size());
			String authcode = generateAuthId(16);
			tokens.add(authcode);
			response.put("AUTHID", authcode);
		}

		return response;
	}

	@Secured
	@GET
	@Path("captcha")
	@Produces(MediaType.APPLICATION_JSON)
	public String GetCaptcha() {
		log.trace("POST Request received for captcha ");

		String temp = login.getcaptcha(6);

		log.trace("Sending captcha: " + temp);

		return temp;
	}

	@Secured
	@POST
	@Path("logout")
	@Produces(MediaType.APPLICATION_JSON)
	public void logoutuser(@Context HttpHeaders httpheaders) {
		log.trace("POST Request received for logout ");
		String token = httpheaders.getHeaderString(HttpHeaders.AUTHORIZATION);

		System.out.println(token);

		if (token != null && token.startsWith("Bearer")) {
			token = token.split(" ")[1];
		}

		System.out.println(token);
		tokens.remove(token);

	}

	public String generateAuthId(int length) {
		String elegibleChars = "ABCDEFGHJKLMNPQRSTUVWXY" + "abcdefghjkmnpqrstuvwxy" + "12345";
		char[] chars = elegibleChars.toCharArray();
		StringBuffer finalString = new StringBuffer();
		for (int i = 0; i < length; i++) {
			finalString.append(chars[(int) Math.round(Math.random() * (chars.length - 1))]);
		}
		return finalString.toString();

	}

	public List<String> getTokens() {
		return tokens;
	}

	public void setTokens(List<String> tokens) {
		this.tokens = tokens;
	}

}
