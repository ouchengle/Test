package com.uplooking.dell.myjsondata.handgamerequestlist;

import java.util.List;

public class HandGameRequest {
	private String type;

    public HandGameRequest(){
        type = "";
    }

	public String gettype() {
              if (type==null) {
                  return "";
              }
              else{
                  return type;
              }
	}

	public void settype(String type) {
		this.type = type;
	}
}
