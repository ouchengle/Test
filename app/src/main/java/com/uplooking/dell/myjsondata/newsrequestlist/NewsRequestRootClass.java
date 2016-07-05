package com.uplooking.dell.myjsondata.newsrequestlist;

import com.uplooking.dell.myjsondata.TerminalMessage;

public class NewsRequestRootClass extends TerminalMessage{

	public NewsRequestRootClass(){
		request = new NewsRequest();
	}

	private NewsRequest request;

	public NewsRequest getrequest() {
		return request;
	}

	public void setrequest(NewsRequest request) {
		this.request = request;
	}
}
