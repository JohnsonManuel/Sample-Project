package com.iManageServer.Application;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.iManageServer.Service.LoginService;

public class MyBinder extends AbstractBinder {

	@Override
	protected void configure() {
		bind(LoginService.class).in(Singleton.class);
	}
}