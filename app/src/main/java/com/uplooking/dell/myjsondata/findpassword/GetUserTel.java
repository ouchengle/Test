package com.uplooking.dell.myjsondata.findpassword;

import com.uplooking.dell.myjsondata.TerminalMessage;

public class GetUserTel extends TerminalMessage{
	private UserName request;

	public GetUserTel(){
		request = new UserName();
		new TerminalMessage();
	}

	public UserName getrequest() {
		return request;
	}

	public void setrequest(UserName request) {
		this.request = request;
	}
}
