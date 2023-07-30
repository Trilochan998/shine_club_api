package com.shineclub.api.dto;

import java.sql.Date;

public class MemberRequest {
	private String memberName;
	private String mobileNo;
	private String email;
	private String gender;
	private Date dob;
	private String password;
	private String imagePath;
	private String village;
	private String city;
	private String district;
	private int pin;
	private Date dateOfTransction;
	private String tranctionDetails;
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
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
	@Override
	public String toString() {
		return "MemberRequest [memberName=" + memberName + ", mobileNo=" + mobileNo + ", email=" + email + ", gender="
				+ gender + ", password=" + password + ", imagePath=" + imagePath + ", village=" + village + ", city="
				+ city + ", district=" + district + ", pin=" + pin + ", tranctionDetails=" + tranctionDetails + "]";
	}
	
	
	
}
