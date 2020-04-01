package com.iManage.Model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.iManage.Bean.workRequestBean;
@ViewScoped
@ManagedBean(name="workRequestModel" ,eager=true)
public class workRequestModel {
	
	@ManagedProperty(value="#{workrequestBean}")
	private workRequestBean workrequestBean;
	
	private workRequestBean selectedworkRequest;
	
	
	
	
	public workRequestModel() {
	
		selectedworkRequest = new workRequestBean();
		
	}
	
	public workRequestBean getWorkrequestBean() {
		return workrequestBean;
	}

	public void setWorkrequestBean(workRequestBean workrequestBean) {
		this.workrequestBean = workrequestBean;
	}
	
	
	public List<workRequestBean>  getInprogressList(){
		
		List<workRequestBean> temp = new ArrayList<workRequestBean>();

		temp.add(new workRequestBean("Doe", "General", "THIS is not a drill HELP ME", "In Progress"));
		temp.add(new workRequestBean("Doe", "General", "THIS is not a drill HELP ME", "In Progress"));
		temp.add(new workRequestBean("Doe", "General", "THIS is not a drill HELP ME", "In Progress"));
		temp.add(new workRequestBean("Doe", "General", "THIS is not a drill HELP ME", "In Progress"));
		

		return temp;
	}
	
	public static List<workRequestBean> loadWorkRequests() {
		
		List<workRequestBean> temp = new ArrayList<workRequestBean>();
		
		temp.add(new workRequestBean("JOhn", "IT","THIS IS A DESCRIPTION", "In Progress"));
		temp.add(new workRequestBean("JOhn", "IT","THIS IS A DESCRIPTION", "General"));
		temp.add(new workRequestBean("JOhn", "IT","THIS IS A DESCRIPTION", "General"));
		temp.add(new workRequestBean("JOhn", "IT","THIS IS A DESCRIPTION", "In Progress"));
		
		return temp;
		
	}
	public List<workRequestBean> getCompletedList()
	{
		List<workRequestBean> temp = new ArrayList<workRequestBean>();
		
		temp.add(new workRequestBean("JOE", "General","THIS IS A LONG LONG LONG LONG DESCRIPTIONTHIS IS A LONG LONG LONG LONG DESCRIPTION", "Completed"));
		temp.add(new workRequestBean("JOE", "General","THIS IS A LONG LONG LONG LONG DESCRIPTIONTHIS IS A LONG LONG LONG LONG DESCRIPTION", "Completed"));
		temp.add(new workRequestBean("JOE", "General","THIS IS A LONG LONG LONG LONG DESCRIPTIONTHIS IS A LONG LONG LONG LONG DESCRIPTION", "Completed"));
		temp.add(new workRequestBean("JOE", "General","THIS IS A LONG LONG LONG LONG DESCRIPTIONTHIS IS A LONG LONG LONG LONG DESCRIPTION", "Completed"));
		
		return temp;
	}
	
	public void updateList() {
		
	}
	
	

	public workRequestBean getSelectedworkRequest() {
		return selectedworkRequest;
	}

	public void setSelectedworkRequest(workRequestBean selectedworkRequest) {
		this.selectedworkRequest = selectedworkRequest;
	}
	
	
	
}
