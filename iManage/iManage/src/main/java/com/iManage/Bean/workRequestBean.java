package com.iManage.Bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;

import com.iManage.Model.workRequestModel;

@ViewScoped
@ManagedBean(name="workRequestBean",eager=true)
public class workRequestBean {
	
	private String name;
	private String requestType;
	private String description;
	private String status;
	private List<workRequestBean> workrequestList;
	
	
	
	@PostConstruct
	public void init() {
		System.out.println("INIT CALLED");
		this.setWorkrequestList(workRequestModel.loadWorkRequests());
	}
	
	
	
	public workRequestBean() {
		workrequestList = new ArrayList<workRequestBean>(); 
	}
	
	
	
	public workRequestBean(String name, String requestType, String description, String status) {
		this.name = name;
		this.requestType = requestType;
		this.description = description;
		this.status = status;
	}
	
	public void addWorkRequest() {
		workrequestList.add(new workRequestBean(name,requestType,description,"In Progress")	);	
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
	public List<workRequestBean> getWorkrequestList() {
		return workrequestList;
	}
	public void setWorkrequestList(List<workRequestBean> workrequestList) {
		this.workrequestList = workrequestList;
	}
	
	
	

}
