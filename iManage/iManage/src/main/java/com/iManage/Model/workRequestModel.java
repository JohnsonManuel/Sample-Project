package com.iManage.Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.iManage.Bean.workRequestBean;

@ManagedBean(name="workRequestModel" ,eager=true)
public class workRequestModel {
	
	@ManagedProperty(value="#{workrequestBean}")
	private workRequestBean workrequestBean;
	
	public workRequestModel() {
		
	}
	
	

	public workRequestBean getWorkrequestBean() {
		return workrequestBean;
	}

	public void setWorkrequestBean(workRequestBean workrequestBean) {
		this.workrequestBean = workrequestBean;
	}
	
	
	
}
