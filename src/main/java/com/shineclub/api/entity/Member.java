package com.shineclub.api.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "member")
public class Member {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;
	private String memberName;
	@Column(unique = true)
	private String mobileNo;
	private String email;
	private String gender;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dob;
	private String password;
	private String imagePath;
	
	@Lob
    private byte[] data;
	
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "member_address_id", referencedColumnName = "addressId")
	private Address address;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "mbr_trns_id", referencedColumnName = "memberId")
	private List<Transaction> transction = new ArrayList<>();
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
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
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Transaction> getTransction() {
		return transction;
	}
	public void setTransction(List<Transaction> transction) {
		this.transction = transction;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberName=" + memberName + ", mobileNo=" + mobileNo + ", email="
				+ email + ", gender=" + gender + ", dob=" + dob + ", password=" + password + ", imagePath=" + imagePath
				+ ", address=" + address + ", transction=" + transction + "]";
	}
	
	
	
	
}
