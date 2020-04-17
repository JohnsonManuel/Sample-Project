package com.iManageServer.Service;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.iManageServer.Dao.CommentsDAO;
import com.iManageServer.Pojo.CommentsPojo;

@Path("comment")
public class CommentService {

	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addComment( @FormParam("id") int id,@FormParam("comment") String comment, @FormParam("time") String time ) {
		CommentsDAO dao = new CommentsDAO();
		return dao.addComment(id,comment,time);
	}

	
	@POST	
	@Path("getcomments")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<CommentsPojo> getComments( @FormParam("id") int id ) {
		CommentsDAO dao = new CommentsDAO();
		return dao.getComments(id);
	}
	
	@POST	
	@Path("delete")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public boolean deleteComment( @FormParam("id") int id ) {
		CommentsDAO dao = new CommentsDAO();
		return dao.deleteComment(id);
	}
	
	
	
	
	
}
