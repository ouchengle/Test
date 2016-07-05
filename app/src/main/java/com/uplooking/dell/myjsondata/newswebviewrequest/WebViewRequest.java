package com.uplooking.dell.myjsondata.newswebviewrequest;

import java.util.List;

public class WebViewRequest {
	private String contentId;
	private String pageIndex;

    public WebViewRequest(){
        contentId = "";
        pageIndex = "";
    }

	public String getcontentId() {
              if (contentId==null) {
                  return "";
              }
              else{
                  return contentId;
              }
	}

	public void setcontentId(String contentId) {
		this.contentId = contentId;
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
}
