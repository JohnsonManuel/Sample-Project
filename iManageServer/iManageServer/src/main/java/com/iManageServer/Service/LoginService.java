package com.iManageServer.Service;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.iManageServer.Dao.LoginDAO;



@Path("login")
public class LoginService{
	private static final Logger log = LogManager.getLogger("mainLogger");	
	LoginDAO login = new LoginDAO();

	@POST
	@Path("user")
	@Produces(MediaType.APPLICATION_JSON)
	public String[] validateUser(@FormParam("user")String user,@FormParam("pass")String pass) {
		log.trace("POST Request received with paramaeters "+user+" "+pass );
		return login.checkuserinDB(user, pass);
	}
	
	@GET
	@Path("captcha")
	@Produces(MediaType.APPLICATION_JSON)
	public String GetCaptcha() {
		log.trace("POST Request received " );

		String temp = login.getcaptcha(6);	
		log.trace("Sending captcha: "+temp);

		return temp;
	}
	
	
	
	
	
}
