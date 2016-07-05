package com.uplooking.dell.myjsondata.userlogin;

import com.uplooking.dell.myjsondata.ErrorCodeAndMessage;

import java.util.List;

public class UserLoginResult extends ErrorCodeAndMessage{
	private MessageResult result;

	public MessageResult getresult() {
		return result;
	}

	public void setresult(MessageResult result) {
		this.result = result;
	}
}
