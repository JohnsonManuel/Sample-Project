package com.iManageServer.Dao;

public class WorkDAO {

	
	public boolean checkuserinDB(String user,String pass) {
		if(user.equals("john")&&pass.equals("John1234")) {
			return true;
		}
		else {
			return false;
			
		}
	}

	public String getcaptcha(int length) {
        String elegibleChars = "ABCDEFGHJKLMNPQRSTUVWXY"+"abcdefghjkmnpqrstuvwxy"+"123456789";
        char[] chars = elegibleChars.toCharArray();
        StringBuffer finalString = new StringBuffer();
        for (int i = 0; i < length; i++) {
        	finalString.append(chars[(int) Math.round(Math.random()*(chars.length - 1))]);
        }
		return finalString.toString();
	}
}
