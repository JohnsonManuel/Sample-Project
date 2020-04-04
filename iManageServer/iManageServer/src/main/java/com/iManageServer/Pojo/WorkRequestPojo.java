package com.iManageServer.Pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="WorkRequestBean")
public class WorkRequestPojo {

	
	private String name;
	private String requestType;
	private String description;
	private String status;
	
	public WorkRequestPojo() {
		
	}
	
	public WorkRequestPojo(String name, String requestType, String description, String status) {
		super();
		this.name = name;
		this.requestType = requestType;
		this.description = description;
		this.status = status;
	}
	@XmlElement(name ="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name ="requestType")
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	@XmlElement(name ="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@XmlElement(name ="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
