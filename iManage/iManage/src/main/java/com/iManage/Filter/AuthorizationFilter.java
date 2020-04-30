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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {
	private static final Logger log = LogManager.getLogger("mainLogger");

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
			boolean passThrough2 = false;

			if (reqURI.indexOf("/login.xhtml") >= 0 && ses != null) {
				if (ses.getAttribute("user-type") != null && ses.getAttribute("team") != null) {
					resp.sendRedirect(
							reqt.getContextPath() + "/" + ses.getAttribute("user-type").toString() + ".xhtml");
				}

			}

			if (ses != null) {
				
				if (ses.getAttribute("user") != null && ses.getAttribute("team") != null) {
					
					if (ses.getAttribute("user-type") != null) {
						if (reqURI.indexOf("/admin.xhtml") >= 0) {
							if (ses.getAttribute("user-type").equals("admin")) {
								passThrough = true;
							} else {
								if (ses.getAttribute("team") == null) {
									passThrough = false;
								} else {
									resp.sendRedirect(reqt.getContextPath() + "/"
											+ ses.getAttribute("user-type").toString() + ".xhtml");
								}
							}
						}
						if (reqURI.indexOf("/user.xhtml") >= 0) {
							if (ses.getAttribute("user-type").equals("user")) {
								passThrough = true;
							} else {
								if (ses.getAttribute("team") == null) {
									passThrough = false;
								} else {
									resp.sendRedirect(reqt.getContextPath() + "/"
											+ ses.getAttribute("user-type").toString() + ".xhtml");
								}
							}
						}
					}
				}
			} else {
				passThrough = false;
			}

			if ((reqURI.indexOf("/captcha.xhtml") >= 0)) {
				if (ses != null) {
					if (ses.getAttribute("user") != null && ses.getAttribute("team") == null) {
						passThrough2 = true;
					}
				}
			}

			if (reqURI.indexOf("/login.xhtml") >= 0 || reqURI.contains("javax.faces.resource") || passThrough
					|| passThrough2) {
				log.info("GO TO : " + reqURI);
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect(reqt.getContextPath() + "/login.xhtml");
			}
		} catch (Exception e) {
			log.error("authorixation filter error !");
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {

	}

}
