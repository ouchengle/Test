package com.uplooking.dell.myjsondata.newsallchannel;

public class RequestType {
	private String type;

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
