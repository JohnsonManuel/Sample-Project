package com.iManage.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.iManage.Bean.CommentsBean;
import com.iManage.Client.Comments;

@ViewScoped
@ManagedBean(name = "commentsModel", eager = true)
public class CommentsModel {

	private static final Logger log = LogManager.getLogger("mainLogger");

	@ManagedProperty(value = "#{commentsBean}")
	private CommentsBean commentsBean;
	@ManagedProperty(value = "#{workRequestModel}")
	private WorkRequestModel workRequestModel;

	private List<CommentsBean> comments;
	private boolean renderCommentPanel = false;

	Comments objj = new Comments();

	Encoder encoder = ESAPI.encoder();
	Validator validator = ESAPI.validator();
	
	/**
	 * Adds the comment to the specified Work request using the work requests ID.
	 * @param workID - The Id of the work-request the comment belongs to.
	 */
	public void addComment(int workID) {
		FacesContext context = FacesContext.getCurrentInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		boolean validinput = validator.isValidInput("comments", encoder.canonicalize(commentsBean.getComment()), "Special", 1024,
				false);
		
		boolean updated ;
		
		if(validinput) {
			updated = objj.addComment(workID, commentsBean.getComment(), dateFormat.format(date));
		}else {
			updated= false;
		}

		if (updated) {

			log.trace("Comment has been added");

			toggleCommentPanel();
			context.addMessage(null, new FacesMessage("Comment added"));
			commentsBean.setComment("");

		} else {
			log.trace("Comment hasn't been added");

			if(validinput) 
				context.addMessage(null, new FacesMessage("Unable to add comment"));
			else 
				context.addMessage(null, new FacesMessage("Remove special characters."));

			
			
		}
	}

	
	
	/**
	 * Deletes the comment using the ID of the Comment
	 * @param id - unique id of Comment
	 * 
	 */
	public void deleteComment(int id) {
		FacesContext context = FacesContext.getCurrentInstance();

		boolean updated = objj.deleteComments(id);
		
		
		if (updated) {
			log.trace("Comment has been deleted");

			context.addMessage(null, new FacesMessage("Comment Deleted"));
			commentsBean.setComment("");
		} else {
			log.trace("Comment hasn't been deleted");

			context.addMessage(null, new FacesMessage("Delete failed"));
		}
	}

	/**
	 * Method loads the comments of the selected work request
	 * @param workrequestId - Id of the work-request
	 */
	public void getcomments(int workId) {
		comments = objj.getComments(workId);
	}

	
	
	/**
	 * Toggles the visibility of CommentsPanel
	 */
	public void toggleCommentPanel() {
		commentsBean.setComment("");
		renderCommentPanel = !renderCommentPanel;

	}

	/**
	 * Gets all Comments of the selected Work Request
	 * @param workId - Unique id of Work Request
	 * @return List<CommentsBean> - list of comments
	 */
	public List<CommentsBean> request_comments(int workId) {
		return objj.getComments(workId);

	}

	// Getters and Setters

	public CommentsBean getCommentsBean() {
		return commentsBean;
	}

	public void setCommentsBean(CommentsBean commentsBean) {
		this.commentsBean = commentsBean;
	}

	public boolean isRenderCommentPanel() {
		return renderCommentPanel;
	}

	public void setRenderCommentPanel(boolean renderCommentPanel) {
		this.renderCommentPanel = renderCommentPanel;
	}

	public WorkRequestModel getWorkRequestModel() {
		return workRequestModel;
	}

	public void setWorkRequestModel(WorkRequestModel workRequestModel) {
		this.workRequestModel = workRequestModel;
	}

	public List<CommentsBean> getComments() {
		return comments;
	}

	public void setComments(List<CommentsBean> comments) {
		this.comments = comments;
	}

}
