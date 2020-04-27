package com.iManage.Client;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.iManage.Filter.ClientFilter;

/**
 * @author Johnson Manuel
 *
 */

public class Login {
	private static final Logger log = LogManager.getLogger("mainLogger");
	private static String BASE_PATH = "http://localhost:8181/iManageServer/rest/login";

	/**
	 * Sends a POST request to check if user in registered.
	 * @param user
	 * @param pass
	 * @return
	 */
	public Map<String, String> checkuserinDB(String user, String pass) {
		log.trace("Sending request with parameterrs User and pass ");

		Client client = ClientBuilder.newClient();

		WebTarget target = client.target("http://localhost:8181/iManageServer/rest/login").path("user");
		Form form = new Form();
		form.param("user", user);
		form.param("pass", pass);
		Response response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);

		Map<String, String> user_details = response.readEntity(new GenericType<HashMap<String, String>>() {
		});

		return user_details;
	}


	/**
	 * Sends a POST request to get a random Captcha string generated at the server.
	 * @return
	 */
	public String getCaptchaString() {
		log.trace("Sending captha server request");

		String captcha;

		WebTarget target = Login.getWebTarget().path("captcha");

		try {
			captcha = target.request(MediaType.APPLICATION_JSON).get(String.class);

		} catch (

		NotAuthorizedException e) {
			log.error("The Server rejected the request as authid is incorrect");
			captcha = null;
		}

		log.trace("Response received :" + captcha);

		return captcha;
	}

	/**
	 * Sends an Empty post request 
	 * Invalidates the Authid Token
	 */
	public void logout() {

		log.trace("Sending captha server request");

		WebTarget target = Login.getWebTarget().path("logout");

		try {
			target.request(MediaType.APPLICATION_JSON).post(Entity.json(""));

		} catch (NotAuthorizedException e) {
			log.error("The Server rejected the request as authid is incorrect");
		}

		log.trace("User logged out");

	}

	public static WebTarget getWebTarget() {
		Client client = ClientBuilder.newClient().register(ClientFilter.class);
		WebTarget target = client.target(BASE_PATH);
		return target;
	}

}
