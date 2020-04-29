package com.iManageServer.Dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertiesResource {
	private static final Logger log = LogManager.getLogger("mainLogger");
	
	
	private static final PropertiesResource INSTANCE = new PropertiesResource();

	
	private final Properties configProp = new Properties();

	private PropertiesResource() {
		// Private constructor to restrict new instances
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("credentials.properties");
		log.trace("Read all properties from file");
		try {
			configProp.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static PropertiesResource getInstance() {
		return INSTANCE;
	}

	public String getProperty(String key) {
		return configProp.getProperty(key);
	}

	

	
	

}
