package com.uplooking.dell.myjsondata.handgamerequestlist;

import java.util.List;

public class HandGameSearchRequest {
	private String netType;
	private String gameType;
	private String gameTag;
	private String orderKey;
	private String searchOrder;
	private int pageIndex;
	private int elementsCountPerPage;

	public String getnetType() {
              if (netType==null) {
                  return "";
              }
              else{
                  return netType;
              }
	}

	public void setnetType(String netType) {
		this.netType = netType;
	}

	public String getgameType() {
              if (gameType==null) {
                  return "";
              }
              else{
                  return gameType;
              }
	}

	public void setgameType(String gameType) {
		this.gameType = gameType;
	}

	public String getgameTag() {
              if (gameTag==null) {
                  return "";
              }
              else{
                  return gameTag;
              }
	}

	public void setgameTag(String gameTag) {
		this.gameTag = gameTag;
	}

	public String getorderKey() {
              if (orderKey==null) {
                  return "";
              }
              else{
                  return orderKey;
              }
	}

	public void setorderKey(String orderKey) {
		this.orderKey = orderKey;
	}

	public String getsearchOrder() {
              if (searchOrder==null) {
                  return "";
              }
              else{
                  return searchOrder;
              }
	}

	public void setsearchOrder(String searchOrder) {
		this.searchOrder = searchOrder;
	}

	public int getpageIndex() {
		return pageIndex;
	}

	public void setpageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getelementsCountPerPage() {
		return elementsCountPerPage;
	}

	public void setelementsCountPerPage(int elementsCountPerPage) {
		this.elementsCountPerPage = elementsCountPerPage;
	}
}
