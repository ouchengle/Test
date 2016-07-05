package com.uplooking.dell.myjsondata.newsrequestlist;

public class NewsRequest {
	private String parentNodeId;
	private String nodeIds;
	private String pageIndex;
	private String type;
	private int elementsCountPerPage;

	public NewsRequest(){
		parentNodeId = "";
		nodeIds = "";
		pageIndex = "";
		type ="";
		elementsCountPerPage = 20;
	}

	public String getparentNodeId() {
              if (parentNodeId==null) {
                  return "";
              }
              else{
                  return parentNodeId;
              }
	}

	public void setparentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}

	public String getnodeIds() {
              if (nodeIds==null) {
                  return "";
              }
              else{
                  return nodeIds;
              }
	}

	public void setnodeIds(String nodeIds) {
		this.nodeIds = nodeIds;
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

	public String getType() {
		if(type == null){
			return "";
		}
		else {
			return type;
		}
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getelementsCountPerPage() {
		return elementsCountPerPage;
	}

	public void setelementsCountPerPage(int elementsCountPerPage) {
		this.elementsCountPerPage = elementsCountPerPage;
	}
}
