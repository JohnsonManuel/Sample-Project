package com.iManageServer.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.FormParam;
import javax.ws.rs.NameBinding;
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
	Boolean response;
	List<WorkRequestPojo> templist;

	@NameBinding
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.TYPE, ElementType.METHOD })
	public @interface Secured {}

	@Secured
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addNewWorkRequest(WorkRequestPojo workrequestbean) {
		log.trace("POST request came with workrequestbean " + workrequestbean.getRequestID());

		if( ! ServerValidation.ESAPIWorkvalidateBean(workrequestbean) ) {
			 throw new BadRequestException("Bad request");
		}
		

		response = dao.addWorkRequestToDB(workrequestbean);

		log.trace("Sending response " + response);

		return response;
	}

	

	@Secured
	@POST
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean updateWorkRequest(WorkRequestPojo workrequestbean) {

		log.trace("POST request came with bean " + workrequestbean.getRequestID());

		if( ! ServerValidation.ESAPIWorkvalidateBean(workrequestbean) ) {
			 throw new BadRequestException("Bad request");
		}
		
		response = dao.updateRequestToDB(workrequestbean);

		log.trace("Sending response " + response);

		return response;
	}

	@Secured
	@POST
	@Path("allrequser")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<WorkRequestPojo> getAllWorkRequestsUser(@FormParam("user") String user) {

		log.trace("POST Request received with paramaeters " + user);
		
		
		if( !  ServerValidation.ESAPIvalidateString(user) ) {
			 throw new BadRequestException("Bad request");
		}
		
		return dao.getAllWorkRequestsUser(user);
	}

	@Secured
	@POST
	@Path("allreqadmin")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<WorkRequestPojo> getAllWorkRequestsAdmin(@FormParam("team") String team) {

		log.trace("POST Request received with paramaeters " + team);

		if( !  ServerValidation.ESAPIvalidateString(team)  ) {
			 throw new BadRequestException("Bad request");
		}
		
		templist = dao.getAllWorkRequestsAdmin(team);

		if (null != templist)
			log.trace("Sending respone list of size " + templist.size());

		return templist;
	}

	@Secured
	@POST
	@Path("delete")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public boolean deleteWorkRequests(@FormParam("id") int id) {

		log.trace("POST Request received with paramaeters " + id);

		if( !  ServerValidation.ESAPIvalidateString(String.valueOf(id))  ) {
			 throw new BadRequestException("Bad request");
		}
		
		response = dao.deleteWorkRequests(id);

		log.trace("Sending respone of " + response);

		return response;
	}
	
	
	
	

}
