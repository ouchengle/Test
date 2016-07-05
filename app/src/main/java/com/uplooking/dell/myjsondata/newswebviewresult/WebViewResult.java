package com.uplooking.dell.myjsondata.newswebviewresult;

import java.util.List;

public class WebViewResult {
	private int Id;
	private String type;
	private String templateVersion;
	private String templateURL;
	private String title;
	private String subTitle;
	private int pageCount;
	private String thumbnailUrl;
	private String originURL;
	private List<Object> subscribes;
	private Object pageIndexNames;
	private String node;
	private int adId;
	private Object videoContent;
	private String mainBody;

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
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

	public String gettemplateVersion() {
              if (templateVersion==null) {
                  return "";
              }
              else{
                  return templateVersion;
              }
	}

	public void settemplateVersion(String templateVersion) {
		this.templateVersion = templateVersion;
	}

	public String gettemplateURL() {
              if (templateURL==null) {
                  return "";
              }
              else{
                  return templateURL;
              }
	}

	public void settemplateURL(String templateURL) {
		this.templateURL = templateURL;
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

	public String getsubTitle() {
              if (subTitle==null) {
                  return "";
              }
              else{
                  return subTitle;
              }
	}

	public void setsubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public int getpageCount() {
		return pageCount;
	}

	public void setpageCount(int pageCount) {
		this.pageCount = pageCount;
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

	public String getoriginURL() {
              if (originURL==null) {
                  return "";
              }
              else{
                  return originURL;
              }
	}

	public void setoriginURL(String originURL) {
		this.originURL = originURL;
	}

	public List<Object> getsubscribes() {
		return subscribes;
	}

	public void setsubscribes(List<Object> subscribes) {
		this.subscribes = subscribes;
	}

	public Object getpageIndexNames() {
		return pageIndexNames;
	}

	public void setpageIndexNames(Object pageIndexNames) {
		this.pageIndexNames = pageIndexNames;
	}

	public String getnode() {
              if (node==null) {
                  return "";
              }
              else{
                  return node;
              }
	}

	public void setnode(String node) {
		this.node = node;
	}

	public int getadId() {
		return adId;
	}

	public void setadId(int adId) {
		this.adId = adId;
	}

	public Object getvideoContent() {
		return videoContent;
	}

	public void setvideoContent(Object videoContent) {
		this.videoContent = videoContent;
	}

	public String getmainBody() {
              if (mainBody==null) {
                  return "";
              }
              else{
                  return mainBody;
              }
	}

	public void setmainBody(String mainBody) {
		this.mainBody = mainBody;
	}
}
