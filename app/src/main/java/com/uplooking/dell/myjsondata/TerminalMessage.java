package com.uplooking.dell.myjsondata;

import java.util.List;

public class TerminalMessage {
	private String os;
	private String osVersion;
	private String deviceType;
	private String appVersion;
	private String deviceId;

    public TerminalMessage(){
        os = "";
        osVersion = "";
        appVersion = "2.0.4";
        deviceId = "";
        deviceType = "";
    }

	public String getos() {
              if (os==null) {
                  return "";
              }
              else{
                  return os;
              }
	}

	public void setos(String os) {
		this.os = os;
	}

	public String getosVersion() {
              if (osVersion==null) {
                  return "";
              }
              else{
                  return osVersion;
              }
	}

	public void setosVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getdeviceType() {
              if (deviceType==null) {
                  return "";
              }
              else{
                  return deviceType;
              }
	}

	public void setdeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getappVersion() {
              if (appVersion==null) {
                  return "";
              }
              else{
                  return appVersion;
              }
	}

	public void setappVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getdeviceId() {
              if (deviceId==null) {
                  return "";
              }
              else{
                  return deviceId;
              }
	}

	public void setdeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
