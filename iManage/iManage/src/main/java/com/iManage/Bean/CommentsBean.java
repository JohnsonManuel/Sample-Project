package com.iManage.Bean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "commentsBean", eager = true)
public class CommentsBean {

	private int id;
	private String comment;
	private String time;
	private Date timer = new Date() ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Date getTimer() {
		timer = new Date();
		return timer;
	}

	public void setTimer(Date timer) {
		this.timer = timer;
	}

}
