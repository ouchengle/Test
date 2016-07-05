package com.uplooking.dell.myjsondata.strategyresult;

import com.uplooking.dell.myjsondata.ErrorCodeAndMessage;

import java.util.List;

public class StrategyResultRootClass extends ErrorCodeAndMessage {
	private List<StrategyResult> result;

	public List<StrategyResult> getresult() {
		return result;
	}

	public void setresult(List<StrategyResult> result) {
		this.result = result;
	}
}
