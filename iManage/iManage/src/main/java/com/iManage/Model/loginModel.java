package com.iManage.Model;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.iManage.Bean.LoginBean;

@RequestScoped
@ManagedBean(name="loginModel",eager=true)
public class loginModel {

	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;	
	
	public String validateUser() {
		System.out.println("Values of usernmae "+loginBean.getUsername());
		System.out.println("Values of password "+loginBean.getPassword());

		if(loginBean.getUsername().equals("admin")&&loginBean.getPassword().equals("admin")) {
			loginBean.setUsername("");
			loginBean.setPassword("");
			loginBean.setCaptcha("SECRET");
			return "capthca";
		}else {
			System.out.println("Came here");
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("invalid Username or password"));
			return null;
		}
		
	}
	
	public String validateCaptcha() {
		
		System.out.println(loginBean.getCaptcha());
		if(loginBean.getCaptcha().equals(loginBean.getCaptchaText())) {
			loginBean.setCaptchaText("");
			return "index?faces-redirect=true";
		}
		else {
			System.out.println("Came here");
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("invalid Captcha"));
			return null;
		}
	}


	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
}
