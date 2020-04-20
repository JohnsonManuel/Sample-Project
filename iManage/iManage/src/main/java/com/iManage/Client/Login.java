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
	private static String BASE_PATH = "http://localhost:8181/iManageServer/rest/login";
	
	
	public String[] checkuserinDB(String user,String pass) {
		 log.trace("Sending request with parameterrs User and pass ");
		 
		 WebTarget target = Login.getWebTarget().path("user");
				
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
		 WebTarget target = Login.getWebTarget().path("captcha");
		 System.out.println(target.getUri());
		 String captcha = target.request(MediaType.APPLICATION_JSON).get(String.class);		
		 log.trace("Response received :"+ captcha);

		return captcha;
	}
	
	
	public static WebTarget getWebTarget() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_PATH);
		return target;
	}
}
