package com.iManageServer.Service;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.iManageServer.Dao.WorkDAO;
import com.iManageServer.Pojo.CommentsPojo;

@Path("comment")
public class CommentService {

	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addNewWorkRequest( @FormParam("id") int id,@FormParam("comment") String comment ) {
		WorkDAO dao = new WorkDAO();
		return dao.addComment(id,comment);
	}

	
	@POST
	@Path("getcomments")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<CommentsPojo> getAllWorkRequests( @FormParam("id") int id ) {
		WorkDAO dao = new WorkDAO();
		return dao.getComments(id);
	}
	
	
	
	
	
}
