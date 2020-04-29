package com.iManageServer.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.Validator;

import com.iManageServer.Pojo.WorkRequestPojo;

public class ServerValidation {
	private static final Logger log = LogManager.getLogger("mainLogger");

	
	static Encoder encoder = ESAPI.encoder();
	static Validator validator = ESAPI.validator();
	
	
	static boolean ESAPIvalidateString(String str) {
		boolean check = validator.isValidInput("field", encoder.canonicalize(str), "Special", 1024,true);
		
		if(check) {
			log.trace("ESAPI validation failed on server");
			log.error("ESAPI validation failed on server");
		}
		else {
			log.trace("ESAPI validation Passed");
			log.error("ESAPI validation Passed");
		}
		return check;
	}
	
	static boolean ESAPIWorkvalidateBean(WorkRequestPojo workrequestbean) {
		
		boolean isvalid = true;

		
		
		boolean check1 = validator.isValidInput("field", encoder.canonicalize(workrequestbean.getName()), "Special", 1024,true);
		boolean check2 = validator.isValidInput("field", encoder.canonicalize(workrequestbean.getRequester()), "Special", 1024,true);
		boolean check3 = validator.isValidInput("field", encoder.canonicalize(workrequestbean.getDescription()), "Special", 1024,true);
		boolean check4 = validator.isValidInput("field", encoder.canonicalize(workrequestbean.getComment()), "Special", 1024,true);
		boolean check5 = validator.isValidInput("field", encoder.canonicalize(workrequestbean.getRequestType()), "Special", 1024,true);
		boolean check6 = validator.isValidInput("field", encoder.canonicalize(workrequestbean.getStatus()), "Special", 1024,true);
		boolean check7 = validator.isValidInput("field", encoder.canonicalize(workrequestbean.getTeam()), "Special", 1024,true);

		if(!check1 || !check2 || !check3 || !check4 || !check5 || !check6 || !check7) {
			log.trace("ESAPI validation failed on server");
			log.error("ESAPI validation failed on server");
			isvalid= false;
		}
		else {
			log.trace("ESAPI validation Passed");
			log.error("ESAPI validation Passed");
		}
		

		
		return isvalid;
	}

}
