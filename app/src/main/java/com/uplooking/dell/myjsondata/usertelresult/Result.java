package com.uplooking.dell.myjsondata.usertelresult;

import java.util.List;

public class Result {
	private String phoneNumber;
	private String email;
	private String question;

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

	public String getquestion() {
              if (question==null) {
                  return "";
              }
              else{
                  return question;
              }
	}

	public void setquestion(String question) {
		this.question = question;
	}
}
