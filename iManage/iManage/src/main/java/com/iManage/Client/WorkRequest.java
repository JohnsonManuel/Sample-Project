package com.iManage.Client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.iManage.Bean.WorkRequestBean;

public class WorkRequest {


	// http://localhost:8181/iManageServer/rest/Work/allreq
		public List<WorkRequestBean> getAll() {
		 Client client = ClientBuilder.newClient();
		 WebTarget target = client.target("http://localhost:8181").path("iManageServer").path("rest").path("Work").path("allreq");
		 
		 System.out.println("getAll() "+target.getUri());
		 
		 List<WorkRequestBean> response = target.request(MediaType.APPLICATION_JSON).get(Response.class).readEntity(new GenericType<List<WorkRequestBean>>(){}) ;
		 return response;
	}
		public List<WorkRequestBean> getInProgress() {
			 Client client = ClientBuilder.newClient();
			 WebTarget target = client.target("http://localhost:8181").path("iManageServer").path("rest").path("Work").path("inprogressreq");
			 
			 System.out.println("getAll() "+target.getUri());
			 
			 List<WorkRequestBean> response = target.request(MediaType.APPLICATION_JSON).get(Response.class).readEntity(new GenericType<List<WorkRequestBean>>(){}) ;
			 return response;
		}
		public List<WorkRequestBean> getCompleted() {
			 Client client = ClientBuilder.newClient();
			 WebTarget target = client.target("http://localhost:8181").path("iManageServer").path("rest").path("Work").path("completedreq");
			 
			 System.out.println("getAll() "+target.getUri());
			 
			 List<WorkRequestBean> response = target.request(MediaType.APPLICATION_JSON).get(Response.class).readEntity(new GenericType<List<WorkRequestBean>>(){}) ;
			 return response;
		}
		
}
