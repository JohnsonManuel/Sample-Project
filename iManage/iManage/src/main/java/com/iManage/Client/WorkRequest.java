package com.iManage.Client;

import java.util.List;

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

public class WorkRequest {
	private static final Logger log = LogManager.getLogger(WorkRequest.class);	

		
		
	
		public void addWorkRequest(String name, String type, String description, String status) {
			 log.trace("Adding work-request .......");
			 Client client = ClientBuilder.newClient();
			 WebTarget target = client.target("http://localhost:8181").path("iManageServer").path("rest").path("Work").path("add");
			 Form form = new Form();
			 form.param("name",name);
			 form.param("type", type);
			 form.param("description", description);
			 form.param("status", status);
			 boolean workadded= target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),Boolean.class);
			 if(workadded) { log.trace("Work-request added");}else{log.trace("Work-request not added");}
		}


	// http://localhost:8181/iManageServer/rest/Work/allreq
		public List<WorkRequestBean> getAll() {
		 log.trace("Get all Reuests List");
		 
		 Client client = ClientBuilder.newClient();
		 WebTarget target = client.target("http://localhost:8181").path("iManageServer").path("rest").path("Work").path("allreq");
		 System.out.println("getAll() "+target.getUri());
		 List<WorkRequestBean> response = target.request(MediaType.APPLICATION_JSON).get(Response.class).readEntity(new GenericType<List<WorkRequestBean>>(){}) ;
		 log.trace("Response list size "+response.size());
		 return response;
		}
		
		


		public boolean  updateWorkRequest(int id ,String name, String description, String status, String comment) {
			log.trace("Updating worklist ");

			 Client client = ClientBuilder.newClient();
			 WebTarget target = client.target("http://localhost:8181").path("iManageServer").path("rest").path("Work").path("update");
			 
			 System.out.println("getAll() "+target.getUri());
			 Form form = new Form();
			 form.param("id", String.valueOf(id));
			 form.param("name",name);
			 form.param("description", description);
			 form.param("status", status);
			 form.param("comment", comment);
			 boolean result= target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),Boolean.class);
			 log.trace("updated? "+result);

			 return result;
		}


		public boolean deleteWorkRequest(int requestID) {
			log.trace("Deleting worklist ");

			 Client client = ClientBuilder.newClient();
			 WebTarget target = client.target("http://localhost:8181").path("iManageServer").path("rest").path("Work").path("delete");
			 
			 System.out.println("getAll() "+target.getUri());
			 Form form = new Form();
			 form.param("id", String.valueOf(requestID));
			 boolean result= target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),Boolean.class);
			 log.trace("updated? "+result);

			 return result;
		}


		
		
}
