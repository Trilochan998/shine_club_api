package com.shineclub.api.dto;

import java.math.BigInteger;

public class PaymentRequest {
	private String mobileNo;
	private BigInteger amount;
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public BigInteger getAmount() {
		return amount;
	}
	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "PaymentRequest [mobileNo=" + mobileNo + ", amount=" + amount + "]";
	}
	
	
}
