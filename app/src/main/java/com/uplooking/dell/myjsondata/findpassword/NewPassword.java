package com.uplooking.dell.myjsondata.findpassword;

import com.uplooking.dell.myjsondata.TerminalMessage;

import java.util.List;

public class NewPassword extends TerminalMessage{
	private NewPasswordMessage request;

	public NewPassword(){
		request = new NewPasswordMessage();
	}

	public NewPasswordMessage getrequest() {
		return request;
	}

	public void setrequest(NewPasswordMessage request) {
		this.request = request;
	}
}
