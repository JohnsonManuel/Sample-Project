package com.iManageServer.Service;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.iManageServer.Dao.WorkDAO;
import com.iManageServer.Pojo.WorkRequestPojo;

@Path("Work")
public class WorkRequestService {

	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addNewWorkRequest( @FormParam("name") String name,@FormParam("type") String type,@FormParam("description") String description,@FormParam("status") String status  ) {
		WorkDAO dao = new WorkDAO();
		dao.addWorkRequestToDB(name,type,description,status);
		return true;
	}
	
	@GET
	@Path("allreq")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<WorkRequestPojo> getAllWorkRequests() {
		WorkDAO dao = new WorkDAO();
		return dao.getAllWorkRequests();
	}
	
	@GET
	@Path("inprogressreq")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<WorkRequestPojo> getInprogressRequests() {
		WorkDAO dao = new WorkDAO();
		return dao.getInprogressWorkRequests();
	}
	
	@GET
	@Path("completedreq")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<WorkRequestPojo> getcompletedRequests() {
		WorkDAO dao = new WorkDAO();
		return dao.getCompletedWorkRequests();
	}
	
	
}
