package com.iManage.Client;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Login {
	private static final Logger log = LogManager.getLogger("mainLogger");	

	public String[] checkuserinDB(String user,String pass) {
		 log.trace("Sending request with parameterrs User and pass ");
		 Client client = ClientBuilder.newClient();
		 WebTarget target = client.target("http://localhost:8181").path("iManageServer").path("rest").path("login").path("user");
		 System.out.println(target.getUri());
		 Form form = new Form();
		 form.param("user",user);
		 form.param("pass",pass);
		 String validUserType[] = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),String[].class);
		 log.trace("Response received :"+ validUserType);
		return validUserType;
	}
	
	
	
	public String getCaptchaString() {
		 log.trace("Sending captha server request");
		 Client client = ClientBuilder.newClient();
		 WebTarget target = client.target("http://localhost:8181").path("iManageServer").path("rest").path("login").path("captcha");
		 System.out.println(target.getUri());
		 String captcha = target.request(MediaType.APPLICATION_JSON).get(String.class);		
		 log.trace("Response received :"+ captcha);

		return captcha;
	}
}
