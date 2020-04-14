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
public class AuthorizationFilter implements Filter{
	
	public AuthorizationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			
			String reqURI = reqt.getRequestURI();
			boolean passThrough =false;
			
			
//			//this is the go to path
//			System.out.println(reqURI);
//			//this is the from path
//			System.out.println(reqt.getContextPath());
			
			
			if(ses != null) {
				if(ses.getAttribute("user")!=null) {
					if(ses.getAttribute("user-type")!=null) {
						
						if(reqURI.indexOf("/admin.xhtml")>=0 && ses.getAttribute("user-type").equals("admin")) {
							passThrough = true;
						}
						if(reqURI.indexOf("/user.xhtml")>=0 && ses.getAttribute("user-type").equals("user")) {
							passThrough = true;
						}
						
					}
				}
			}
			
			System.out.println(reqt.getContextPath());
			
			if(reqURI.indexOf("/captcha.xhtml") >= 0) {
				passThrough=true;
			}
			
			
			
			
			System.out.println("GO TO "+reqURI);
			
			
			if (reqURI.indexOf("/login.xhtml") >= 0
					|| reqURI.contains("javax.faces.resource")
					|| passThrough
//					&& ses.getAttribute("user") != null
					)
					
//					||((ses.getAttribute("user-type").equals("admin")&& reqURI.indexOf("/admin.xhtml") >= 0)&&)
				
				{
		
				System.out.println("chain ");
			
				chain.doFilter(request, response);
				
			
				
				}
			else {
				
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
