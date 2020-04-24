package com.iManage.Client;

import java.util.List;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.iManage.Bean.CommentsBean;
import com.iManage.Filter.ClientFilter;

public class Comments {

	private static final Logger log = LogManager.getLogger("mainLogger");
	private static String BASE_PATH = "http://localhost:8181/iManageServer/rest/comment";

	public boolean addComment(int id, String comment, String time) {

		WebTarget target = Comments.getWebTarget().path("add");

		System.out.println("getAll() " + target.getUri());
		Form form = new Form();
		form.param("id", String.valueOf(id));
		form.param("comment", comment);
		form.param("time", time);

		boolean result;
		try {
			result = target.request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Boolean.class);

		} catch (NotAuthorizedException e) {
			log.error("The Server rejected the request as authid is incorrect");
			result = false;
		}

		log.trace("updated? " + result);

		return result;

	}

	
	/**
	 * Sends a POST request to retreive a List of comments for workId
	 * @param workId - Unique id of workRequest
	 * @return List<CommentsBean> - list of comments
	 */
	public List<CommentsBean> getComments(int workId) {

		log.trace("Get all comments of " + workId);

		WebTarget target = Comments.getWebTarget().path("getcomments");
		System.out.println("getAll() " + target.getUri());
		Form form = new Form();
		form.param("id", String.valueOf(workId));
		List<CommentsBean> response;
		try {
			response = target.request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class)
					.readEntity(new GenericType<List<CommentsBean>>() {
					});

		} catch (NotAuthorizedException e) {
			log.error("The Server rejected the request as authid is incorrect");
			response = null;
		}

		log.trace("Response list size " + response.size());
		return response;

	}

	/**
	 * Sends post request to delete comment.
	 * @param commentId - unique id of comment.
	 * @return boolean true if the post request was successful , false otherwise
	 */
	public boolean deleteComments(int commentId) {
		log.trace("Delete Comment of id " + commentId);

		WebTarget target = Comments.getWebTarget().path("delete");
		System.out.println("getAll() " + target.getUri());
		Form form = new Form();
		form.param("id", String.valueOf(commentId));
		boolean result;

		try {
			result = target.request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Boolean.class);
		} catch (NotAuthorizedException e) {
			log.error("The Server rejected the request as authid is incorrect");
			result = false;
		}

		return result;
	}

	/**
	 * Utility class added to return WebTarget
	 * @return WebTarget 
	 */
	public static WebTarget getWebTarget() {
		Client client = ClientBuilder.newClient().register(ClientFilter.class);
		WebTarget target = client.target(BASE_PATH);
		return target;
	}

}
