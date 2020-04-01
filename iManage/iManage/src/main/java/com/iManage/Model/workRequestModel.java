package com.iManage.Model;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.iManage.Bean.WorkRequestBean;
import com.iManage.Client.WorkRequest;
@ViewScoped
@ManagedBean(name="workRequestModel" ,eager=true)
public class WorkRequestModel {
	
	@ManagedProperty(value="#{workrequestBean}")
	private WorkRequestBean workrequestBean;
	private WorkRequestBean selectedworkRequest;
	private List<WorkRequestBean> inProgressWorksList;
	private List<WorkRequestBean> allWorksList;
	private List<WorkRequestBean> completedWorksList;

	
	@PostConstruct
	public void init() {
		WorkRequest obj = new WorkRequest();
		allWorksList = obj.getAll();
		inProgressWorksList = obj.getInProgress();
		completedWorksList = obj.getCompleted();
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
		// need to work on
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
