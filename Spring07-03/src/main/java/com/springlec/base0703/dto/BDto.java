package com.springlec.base0703.dto;



public class BDto {

	
	int bId;
	String bName;
	String bTel;
	String bAddress;
	String bEmail;
	String bRelation;
	
	
	
	
	public BDto() {
		// TODO Auto-generated constructor stub
	}




	public BDto(int bId, String bName, String bTel, String bAddress, String bEmail, String bRelation) {
		super();
		this.bId = bId;
		this.bName = bName;
		this.bTel = bTel;
		this.bAddress = bAddress;
		this.bEmail = bEmail;
		this.bRelation = bRelation;
	}




	public int getbId() {
		return bId;
	}




	public void setbId(int bId) {
		this.bId = bId;
	}




	public String getbName() {
		return bName;
	}




	public void setbName(String bName) {
		this.bName = bName;
	}




	public String getbTel() {
		return bTel;
	}




	public void setbTel(String bTel) {
		this.bTel = bTel;
	}




	public String getbAddress() {
		return bAddress;
	}




	public void setbAddress(String bAddress) {
		this.bAddress = bAddress;
	}




	public String getbEmail() {
		return bEmail;
	}




	public void setbEmail(String bEmail) {
		this.bEmail = bEmail;
	}




	public String getbRelation() {
		return bRelation;
	}




	public void setbRelation(String bRelation) {
		this.bRelation = bRelation;
	}



	
	
}
