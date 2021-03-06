package com.iManageServer.Pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CommentsBean")
public class CommentsPojo {

	private int id;
	private String comment;
	private String time;

	public CommentsPojo(int id, String comment, String time) {
		super();
		this.id = id;
		this.comment = comment;
		this.setTime(time);
	}

	@XmlElement(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name = "comment")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@XmlElement(name = "time")
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
