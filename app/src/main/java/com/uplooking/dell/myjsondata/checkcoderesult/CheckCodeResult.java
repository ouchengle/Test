package com.uplooking.dell.myjsondata.checkcoderesult;

import com.uplooking.dell.myjsondata.ErrorCodeAndMessage;

import java.util.List;

public class CheckCodeResult extends ErrorCodeAndMessage{

	private Object result;


	public Object getresult() {
		return result;
	}

	public void setresult(Object result) {
		this.result = result;
	}
}
