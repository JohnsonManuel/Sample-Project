package com.iManage.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();
			boolean passThrough = false;

//			//this is the go to path
//			System.out.println(reqURI);
//			//this is the from path
//			System.out.println(reqt.getContextPath());

			if(reqURI.indexOf("/login.xhtml")>=0 && ses!=null) {
				System.out.println("page hit");
				if(ses.getAttribute("user-type")!=null && ses.getAttribute("team")!=null) {
				resp.sendRedirect(reqt.getContextPath()+"/"+ses.getAttribute("user-type").toString()+".xhtml");
				}
				
			}
			
			
			
			if (ses != null) {
				System.out.println("The sit hot");
				if (ses.getAttribute("user") != null && ses.getAttribute("team") != null ) {
					if (ses.getAttribute("user-type") != null) {

						if (reqURI.indexOf("/admin.xhtml") >= 0) {
							
							 if( ses.getAttribute("user-type").equals("admin")) {
								 passThrough = true;
							 }else {
								 if(ses.getAttribute("team")==null) {
									 passThrough= false;
								 }else {
									 resp.sendRedirect(reqt.getContextPath()+"/"+ses.getAttribute("user-type").toString()+".xhtml");

								 }
							 }
							
						}
						if (reqURI.indexOf("/user.xhtml") >= 0) {
							if( ses.getAttribute("user-type").equals("user")) {
								 passThrough = true;
							 }else {
								 if(ses.getAttribute("team")==null) {
									 passThrough= false;
								 }else {
									 resp.sendRedirect(reqt.getContextPath()+"/"+ses.getAttribute("user-type").toString()+".xhtml");

								 }
							 }
						}
					}
				}
			} else {
					passThrough = false;
			}

			System.out.println(reqt.getContextPath());

			
			
			
			
			
//			if((ses != null)&&reqURI.indexOf("/captcha.xhtml")>=0) {
//				passThrough=(?false:true;				
//			}
			
			
			
			

			System.out.println("GO TO " + reqURI);

			if (reqURI.indexOf("/login.xhtml") >= 0 || reqURI.contains("javax.faces.resource") || passThrough ||reqURI.indexOf("/captcha.xhtml")>=0 ) {
				System.out.println("chain ");
				System.out.println("Still came here");
				chain.doFilter(request, response);
			} else {
				System.out.println("Redirect");
				resp.sendRedirect(reqt.getContextPath() + "/login.xhtml");
			}
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {

	}

}
