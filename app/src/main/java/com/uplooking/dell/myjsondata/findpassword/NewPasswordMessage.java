package com.uplooking.dell.myjsondata.findpassword;

import java.util.List;

public class NewPasswordMessage {
	private int codetype;
	private String username;
	private String newPassword;
	private String VerificationCode;

	public NewPasswordMessage() {
		codetype = 1;
		username = "";
		newPassword = "";
		VerificationCode = "";
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

	public String getnewPassword() {
              if (newPassword==null) {
                  return "";
              }
              else{
                  return newPassword;
              }
	}

	public void setnewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getVerificationCode() {
              if (VerificationCode==null) {
                  return "";
              }
              else{
                  return VerificationCode;
              }
	}

	public void setVerificationCode(String VerificationCode) {
		this.VerificationCode = VerificationCode;
	}
}
