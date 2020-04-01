package com.iManage.Bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;

@ViewScoped
@ManagedBean(name="workRequestBean",eager=true)
public class WorkRequestBean {
	
	private String name;
	private String requestType;
	private String description;
	private String status;
	
	private List<WorkRequestBean> workrequestList;
	

	
	public WorkRequestBean() {
		workrequestList = new ArrayList<WorkRequestBean>(); 
	}
	
	
	
	public WorkRequestBean(String name, String requestType, String description, String status) {
		this.name = name;
		this.requestType = requestType;
		this.description = description;
		this.status = status;
	}
	
	public void addWorkRequest() {
		workrequestList.add(new WorkRequestBean(name,requestType,description,"In Progress")	);	
		this.name="";
		this.setDescription("");
		PrimeFaces.current().executeScript("PF('dlg2').hide();");
		System.out.println(workrequestList.size());
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
	public List<WorkRequestBean> getWorkrequestList() {
		return workrequestList;
	}
	public void setWorkrequestList(List<WorkRequestBean> workrequestList) {
		this.workrequestList = workrequestList;
	}
	


}
