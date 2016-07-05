package com.uplooking.dell.myjsondata.handgameresult;

import com.uplooking.dell.myjsondata.ErrorCodeAndMessage;

import java.util.List;

public class HandGameResultRootClass extends ErrorCodeAndMessage {
	private List<HandGameResult> result;

	public List<HandGameResult> getresult() {
		return result;
	}

	public void setresult(List<HandGameResult> result) {
		this.result = result;
	}
}
