package com.shineclub.api.dto;

public class OTPVerificationRequest {
	private String otp;
	private String userName;

	
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "OTPVerificationRequest [otp=" + otp + ", userName=" + userName + "]";
	}

	
	
	
	

}
