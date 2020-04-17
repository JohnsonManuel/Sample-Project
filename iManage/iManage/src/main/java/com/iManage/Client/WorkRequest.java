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

import com.iManage.Bean.CommentsBean;
import com.iManage.Bean.WorkRequestBean;

public class WorkRequest {
	private static final Logger log = LogManager.getLogger("mainLogger");	

		
		
	
		public boolean addWorkRequest(String currentUser,String name, String type, String description, String status,String team) {
			 log.trace("Adding work-request .......");
			 Client client = ClientBuilder.newClient();
			 WebTarget target = client.target("http://localhost:8181").path("iManageServer").path("rest").path("Work").path("add");
			 Form form = new Form();
			 form.param("assignedBy", currentUser);
			 form.param("name",name);
			 form.param("type", type);
			 form.param("description", description);
			 form.param("status", status);
			 form.param("team", team);
			 boolean workadded= target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),Boolean.class);
			 if(workadded) { log.trace("Work-request added");}else{log.trace("Work-request not added");}
			 return workadded;
			
		}


	// http://localhost:8181/iManageServer/rest/Work/allreq
		public List<WorkRequestBean> getAllAdmin(String teamName) {
			 log.trace("Get all Reuests List");
			 
			 Client client = ClientBuilder.newClient();
			 WebTarget target = client.target("http://localhost:8181").path("iManageServer").path("rest").path("Work").path("allreqadmin");
			 System.out.println("getAll() "+target.getUri());
			 Form form = new Form();
			 form.param("team", teamName);
			 List<WorkRequestBean> response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),Response.class).readEntity(new GenericType<List<WorkRequestBean>>(){}) ;
			 log.trace("Response list size "+response.size());
			 return response;
			}
		public List<WorkRequestBean> getAllUser(String user) {
		 log.trace("Get all Reuests List");
		 
		 Client client = ClientBuilder.newClient();
		 WebTarget target = client.target("http://localhost:8181").path("iManageServer").path("rest").path("Work").path("allrequser");
		 System.out.println("getAll() "+target.getUri());
		 Form form = new Form();
		 form.param("user", user);

		 List<WorkRequestBean> response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),Response.class).readEntity(new GenericType<List<WorkRequestBean>>(){}) ;
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


		public boolean addComment(int id, String comment,String time) {
			
			 Client client = ClientBuilder.newClient();
			 WebTarget target = client.target("http://localhost:8181").path("iManageServer").path("rest").path("comment").path("add");
			 
			 System.out.println("getAll() "+target.getUri());
			 Form form = new Form();
			 form.param("id", String.valueOf(id));
			 form.param("comment", comment);
			 form.param("time",time);
			 boolean result= target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),Boolean.class);
			 log.trace("updated? "+result);

			 return result;
			
		}


		public List<CommentsBean> getComments(int key) {


			 log.trace("Get all comments of "+ key);
			 
			 Client client = ClientBuilder.newClient();
			 WebTarget target = client.target("http://localhost:8181").path("iManageServer").path("rest").path("comment").path("getcomments");
			 System.out.println("getAll() "+target.getUri());
			 Form form = new Form();
			 form.param("id", String.valueOf(key));
			 List<CommentsBean> response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),Response.class).readEntity(new GenericType<List<CommentsBean>>(){}) ;
			 log.trace("Response list size "+response.size());
			 return response;
			
			
			
		}


		public boolean deleteComments(int id) {
			
			log.trace("Delete Comment of id "+ id);
			 
			 Client client = ClientBuilder.newClient();
			 WebTarget target = client.target("http://localhost:8181").path("iManageServer").path("rest").path("comment").path("delete");
			 System.out.println("getAll() "+target.getUri());
			 Form form = new Form();
			 form.param("id", String.valueOf(id));
			 boolean result= target.request(MediaType.APPLICATION_JSON).post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),Boolean.class);
			
			  
			
			return result;
		}


	


		
		
}
