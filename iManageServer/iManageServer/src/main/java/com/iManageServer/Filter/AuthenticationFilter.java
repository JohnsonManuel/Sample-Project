package com.iManageServer.Filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.iManageServer.Service.LoginService;
import com.iManageServer.Service.WorkRequestService.Secured;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	@Inject
	private LoginService service;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		String authCodeReceived = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
			authCodeReceived = authorizationHeader.split(" ")[1];
		}

		try {
			validateAuthCode(authCodeReceived);
		} catch (Exception e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());

		}

	}

	public void validateAuthCode(String authReceived) throws Exception {

		if (null != authReceived && service.getTokens() != null) {
			if (service.getTokens().contains(authReceived)) {

			} else {
				throw new Exception();
			}
		} else {
			throw new Exception("Exception null");
		}

	}

}
