package com.iManage.Client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class Login {
	
	
	public boolean checkuserinDB(String user,String pass) {
		 Client client = ClientBuilder.newClient();
		 WebTarget target = client.target("http://localhost:8181").path("iManageServer").path("rest").path("login").path("user");
		 System.out.println(target.getUri());
		 Form form = new Form();
		 form.param("user",user);
		 form.param("pass",pass);
		 String validUser = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),String.class);
		 
		return Boolean.parseBoolean(validUser);
	}
	
		//  http://localhost:8181/iManageServer/rest/login/captcha
	public String getCaptchaString() {
		 Client client = ClientBuilder.newClient();
		 WebTarget target = client.target("http://localhost:8181").path("iManageServer").path("rest").path("login").path("captcha");
		 System.out.println(target.getUri());
		 String captcha = target.request(MediaType.APPLICATION_JSON).get(String.class);
		 
		 System.out.println("the captcha returned from server is "+ captcha);
		 
		return captcha;
	}
}
