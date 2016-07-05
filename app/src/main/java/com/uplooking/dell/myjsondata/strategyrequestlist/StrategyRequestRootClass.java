package com.uplooking.dell.myjsondata.strategyrequestlist;

import com.uplooking.dell.myjsondata.TerminalMessage;

import java.util.List;

public class StrategyRequestRootClass extends TerminalMessage{
	private StrategyRequest request;

	public StrategyRequestRootClass(){
		request = new StrategyRequest();
	}

	public StrategyRequest getrequest() {
		return request;
	}

	public void setrequest(StrategyRequest request) {
		this.request = request;
	}
}
