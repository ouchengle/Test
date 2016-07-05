package com.uplooking.dell.myjsondata.findpassword;

import java.util.List;

public class UserName {
	private String username;

    public UserName(){
        username = "";
    }

	public String getusername() {
              if (username==null) {
                  return "";
              }
              else{
                  return username;
              }
	}

	public void setusername(String username) {
		this.username = username;
	}
}
