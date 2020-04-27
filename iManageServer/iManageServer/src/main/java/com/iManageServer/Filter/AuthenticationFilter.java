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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.iManageServer.Service.LoginService;
import com.iManageServer.Service.WorkRequestService.Secured;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	private static final Logger log = LogManager.getLogger("mainLogger");

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
			log.trace("Validating request");
			validateAuthCode(authCodeReceived);
		} catch (Exception e) {
			log.trace(e.getMessage());
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());

		}

	}

	/**
	 * 
	 * This method validated the received Authcode and checks whether it is present in server storage
	 * 
	 * @param authReceived The authcode received from user
	 * @throws Exception
	 */
	public void validateAuthCode(String authReceived) throws Exception {

		if (null != authReceived && service.getTokens() != null) {
			if (service.getTokens().contains(authReceived)) {
				log.trace("Request is valid");
			} else {
				throw new Exception("The authid received is invalid");
			}
		} else {
			log.trace("No Authid received");

			throw new Exception("Exception null");
		}

	}

}
