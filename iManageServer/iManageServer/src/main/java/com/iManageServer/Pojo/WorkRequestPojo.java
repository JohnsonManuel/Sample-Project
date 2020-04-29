package com.iManageServer.Pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "WorkRequestBean")
public class WorkRequestPojo {

	private String requester;
	private String requesterTeam;
	private int requestID;
	private String name;
	private String requestType;
	private String description;
	private String status;
	private String comment;
	private String team;

	public WorkRequestPojo() {

	}

	public WorkRequestPojo(int requestID, String name, String requester, String requestType, String description,
			String status, String comment, String team ,String requesterTeam) {
		super();
		this.name = name;
		this.requestType = requestType;
		this.description = description;
		this.status = status;
		this.requestID = requestID;
		this.comment = comment;
		this.requester = requester;
		this.team = team;
		this.requesterTeam = requesterTeam;
	}

	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "requestType")
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	@XmlElement(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@XmlElement(name = "requestID")
	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	@XmlElement(name = "comment")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
	@XmlElement(name = "requester")
	public String getRequester() {
		return requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}
	
	@XmlElement(name = "team")
	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	@XmlElement(name = "requesterTeam")
	public String getRequesterTeam() {
		return requesterTeam;
	}

	public void setRequesterTeam(String requesterTeam) {
		this.requesterTeam = requesterTeam;
	}

}
