package com.shineclub.api.dto;

import java.sql.Date;

public class TransactionDetails {
	
	private Date dateOfTransction;
	private String tranctionDetails;
	private String mobileNo;
	
	public Date getDateOfTransction() {
		return dateOfTransction;
	}
	public void setDateOfTransction(Date dateOfTransction) {
		this.dateOfTransction = dateOfTransction;
	}
	public String getTranctionDetails() {
		return tranctionDetails;
	}
	public void setTranctionDetails(String tranctionDetails) {
		this.tranctionDetails = tranctionDetails;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
}
