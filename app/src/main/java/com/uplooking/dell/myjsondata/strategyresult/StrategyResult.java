package com.uplooking.dell.myjsondata.strategyresult;

public class StrategyResult {
	private int specialID;
	private String thumbnailUrl;
	private String title;
	private int favoriteCnt;

	public int getspecialID() {
		return specialID;
	}

	public void setspecialID(int specialID) {
		this.specialID = specialID;
	}

	public String getthumbnailUrl() {
              if (thumbnailUrl==null) {
                  return "";
              }
              else{
                  return thumbnailUrl;
              }
	}

	public void setthumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String gettitle() {
              if (title==null) {
                  return "";
              }
              else{
                  return title;
              }
	}

	public void settitle(String title) {
		this.title = title;
	}

	public int getfavoriteCnt() {
		return favoriteCnt;
	}

	public void setfavoriteCnt(int favoriteCnt) {
		this.favoriteCnt = favoriteCnt;
	}
}
