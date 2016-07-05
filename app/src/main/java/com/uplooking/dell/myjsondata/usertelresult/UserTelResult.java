package com.uplooking.dell.myjsondata.usertelresult;

import com.uplooking.dell.myjsondata.ErrorCodeAndMessage;

import java.util.List;

public class UserTelResult extends ErrorCodeAndMessage{
	private Result result;

	public Result getresult() {
		return result;
	}

	public void setresult(Result result) {
		this.result = result;
	}
}
