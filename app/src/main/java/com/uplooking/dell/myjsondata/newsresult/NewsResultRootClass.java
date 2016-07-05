package com.uplooking.dell.myjsondata.newsresult;

import com.uplooking.dell.myjsondata.ErrorCodeAndMessage;

import java.util.List;

public class NewsResultRootClass extends ErrorCodeAndMessage{
	private List<NewsResult> result;

	public List<NewsResult> getresult() {
		return result;
	}

	public void setresult(List<NewsResult> result) {
		this.result = result;
	}
}
