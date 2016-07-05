package com.uplooking.dell.myjsondata.userlogin;

import java.util.List;

public class UserLoginRequestData {
	private String userName;
	private String passWord;

    public UserLoginRequestData(){
        userName = "";
        passWord = "";
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

	public String getpassWord() {
              if (passWord==null) {
                  return "";
              }
              else{
                  return passWord;
              }
	}

	public void setpassWord(String passWord) {
		this.passWord = passWord;
	}
}
