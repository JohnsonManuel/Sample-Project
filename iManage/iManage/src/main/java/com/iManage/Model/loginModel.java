package com.iManage.Model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.Validator;

import com.iManage.Bean.LoginBean;
import com.iManage.Client.Login;

@SessionScoped
@ManagedBean(name="loginModel",eager=true)
public class loginModel {
	private static final Logger log = LogManager.getLogger(loginModel.class);	

	
	@ManagedProperty(value="#{loginBean}")
	private LoginBean loginBean;	
	
	private String response[] ;

	
	Encoder encoder = ESAPI.encoder();
	Validator validator = ESAPI.validator();
	
	
	
//	public static void main(String[] args) {
//		loginModel obj = new loginModel();
//		
//		System.out.println( 
//				obj.validator.isValidInput("Username", obj.encoder.canonicalize("12345666ababababababhdbsajbjdfHJAHJBDJA_"), "SafeString", 40, true)
//				);
//		
//	}
	
	public String validateUser() {
		
		
		
		log.trace("Validating user ");
		Login login = new Login();
		boolean isSafeusername = validator.isValidInput("Username", encoder.canonicalize(loginBean.getUsername()), "UserName", 40, false);
		boolean isSafepassword = validator.isValidInput("password", encoder.canonicalize(loginBean.getPassword()), "Special", 40, false);

		if(isSafeusername&& isSafepassword) {
			log.trace("Esapi passes for Login input");
			response = login.checkuserinDB(loginBean.getUsername(),getEncryptText(loginBean.getPassword()));
		}else {
			log.trace("Esapi failed for Login input");
			response = new String[2];
		}
	
	
		

		log.trace("User");
		
		if(response[0]!=null) {
			log.trace("User exists and is of type "+response[0]);
			
					
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user",loginBean.getUsername());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user-type",response[0]);

			loginBean.setUserType(response[0]);			

			//reset values
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
		boolean isSafetext = validator.isValidInput("Captcha", encoder.canonicalize(loginBean.getCaptchaText()), "SafeString", 7, false);

		
		
		log.trace("Validating Captcha");
		System.out.println(loginBean.getCaptcha());
		
		if(loginBean.getCaptcha().equals(loginBean.getCaptchaText()) && isSafetext ) {
			log.trace("Esapi passes for captcha input");

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("team",response[1]);

			log.trace("Entered captcah is valid");
			loginBean.setCaptchaText("");
			loginBean.setCaptcha("");
			loginBean.setCaptcha64("");
		
			if(loginBean.getUserType().equals("admin")) {
				return "admin?faces-redirect=true";
			}else {
				return "user?faces-redirect=true";
			}
		}
		else {
			log.trace("Esapi failed for captcha input or validation failed");

			loginBean.setCaptchaText("");
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("invalid Captcha"));
			return null ;
		}
	}
	
	public String logout() {
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
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
	
//	public static void main(String[] args) {
//		System.out.println(getEncryptText("admin2"));
//	}
	
	
	
	public static String getEncryptText( String plainText) {
		  String salt = "SecretText";
		  int iterations = 10000;
	      int keyLength = 512;
	      
		  byte[] hashedBytes = hashPassword(plainText.toCharArray(),salt.getBytes(), iterations, keyLength);
		  String hashedString = Hex.encodeHexString(hashedBytes);
		  
		  return hashedString;

	}

    public static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return res;
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }
	
	
	
	
	

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

		

}
