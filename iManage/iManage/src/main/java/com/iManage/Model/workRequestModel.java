package com.iManage.Model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.iManage.Bean.WorkRequestBean;
import com.iManage.Client.WorkRequest;

@ViewScoped
@ManagedBean(name="workRequestModel" ,eager=true)
public class WorkRequestModel {
	
	@ManagedProperty(value="#{workRequestBean}")
	private WorkRequestBean workrequestBean;
	private boolean renderComment;
	private WorkRequestBean selectedworkRequest;
	private List<WorkRequestBean> allWorksList;

	
	@PostConstruct
	public void init() {
		updateTables();
	}
	
	
	
	public WorkRequestModel() {
		selectedworkRequest = new WorkRequestBean();

	}
	
	public WorkRequestBean getWorkrequestBean() {
		return workrequestBean;
	}

	public void setWorkrequestBean(WorkRequestBean workrequestBean) {
		this.workrequestBean = workrequestBean;
	}
	

	public void deleteWorkRequest( WorkRequestBean delWork) {
		System.out.println(delWork.getRequestID());
		allWorksList.remove(delWork);
		
		
	}
	public void updateList() {
		
		PrimeFaces.current().executeScript("toggleSubMenu('inp');");
		System.out.println("EVENT FIRED    __________________________________________________________--");
		WorkRequest objj = new WorkRequest();
		FacesContext context = FacesContext.getCurrentInstance();
		boolean updated = objj.updateWorkRequest( selectedworkRequest.getRequestID(), selectedworkRequest.getName(),selectedworkRequest.getDescription(),selectedworkRequest.getStatus(),selectedworkRequest.getComment() );
		updateTables();
		if(updated) {
	        context.addMessage(null, new FacesMessage("Work request updated"));
		}
		else {
			 context.addMessage(null, new FacesMessage("update failed"));
		}
	}
	
	
	public void addWorkRequest() {
		System.out.println(" Came here");
		System.out.println(workrequestBean.getName());
		WorkRequest objj = new WorkRequest();
		System.out.println(workrequestBean.getName());
		objj.addWorkRequest(workrequestBean.getName(),workrequestBean.getRequestType(),workrequestBean.getDescription(),workrequestBean.getStatus());
		workrequestBean.setName("");
		workrequestBean.setDescription("");
		workrequestBean.setRequestType("");
		workrequestBean.setStatus("");
		updateTables();
		PrimeFaces.current().executeScript("PF('dlg2').hide();");

	}
	public void updateTables()
	{
		WorkRequest obj = new WorkRequest();
		allWorksList = obj.getAll();

	}	
	

	public WorkRequestBean getSelectedworkRequest() {
		return selectedworkRequest;
	}
	
	public void setSelectedworkRequest(WorkRequestBean selectedworkRequest) {
		this.selectedworkRequest = selectedworkRequest;
	}


	public List<WorkRequestBean> getAllWorksList() {
		return allWorksList;
	}

	public void setAllWorksList(List<WorkRequestBean> allWorkList) {
		this.allWorksList = allWorkList;
	}

	public List<WorkRequestBean> completedWorksList(){
		List<WorkRequestBean> filteredList = new ArrayList<WorkRequestBean>();
		for(WorkRequestBean temp : allWorksList) {
			if(temp.getStatus().equals("Completed")) {
				filteredList.add(temp);
			}
		}
		return filteredList;
	}
	
	public List<WorkRequestBean> inProgressWorksList(){
		List<WorkRequestBean> filteredList = new ArrayList<WorkRequestBean>();
		for(WorkRequestBean temp : allWorksList) {
			if(temp.getStatus().equals("In Progress")) {
				filteredList.add(temp);
			}
		}
		return filteredList;
	}
	public List<WorkRequestBean> openProgressWorksList(){
		List<WorkRequestBean> filteredList = new ArrayList<WorkRequestBean>();
		for(WorkRequestBean temp : allWorksList) {
			if(temp.getStatus().equals("Open")) {
				filteredList.add(temp);
			}
		}
		return filteredList;
	}
	
	public void handleStatusChange() {
		if(selectedworkRequest.getStatus().equals("Completed")) {
			renderComment= true;
		}else {
			renderComment= false;
		}
	}
	

	public boolean isRenderComment() {
		return renderComment;
	}

	public void setRenderComment(boolean renderComment) {
		this.renderComment = renderComment;
	}
	
	

}
