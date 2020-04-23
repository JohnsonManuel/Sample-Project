package com.iManage.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.Validator;
import org.primefaces.PrimeFaces;

import com.iManage.Bean.CommentsBean;
import com.iManage.Bean.WorkRequestBean;
import com.iManage.Client.WorkRequest;

@ViewScoped
@ManagedBean(name = "workRequestModel", eager = true)
public class WorkRequestModel {

	private static final Logger log = LogManager.getLogger("mainLogger");

	@ManagedProperty(value = "#{workRequestBean}")
	private WorkRequestBean workrequestBean;

	private String currentUser;
	private String userType;
	private String team;

	private boolean renderComment;
	private WorkRequestBean selectedworkRequest;
	private List<WorkRequestBean> allWorksList;
	private List<WorkRequestBean> completedWorksList;
	private List<WorkRequestBean> inProgressWorksList;
	private List<WorkRequestBean> openProgressWorksList;

	private CommentsBean selectedcommentsBean;

	private String comment;

	Encoder encoder = ESAPI.encoder();
	Validator validator = ESAPI.validator();

	@PostConstruct
	public void init() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		currentUser = (String) sessionMap.get("user");
		userType = (String) sessionMap.get("user-type");
		team = (String) sessionMap.get("team");
		updateTables();
	}

	public WorkRequestModel() {
		selectedworkRequest = new WorkRequestBean();

	}

	public void updateTables() {
		WorkRequest obj = new WorkRequest();

		if (userType.equals("admin")) {
			allWorksList = obj.getAllAdmin(team);
		} else {
			allWorksList = obj.getAllUser(currentUser);
		}

		inProgressWorksList = updateinProgressWorksList();
		completedWorksList = updatecompletedWorksList();
		openProgressWorksList = updateopenProgressWorksList();

	}

	public void deleteWorkRequest(WorkRequestBean delWork) {

		FacesContext context = FacesContext.getCurrentInstance();
		WorkRequest objj = new WorkRequest();
		boolean updated = objj.deleteWorkRequest(delWork.getRequestID());
		updateTables();

		if (updated) {
			log.trace("Work request of id " + delWork.getRequestID() + " has been deleted");
			allWorksList.remove(delWork);
			context.addMessage(null, new FacesMessage("Work request deleted"));

		} else {
			log.trace("Work request of id " + delWork.getRequestID() + " wasn't deleted");
			context.addMessage(null, new FacesMessage("Delete failed"));
		}
	}

	public void updateList() {

		WorkRequest objj = new WorkRequest();
		FacesContext context = FacesContext.getCurrentInstance();

		boolean updated;
		boolean check1, check2;

		check1 = validator.isValidInput("summary", encoder.canonicalize(selectedworkRequest.getName()), "Special", 1024,
				false);
		check2 = validator.isValidInput("summary", encoder.canonicalize(selectedworkRequest.getComment()), "Special",
				1024, true);

		if (check1 && check2) {
			updated = objj.updateWorkRequest(selectedworkRequest);
		} else {
			updated = false;
		}

		updateTables();
		renderComment = false;
		if (updated) {
			log.trace("Work request of id " + selectedworkRequest.getRequestID() + " has been added");
			context.addMessage(null, new FacesMessage("Work request updated"));

		} else {
			if (check1 || check2) {
				System.out.println(check1 + " " + check2);
				log.trace(check1 + " " + check2 + " " + "Malicious input detected");
				context.addMessage(null, new FacesMessage("Malicious input "));

			} else {
				log.trace("Work request of id " + selectedworkRequest.getRequestID() + " hasn't  been added");

				context.addMessage(null, new FacesMessage("update failed "));

			}
		}
	}

	public void addWorkRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		WorkRequest objj = new WorkRequest();

		boolean response;
		boolean check1, check2;

		workrequestBean.setRequestedBy(currentUser);
		workrequestBean.setStatus("Open");

		check1 = validator.isValidInput("summary", encoder.canonicalize(workrequestBean.getName()), "Special", 1024,
				false);
		check2 = validator.isValidInput("description", encoder.canonicalize(workrequestBean.getDescription()),
				"Special", 1024, false);

		if (check1 && check2) {

			response = objj.addWorkRequest(workrequestBean);
		} else {
			response = false;
		}

		if (response) {
			log.trace("Work request has been added");
			workrequestBean.setName("");
			workrequestBean.setDescription("");
			workrequestBean.setRequestType("");
			workrequestBean.setStatus("");

			context.addMessage(null, new FacesMessage("Work request added"));

			updateTables();
			PrimeFaces.current().executeScript("PF('dlg2').hide();");
		} else {
			if ((check1 && check2)) {
				log.trace(check1 + " " + check2 + " " + "Malicious input detected");

				context.addMessage(null, new FacesMessage("Work request add error"));
			} else {
				log.trace("Work request hasn't  been added");
				context.addMessage(null, new FacesMessage("Remove special charcters and Try again !"));

			}
		}
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

	// Getters and Setteres
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

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	public WorkRequestBean getWorkrequestBean() {
		return workrequestBean;
	}

	public void setWorkrequestBean(WorkRequestBean workrequestBean) {
		this.workrequestBean = workrequestBean;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

}
