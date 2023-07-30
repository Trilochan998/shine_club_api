package com.shineclub.api.dto;

public class PaymentResponse {

	private String secretKey;
	private String razorPayOrderId;
	private String applicationFee;
	private String secretId;
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getRazorPayOrderId() {
		return razorPayOrderId;
	}
	public void setRazorPayOrderId(String razorPayOrderId) {
		this.razorPayOrderId = razorPayOrderId;
	}
	public String getApplicationFee() {
		return applicationFee;
	}
	public void setApplicationFee(String applicationFee) {
		this.applicationFee = applicationFee;
	}
	public String getSecretId() {
		return secretId;
	}
	public void setSecretId(String secretId) {
		this.secretId = secretId;
	}
	@Override
	public String toString() {
		return "PaymentResponse [secretKey=" + secretKey + ", razorPayOrderId=" + razorPayOrderId + ", applicationFee="
				+ applicationFee + ", secretId=" + secretId + "]";
	}
	
}
