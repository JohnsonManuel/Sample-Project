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

import com.iManage.Bean.CommentsBean;
import com.iManage.Bean.WorkRequestBean;
import com.iManage.Client.WorkRequest;

@ViewScoped
@ManagedBean(name = "workRequestModel", eager = true)
public class WorkRequestModel {

	@ManagedProperty(value = "#{workRequestBean}")
	private WorkRequestBean workrequestBean;
	
	@ManagedProperty(value = "#{commentsBean}")
	private CommentsBean commentsBean;


	
	private boolean renderComment;
	private WorkRequestBean selectedworkRequest;
	private List<WorkRequestBean> allWorksList;
	private List<WorkRequestBean> completedWorksList;
	private List<WorkRequestBean> inProgressWorksList;
	private List<WorkRequestBean> openProgressWorksList;
	private List<CommentsBean> comments;
	
	private CommentsBean selectedcommentsBean;
	
	private String comment;
	//private List<String> comments;

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

	public void deleteWorkRequest(WorkRequestBean delWork) {
		
		System.out.println(delWork.getRequestID());
		FacesContext context = FacesContext.getCurrentInstance();
		WorkRequest objj = new WorkRequest();
		boolean updated = objj.deleteWorkRequest(delWork.getRequestID());

		if (updated) {
			allWorksList.remove(delWork);
			context.addMessage(null, new FacesMessage("Work request deleted"));

		} else {
			context.addMessage(null, new FacesMessage("update failed"));
		}
	}

	
	public void updateList() {

		//PrimeFaces.current().executeScript("toggleSubMenu();");
		WorkRequest objj = new WorkRequest();
		FacesContext context = FacesContext.getCurrentInstance();
		boolean updated = objj.updateWorkRequest(selectedworkRequest.getRequestID(), selectedworkRequest.getName(),
				selectedworkRequest.getDescription(), selectedworkRequest.getStatus(),
				selectedworkRequest.getComment());
		updateTables();
		if (updated) {
			context.addMessage(null, new FacesMessage("Work request updated"));

		} else {
			context.addMessage(null, new FacesMessage("update failed"));
		}
	}

	public void addWorkRequest() {
		System.out.println(" Came here");
		System.out.println(workrequestBean.getName());
		WorkRequest objj = new WorkRequest();
		System.out.println(workrequestBean.getName());
		objj.addWorkRequest(workrequestBean.getName(), workrequestBean.getRequestType(),
				workrequestBean.getDescription(), workrequestBean.getStatus());
		workrequestBean.setName("");
		workrequestBean.setDescription("");
		workrequestBean.setRequestType("");
		workrequestBean.setStatus("");
		updateTables();
		PrimeFaces.current().executeScript("PF('dlg2').hide();");

	}

	public void updateTables() {
		WorkRequest obj = new WorkRequest();
		allWorksList = obj.getAll();
		inProgressWorksList = updateinProgressWorksList();
		completedWorksList = updatecompletedWorksList();
		openProgressWorksList = updateopenProgressWorksList();
		

	}
	
	public List<WorkRequestBean> updatecompletedWorksList() {
		List<WorkRequestBean> filteredList = new ArrayList<WorkRequestBean>();
		for (WorkRequestBean temp : allWorksList) {
			if (temp.getStatus().equals("Completed")) {
				filteredList.add(temp);
			}
		}
		return filteredList;
	}

	public List<WorkRequestBean> updateinProgressWorksList() {
		List<WorkRequestBean> filteredList = new ArrayList<WorkRequestBean>();
		for (WorkRequestBean temp : allWorksList) {
			if (temp.getStatus().equals("In Progress")) {
				filteredList.add(temp);
			}
		}
		return filteredList;
	}

	public List<WorkRequestBean> updateopenProgressWorksList() {
		List<WorkRequestBean> filteredList = new ArrayList<WorkRequestBean>();
		for (WorkRequestBean temp : allWorksList) {
			if (temp.getStatus().equals("Open")) {
				filteredList.add(temp);
			}
		}
		return filteredList;
	}

	public void handleStatusChange() {
		if (selectedworkRequest.getStatus().equals("Completed")) {
			renderComment = true;
		} else {
			renderComment = false;
		}
	}
	
	
	public List<CommentsBean> request_comments(int key){	
		WorkRequest objj = new WorkRequest();
		return objj.getComments(key);
		
		
	}
	
	public void addComment() {
		FacesContext context = FacesContext.getCurrentInstance();
		WorkRequest objj = new WorkRequest();
		System.out.println(selectedworkRequest.getRequestID()+" "+commentsBean.getComment());
		boolean updated = objj.addComment(selectedworkRequest.getRequestID(),commentsBean.getComment());
		System.out.println(updated);
		if (updated) {
			context.addMessage(null, new FacesMessage("Work request updated"));
			commentsBean.setComment("");
		} else {
			context.addMessage(null, new FacesMessage("Delete failed"));
		}
	}
	
	
	
	
	
	//Getters and Setteres
	
	
	
	
	

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

	public boolean isRenderComment() {
		return renderComment;
	}

	public void setRenderComment(boolean renderComment) {
		this.renderComment = renderComment;
	}

	public List<WorkRequestBean> getCompletedWorksList() {
		return completedWorksList;
	}

	public void setCompletedWorksList(List<WorkRequestBean> completedWorksList) {
		this.completedWorksList = completedWorksList;
	}

	public List<WorkRequestBean> getInProgressWorksList() {
		return inProgressWorksList;
	}

	public void setInProgressWorksList(List<WorkRequestBean> inProgressWorksList) {
		this.inProgressWorksList = inProgressWorksList;
	}

	public List<WorkRequestBean> getOpenProgressWorksList() {
		return openProgressWorksList;
	}

	public void setOpenProgressWorksList(List<WorkRequestBean> openProgressWorksList) {
		this.openProgressWorksList = openProgressWorksList;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public CommentsBean getSelectedcommentsBean() {
		return selectedcommentsBean;
	}

	public void setSelectedcommentsBean(CommentsBean selectedcommentsBean) {
		this.selectedcommentsBean = selectedcommentsBean;
	}

	public CommentsBean getCommentsBean() {
		return commentsBean;
	}

	public void setCommentsBean(CommentsBean commentsBean) {
		this.commentsBean = commentsBean;
	}

	public List<CommentsBean> getComments() {
		return comments;
	}

	public void setComments(List<CommentsBean> comments) {
		this.comments = comments;
	}
	
	


//	public List<String> getComments() {
//		return comments;
//	}
//
//	public void setComments(List<String> comments) {
//		this.comments = comments;
//	}

}
