package com.uplooking.dell.myjsondata.newswebviewresult;

import com.uplooking.dell.myjsondata.ErrorCodeAndMessage;

import java.util.List;

public class WebViewResultRootClass extends ErrorCodeAndMessage{
	private WebViewResult result;

	public WebViewResult getresult() {
		return result;
	}

	public void setresult(WebViewResult result) {
		this.result = result;
	}
}
