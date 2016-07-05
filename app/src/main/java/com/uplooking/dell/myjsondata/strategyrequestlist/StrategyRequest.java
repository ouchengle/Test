package com.uplooking.dell.myjsondata.strategyrequestlist;

import java.util.List;

public class StrategyRequest {
	private String pageIndex;
	private String pageCount;
	private String type;

    public StrategyRequest(){
        pageIndex = "";
        pageCount = "";
        type = "";
    }

	public String getpageIndex() {
              if (pageIndex==null) {
                  return "";
              }
              else{
                  return pageIndex;
              }
	}

	public void setpageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getpageCount() {
              if (pageCount==null) {
                  return "";
              }
              else{
                  return pageCount;
              }
	}

	public void setpageCount(String pageCount) {
		this.pageCount = pageCount;
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
