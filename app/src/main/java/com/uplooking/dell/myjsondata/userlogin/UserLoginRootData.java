package com.uplooking.dell.myjsondata.userlogin;

import com.uplooking.dell.myjsondata.TerminalMessage;

public class UserLoginRootData extends TerminalMessage{
	private UserLoginRequestData request;

	public UserLoginRootData(){
		request = new UserLoginRequestData();
	}

	public UserLoginRequestData getrequest() {
		return request;
	}

	public void setrequest(UserLoginRequestData userLoginRequestData) {
		this.request = userLoginRequestData;
	}
}
