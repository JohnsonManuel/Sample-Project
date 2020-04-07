package com.iManageServer.Pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="WorkRequestBean")
public class WorkRequestPojo {

	private int requestID;
	private String name;
	private String requestType;
	private String description;
	private String status;
	private String comment;
	
	public WorkRequestPojo() {
		
	}
	
	public WorkRequestPojo(int requestID,String name, String requestType, String description, String status,String comment) {
		super();
		this.name = name;
		this.requestType = requestType;
		this.description = description;
		this.status = status;
		this.requestID = requestID;
		this.comment= comment;
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
	@XmlElement(name ="requestID")
	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
