package com.uplooking.dell.myjsondata;

import java.util.List;

public class ErrorCodeAndMessage {
	private int errorCode;
	private String errorMessage;

	public int geterrorCode() {
		return errorCode;
	}

	public void seterrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String geterrorMessage() {
              if (errorMessage==null) {
                  return "";
              }
              else{
                  return errorMessage;
              }
	}

	public void seterrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
