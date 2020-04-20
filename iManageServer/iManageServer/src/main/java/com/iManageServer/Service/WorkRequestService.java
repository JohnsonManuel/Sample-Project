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
	
	WorkDAO dao = new WorkDAO();

	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addNewWorkRequest(WorkRequestPojo workrequestbean) {

		return dao.addWorkRequestToDB(workrequestbean);
	}
	
	
	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean updateWorkRequest( WorkRequestPojo workrequestbean) {

		return dao.updateRequestToDB(workrequestbean);
	}
	
	@POST
	@Path("allrequser")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<WorkRequestPojo> getAllWorkRequestsUser(@FormParam("user") String user) {
		
		log.trace("POST Request received with paramaeters "+user);

		return dao.getAllWorkRequestsUser(user);
	}
	
	@POST
	@Path("allreqadmin")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<WorkRequestPojo> getAllWorkRequestsAdmin(@FormParam("team") String team) {
		
		log.trace("POST Request received with paramaeters "+team);

		
		return dao.getAllWorkRequestsAdmin(team);
	}
	
	@POST
	@Path("delete")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public boolean deleteWorkRequests( @FormParam("id") int id ) {
		
		log.trace("POST Request received with paramaeters "+id);

		
		return dao.deleteWorkRequests(id);
	}
	
	
	
	
}
