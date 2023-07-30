package com.shineclub.api.dto;

import java.util.Arrays;

public class AllMembers {
	private String name;
	private String imagePath;
	private String email;
	private String phoneNo;
	private byte[] image;
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	@Override
	public String toString() {
		return "AllMembers [name=" + name + ", imagePath=" + imagePath + ", email=" + email + ", phoneNo=" + phoneNo
				+ ", image=" + Arrays.toString(image) + "]";
	}
	
	
	
	
}
