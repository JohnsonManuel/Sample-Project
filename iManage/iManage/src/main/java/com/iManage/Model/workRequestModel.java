package com.iManage.Model;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.PrimeFaces;

import com.iManage.Bean.WorkRequestBean;
import com.iManage.Client.WorkRequest;

@RequestScoped
@ManagedBean(name="workRequestModel" ,eager=true)
public class WorkRequestModel {
	
	@ManagedProperty(value="#{workRequestBean}")
	private WorkRequestBean workrequestBean;
	
	private WorkRequestBean selectedworkRequest;
	private List<WorkRequestBean> inProgressWorksList;
	private List<WorkRequestBean> allWorksList;
	private List<WorkRequestBean> completedWorksList;

	
	@PostConstruct
	public void init() {
		updateTables();
	}
	
	public WorkRequestModel() {

	}
	
	public WorkRequestBean getWorkrequestBean() {
		return workrequestBean;
	}

	public void setWorkrequestBean(WorkRequestBean workrequestBean) {
		this.workrequestBean = workrequestBean;
	}
	

	
	public void updateList() {
		System.out.println(selectedworkRequest.getName());
		System.out.println(selectedworkRequest.getRequestType());
		System.out.println(selectedworkRequest.getDescription());
		System.out.println(selectedworkRequest.getStatus());

		// need to work on
	}
	
	
	public void addWorkRequest() {
		System.out.println(" Came here");
		System.out.println(workrequestBean.getName());
		WorkRequest objj = new WorkRequest();
		System.out.println(workrequestBean.getName());
		objj.addWorkRequest(workrequestBean.getName(),workrequestBean.getRequestType(),workrequestBean.getDescription(),"In Progress");
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
		inProgressWorksList = obj.getInProgress();
		completedWorksList = obj.getCompleted();
	}	
	

	public WorkRequestBean getSelectedworkRequest() {
		System.out.println(" Selected bean get");
		return selectedworkRequest;
	}
	
	public void setSelectedworkRequest(WorkRequestBean selectedworkRequest) {
		System.out.println(" Selected bean set");
		this.selectedworkRequest = selectedworkRequest;
	}

	public List<WorkRequestBean> getInProgressWorksList() {
		return inProgressWorksList;
	}

	public void setInProgressWorksList(List<WorkRequestBean> inProgressWorkList) {
		this.inProgressWorksList = inProgressWorkList;
	}

	public List<WorkRequestBean> getAllWorksList() {
		return allWorksList;
	}

	public void setAllWorksList(List<WorkRequestBean> allWorkList) {
		this.allWorksList = allWorkList;
	}

	public List<WorkRequestBean> getCompletedWorksList() {
		return completedWorksList;
	}

	public void setCompletedWorksList(List<WorkRequestBean> completedWorkList) {
		this.completedWorksList = completedWorkList;
	}
	
	
}
