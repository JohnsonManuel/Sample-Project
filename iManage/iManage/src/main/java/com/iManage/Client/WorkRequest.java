package com.iManage.Client;

import java.util.List;

import javax.ws.rs.BadRequestException;
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

import com.iManage.Bean.WorkRequestBean;
import com.iManage.Filter.ClientFilter;

public class WorkRequest {
	private static final Logger log = LogManager.getLogger("mainLogger");
	private static String BASE_PATH = "http://localhost:8181/iManageServer/rest/Work";

	/**
	 * Sends a POST request with the values of workrequestbean values to add to DB
	 * 
	 * @param workrequestbean
	 * @return boolean - yes if the request succeeds , false otherwise
	 */
	public boolean addWorkRequest(WorkRequestBean workrequestbean) {
		log.trace("Adding work-request .......");

		WebTarget target = WorkRequest.getWebTarget().path("add");

		boolean workadded;
		try {
			workadded = target.request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(workrequestbean, MediaType.APPLICATION_JSON), Boolean.class);
			if (workadded) {
				log.trace("Work-request added");
			} else {
				log.trace("Work-request not added");
			}

		} catch (NotAuthorizedException e) {
			log.error("The Server rejected the request as authid is incorrect");
			workadded = false;
		}catch (BadRequestException e) {
			log.error("Server ESAPI failed");
			workadded = false;
		}

		return workadded;

	}

	public List<WorkRequestBean> getAllAdmin(String teamName) {
		log.trace("Get all Reuests List");

		WebTarget target = WorkRequest.getWebTarget().path("allreqadmin");

		Form form = new Form();
		form.param("team", teamName);

		List<WorkRequestBean> response;
		try {
			response = target.request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class)
					.readEntity(new GenericType<List<WorkRequestBean>>() {
					});
		} catch (NotAuthorizedException e) {
			log.error("The Server rejected the request as authid is incorrect");
			response = null;
		}catch (BadRequestException e) {
			log.error("Server ESAPI failed");
			response = null;
		}

		log.trace("Response list size " + response.size());
		return response;
	}

	public List<WorkRequestBean> getAllUser(String user) {
		log.trace("Get all Reuests List");

		WebTarget target = WorkRequest.getWebTarget().path("allrequser");
		System.out.println("getAll() " + target.getUri());
		Form form = new Form();
		form.param("user", user);

		List<WorkRequestBean> response;
		try {
			response = target.request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class)
					.readEntity(new GenericType<List<WorkRequestBean>>() {
					});
		} catch (NotAuthorizedException e) {
			log.error("The Server rejected the request as authid is incorrect");
			response = null;
		}catch (BadRequestException e) {
			log.error("Server ESAPI failed");
			response = null;
		}

		log.trace("Response list size " + response.size());
		return response;
	}

	/**
	 * Sends a POST request with workbean with updated values
	 * 
	 * @param workbean
	 * @return boolean - yes if the update succeeds , false otherwise
	 */
	public boolean updateWorkRequest(WorkRequestBean workbean) {
		log.trace("Updating worklist ");
		WebTarget target = WorkRequest.getWebTarget().path("update");
		boolean result;

		try {
			result = target.request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(workbean, MediaType.APPLICATION_JSON), Boolean.class);
		} catch (NotAuthorizedException e) {
			log.error("The Server rejected the request as authid is incorrect");
			result = false;
		}catch (BadRequestException e) {
			log.error("Server ESAPI failed");
			result = false;
		}

		log.trace("updated? " + result);

		return result;
	}

	/**
	 * 
	 * Sends a POST request to delete work request along with its comments to DB
	 * 
	 * @param requestID
	 * @return boolean - yes if the update succeeds , false otherwise
	 */
	public boolean deleteWorkRequest(int requestID) {
		log.trace("Deleting worklist ");

		WebTarget target = WorkRequest.getWebTarget().path("delete");

		Form form = new Form();
		form.param("id", String.valueOf(requestID));
		boolean result;
		try {

			result = target.request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Boolean.class);

		} catch (NotAuthorizedException e) {
			log.error("The Server rejected the request as authid is incorrect");
			result = false;
		}catch (BadRequestException e) {
			log.error("Server ESAPI failed");
			result = false;
		}

		log.trace("updated? " + result);

		return result;
	}

	public static WebTarget getWebTarget() {
		Client client = ClientBuilder.newClient().register(ClientFilter.class);

		WebTarget target = client.target(BASE_PATH);
		return target;
	}

}
