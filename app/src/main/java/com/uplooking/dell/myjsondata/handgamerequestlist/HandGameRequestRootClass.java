package com.uplooking.dell.myjsondata.handgamerequestlist;

import com.uplooking.dell.myjsondata.newsrequestlist.NewsRequestRootClass;

import java.util.List;

public class HandGameRequestRootClass extends NewsRequestRootClass{
	private HandGameRequest request;

	public HandGameRequestRootClass(){
		request = new HandGameRequest();
	}

	public HandGameRequest getHandGameRequest() {
		return request;
	}

	public void setrequest(HandGameRequest request) {
		this.request = request;
	}
}
