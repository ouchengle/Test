package com.uplooking.dell.myjsondata.newswebviewrequest;

import com.uplooking.dell.myjsondata.TerminalMessage;

import java.util.List;

public class WebViewRequestRootClass extends TerminalMessage{
	private WebViewRequest request;

	public WebViewRequestRootClass(){
		request = new WebViewRequest();
	}

	public WebViewRequest getrequest() {
		return request;
	}

	public void setrequest(WebViewRequest request) {
		this.request = request;
	}
}
