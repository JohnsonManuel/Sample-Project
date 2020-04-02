package com.iManage.Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name="workRequestBean",eager=true)
public class WorkRequestBean {
	
	private String name;
	private String requestType;
	private String description;
	private String status;
	
	
	public WorkRequestBean() {
	}
	

	public WorkRequestBean(String name, String requestType, String description, String status) {
		this.name = name;
		this.requestType = requestType;
		this.description = description;
		this.status = status;
	}
	
	


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getDescription() {
		return description;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	


}
