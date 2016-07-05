package com.uplooking.dell.myjsondata.checkcode;

import com.uplooking.dell.myjsondata.TerminalMessage;

import java.util.List;

public class CheckCode extends TerminalMessage{
/*    private String os;
    private String osVersion;
    private String appVersion;
    private String deviceId;
    private String deviceType;*/
    private String app;
    private RequestType request;

    public CheckCode() {
/*        os = "";
        osVersion = "";

        appVersion = "";
        deviceId = "";
        deviceType = "";*/
        app = "";
        new TerminalMessage();
        request = new RequestType();
    }

   /* public String getos() {
        if (os == null) {
            return "";
        } else {
            return os;
        }
    }

    public void setos(String os) {
        this.os = os;
    }

    public String getosVersion() {
        if (osVersion == null) {
            return "";
        } else {
            return osVersion;
        }
    }

    public void setosVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getappVersion() {
        if (appVersion == null) {
            return "";
        } else {
            return appVersion;
        }
    }

    public void setappVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getdeviceId() {
        if (deviceId == null) {
            return "";
        } else {
            return deviceId;
        }
    }

    public void setdeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getdeviceType() {
        if (deviceType == null) {
            return "";
        } else {
            return deviceType;
        }
    }

    public void setdeviceType(String deviceType) {
        this.deviceType = deviceType;
    }*/

    public String getapp() {
        if (app == null) {
            return "";
        } else {
            return app;
        }
    }

    public void setapp(String app) {
        this.app = app;
    }

    public RequestType getrequest() {
        return request;
    }

    public void setrequest(RequestType request) {
        this.request = request;
    }
}
