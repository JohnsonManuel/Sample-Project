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

import com.iManageServer.Dao.CommentsDAO;
import com.iManageServer.Pojo.CommentsPojo;

@Path("comment")
public class CommentService {

	CommentsDAO dao = new CommentsDAO();

	@NameBinding
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.TYPE, ElementType.METHOD })
	public @interface Secured {
	}

	@Secured
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addComment(@FormParam("id") int id, @FormParam("comment") String comment,
			@FormParam("time") String time) {
		
		if( !ServerValidation.ESAPIvalidateString(String.valueOf(id))
				||!ServerValidation.ESAPIvalidateString(comment)
				||!ServerValidation.ESAPIvalidateString(time)) {
			 throw new BadRequestException("Bad request");
		}
		
		return dao.addComment(id, comment, time);
	}

	@Secured
	@POST
	@Path("getcomments")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<CommentsPojo> getComments(@FormParam("id") int id) {
		
		if( !  ServerValidation.ESAPIvalidateString(String.valueOf(id))  ) {
			 throw new BadRequestException("Bad request");
		}

		return dao.getComments(id);
	}

	@Secured
	@POST
	@Path("delete")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public boolean deleteComment(@FormParam("id") int id) {
		
		if( !  ServerValidation.ESAPIvalidateString(String.valueOf(id))  ) {
			 throw new BadRequestException("Bad request");
		}

		return dao.deleteComment(id);
	}

}
