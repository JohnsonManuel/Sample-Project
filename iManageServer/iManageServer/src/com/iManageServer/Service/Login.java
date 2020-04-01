package com.iManageServer.Service;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.iManageServer.Dao.WorkDAO;

@Path("login")
public class Login {

	@POST
	@Path("user")
	@Produces(MediaType.APPLICATION_JSON)
	public String validateUser(@FormParam("user")String user,@FormParam("pass")String pass) {
		System.out.println(" User and pass are" + user+" "+ pass);
		WorkDAO workDAO = new WorkDAO();
		return workDAO.checkuserinDB(user, pass);
	}
	
	@GET
	@Path("captcha")
	@Produces(MediaType.APPLICATION_JSON)
	public String GetCaptcha() {
		WorkDAO work = new WorkDAO();
		String temp = work.getcaptcha(6);
		return temp;
	}
	
	
	
	
	
}
