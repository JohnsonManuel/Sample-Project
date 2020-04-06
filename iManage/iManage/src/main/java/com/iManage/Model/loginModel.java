package com.iManage.Model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.iManage.Bean.LoginBean;
import com.iManage.Client.Login;

@RequestScoped
@ManagedBean(name="loginModel",eager=true)
public class loginModel {
	private static final Logger log = LogManager.getLogger(loginModel.class);	

	
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;	
	
	
	
	public String validateUser() {
		log.trace("Validating user ");
		Login login = new Login();
		String user = login.checkuserinDB(loginBean.getUsername(),loginBean.getPassword());
		
		log.trace("User");
		
		if(!user.isEmpty()) {
			log.trace("User exists and is of type "+user);
			loginBean.setUserType(user);
			loginBean.setUsername("");
			loginBean.setPassword("");	
			String captcha = login.getCaptchaString();
			log.trace("Getting capthca from server...");
			loginBean.setCaptcha(captcha);
			log.trace("Setting captcha of value :"+ captcha);
			loginBean.setCaptcha64(ConvertTexttoBase64(captcha));
			return "captcha";
		}else {
			log.trace("User doesn't exist");
			System.out.println("Came here");
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Invalid Username or password"));
			return null;
		}
		
	}
	
	public String validateCaptcha() {
		log.trace("Validating Captcha");
		System.out.println(loginBean.getCaptcha());
		if(loginBean.getCaptcha().equals(loginBean.getCaptchaText())) {
			log.trace("Entered captcah is valid");
			loginBean.setCaptchaText("");
			loginBean.setCaptcha("");
			loginBean.setCaptcha64("");
			loginBean.setUsername("");
			if(loginBean.getUserType().equals("admin")) {
				return "admin?faces-redirect=true";
			}else {
				return "user?faces-redirect=true";
			}
		}
		else {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("invalid Captcha"));
			return null;
		}
	}
	
	public String logout() {
		return "login?faces-redirect=true";
		
	}


	public String ConvertTexttoBase64(String str) {
		
		int width =320;
		int height =60;
		
		Color bg= new Color(135,127,127);
		Color fg = new Color(100,100,100);
		Font font = new Font("Arial",Font.BOLD,40);
		
		BufferedImage cpimg = new BufferedImage(width,height,BufferedImage.OPAQUE);
		
		Graphics g = cpimg.createGraphics();
		g.setFont(font);
		g.setColor(bg);
		g.fillRect(0, 0, width, height);
		g.setColor(fg);
		g.drawString(str, 80, 45);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(cpimg, "jpg", baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String encodedString = Base64.getEncoder().encodeToString(baos.toByteArray());

		return encodedString;
		
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
}
