package com.uplooking.dell.myjsondata.handgameresult;

import java.util.List;

public class HandGameResult {
	private String type;
	private String contentType;
	private String title;
	private Object thumbnailURLs;
	private Object authorName;
	private Object authorHeadImageURL;
	private int readingCount;
	private int commentsCount;
	private Object badges;
	private int contentId;
	private Object contentURL;
	private int adId;
	private int score;
	private Object fileSize;
	private Object gameType;
	private boolean dataPackage;
	private List<ChildElement> childElements;

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

	public String getcontentType() {
              if (contentType==null) {
                  return "";
              }
              else{
                  return contentType;
              }
	}

	public void setcontentType(String contentType) {
		this.contentType = contentType;
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

	public Object getthumbnailURLs() {
		return thumbnailURLs;
	}

	public void setthumbnailURLs(Object thumbnailURLs) {
		this.thumbnailURLs = thumbnailURLs;
	}

	public Object getauthorName() {
		return authorName;
	}

	public void setauthorName(Object authorName) {
		this.authorName = authorName;
	}

	public Object getauthorHeadImageURL() {
		return authorHeadImageURL;
	}

	public void setauthorHeadImageURL(Object authorHeadImageURL) {
		this.authorHeadImageURL = authorHeadImageURL;
	}

	public int getreadingCount() {
		return readingCount;
	}

	public void setreadingCount(int readingCount) {
		this.readingCount = readingCount;
	}

	public int getcommentsCount() {
		return commentsCount;
	}

	public void setcommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	public Object getbadges() {
		return badges;
	}

	public void setbadges(Object badges) {
		this.badges = badges;
	}

	public int getcontentId() {
		return contentId;
	}

	public void setcontentId(int contentId) {
		this.contentId = contentId;
	}

	public Object getcontentURL() {
		return contentURL;
	}

	public void setcontentURL(Object contentURL) {
		this.contentURL = contentURL;
	}

	public int getadId() {
		return adId;
	}

	public void setadId(int adId) {
		this.adId = adId;
	}

	public int getscore() {
		return score;
	}

	public void setscore(int score) {
		this.score = score;
	}

	public Object getfileSize() {
		return fileSize;
	}

	public void setfileSize(Object fileSize) {
		this.fileSize = fileSize;
	}

	public Object getgameType() {
		return gameType;
	}

	public void setgameType(Object gameType) {
		this.gameType = gameType;
	}

	public boolean getdataPackage() {
		return dataPackage;
	}

	public void setdataPackage(boolean dataPackage) {
		this.dataPackage = dataPackage;
	}

	public List<ChildElement> getchildElements() {
		return childElements;
	}

	public void setchildElements(List<ChildElement> childElements) {
		this.childElements = childElements;
	}
}
