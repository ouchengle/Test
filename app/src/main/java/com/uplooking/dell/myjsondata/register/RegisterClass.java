package com.uplooking.dell.myjsondata.register;

import com.uplooking.dell.myjsondata.TerminalMessage;

import java.util.List;

public class RegisterClass extends TerminalMessage{
	private String app;
	private RegisterMessage request;

	public RegisterClass(){
		app = "";
		request = new RegisterMessage();
	}

	public String getapp() {
		if (app==null) {
			return "";
		}
		else{
			return app;
		}
	}

	public void setapp(String app) {
		this.app = app;
	}

	public RegisterMessage getrequest() {
		return request;
	}

	public void setrequest(RegisterMessage request) {
		this.request = request;
	}
}
