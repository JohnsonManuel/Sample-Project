package com.iManage.Filter;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;

public class ClientFilter implements ClientRequestFilter {

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		String SecretAuthCode = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("AUTHID");
		requestContext.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + SecretAuthCode);
	}

}
