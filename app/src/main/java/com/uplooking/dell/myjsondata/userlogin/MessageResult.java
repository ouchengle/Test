package com.uplooking.dell.myjsondata.userlogin;

import java.util.List;

public class MessageResult {
	private String loginToken;
	private String userName;
	private int userId;
	private String pic;

	public String getloginToken() {
              if (loginToken==null) {
                  return "";
              }
              else{
                  return loginToken;
              }
	}

	public void setloginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	public String getuserName() {
              if (userName==null) {
                  return "";
              }
              else{
                  return userName;
              }
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public int getuserId() {
		return userId;
	}

	public void setuserId(int userId) {
		this.userId = userId;
	}

	public String getpic() {
              if (pic==null) {
                  return "";
              }
              else{
                  return pic;
              }
	}

	public void setpic(String pic) {
		this.pic = pic;
	}
}
