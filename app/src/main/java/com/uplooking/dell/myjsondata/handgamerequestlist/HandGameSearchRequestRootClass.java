package com.uplooking.dell.myjsondata.handgamerequestlist;

import com.uplooking.dell.myjsondata.TerminalMessage;

public class HandGameSearchRequestRootClass extends TerminalMessage {
	private HandGameSearchRequest request;

	public HandGameSearchRequest getrequest() {
		return request;
	}

	public void setrequest(HandGameSearchRequest request) {
		this.request = request;
	}
}
