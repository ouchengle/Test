package com.uplooking.dell.myjsondata.checkcode;

import java.util.List;

public class RequestType {
	private String phoneNumber;
	private String email;
	private int codetype;
	private String username;

	public RequestType(){
		phoneNumber = "";
		email = "";
		codetype = 1;
		username = "";
	}

	public String getphoneNumber() {
              if (phoneNumber==null) {
                  return "";
              }
              else{
                  return phoneNumber;
              }
	}

	public void setphoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getemail() {
              if (email==null) {
                  return "";
              }
              else{
                  return email;
              }
	}

	public void setemail(String email) {
		this.email = email;
	}

	public int getcodetype() {
		return codetype;
	}

	public void setcodetype(int codetype) {
		this.codetype = codetype;
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
