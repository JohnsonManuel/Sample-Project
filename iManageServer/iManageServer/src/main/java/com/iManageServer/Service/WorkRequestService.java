package com.iManageServer.Service;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.iManageServer.Dao.WorkDAO;
import com.iManageServer.Pojo.WorkRequestPojo;

@Path("Work")
public class WorkRequestService {

	private static final Logger log = LogManager.getLogger("mainLogger");	

	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addNewWorkRequest( @FormParam("assignedBy") String assignedBy ,@FormParam("name") String name,@FormParam("type") String type,@FormParam("description") String description,@FormParam("status") String status ,@FormParam("team") String team) {
		
		log.trace("POST Request received with paramaeters "+assignedBy+" "+name+" "+type+" "+description+" "+status+" "+team);

		WorkDAO dao = new WorkDAO();
		return dao.addWorkRequestToDB(assignedBy,name,type,description,status,team);
	}
	
	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean updateWorkRequest(  @FormParam("id") int id,@FormParam("name") String name,@FormParam("description") String description,@FormParam("status") String status,@FormParam("comment") String comment   ) {
		
		log.trace("POST Request received with paramaeters "+id+" "+name+" "+comment+" "+description+" "+status);

		
		WorkDAO dao = new WorkDAO();
		return dao.updateRequestToDB(id,name,description,status,comment);
	}
	
	@POST
	@Path("allrequser")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<WorkRequestPojo> getAllWorkRequestsUser(@FormParam("user") String user) {
		
		log.trace("POST Request received with paramaeters "+user);

		WorkDAO dao = new WorkDAO();
		return dao.getAllWorkRequestsUser(user);
	}
	
	@POST
	@Path("allreqadmin")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<WorkRequestPojo> getAllWorkRequestsAdmin(@FormParam("team") String team) {
		
		log.trace("POST Request received with paramaeters "+team);

		
		WorkDAO dao = new WorkDAO();
		return dao.getAllWorkRequestsAdmin(team);
	}
	
	@POST
	@Path("delete")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public boolean deleteWorkRequests( @FormParam("id") int id ) {
		
		log.trace("POST Request received with paramaeters "+id);

		
		System.out.println(id);
		WorkDAO dao = new WorkDAO();
		return dao.deleteWorkRequests(id);
	}
	
	
	
	
}
