package com.iManageServer.Application;

import org.glassfish.jersey.server.ResourceConfig;

public class JerseyApplication extends ResourceConfig {

	public JerseyApplication() {
		register(new MyBinder());
		packages("com.iManageServer.Service", "com.iManageServer.Filter");

	}

}
