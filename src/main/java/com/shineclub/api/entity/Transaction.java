package com.shineclub.api.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transction")
public class Transaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long transctionId;
	private Date dateOfTransction;
	private String paymentStatus;
	private String rozerpayPaymentId;
	private String razorpayOrderId;
	private String razorpaySignature;
	private String customerName;
	private String phoneNo;
	private String amount;
	private int year;
	private int month;
	
	public Long getTransctionId() {
		return transctionId;
	}
	public void setTransctionId(Long transctionId) {
		this.transctionId = transctionId;
	}
	public Date getDateOfTransction() {
		return dateOfTransction;
	}
	public void setDateOfTransction(Date dateOfTransction) {
		this.dateOfTransction = dateOfTransction;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getRozerpayPaymentId() {
		return rozerpayPaymentId;
	}
	public void setRozerpayPaymentId(String rozerpayPaymentId) {
		this.rozerpayPaymentId = rozerpayPaymentId;
	}
	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}
	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}
	public String getRazorpaySignature() {
		return razorpaySignature;
	}
	public void setRazorpaySignature(String razorpaySignature) {
		this.razorpaySignature = razorpaySignature;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	@Override
	public String toString() {
		return "Transaction [transctionId=" + transctionId + ", dateOfTransction=" + dateOfTransction
				+ ", paymentStatus=" + paymentStatus + ", rozerpayPaymentId=" + rozerpayPaymentId + ", razorpayOrderId="
				+ razorpayOrderId + ", razorpaySignature=" + razorpaySignature + ", customerName=" + customerName
				+ ", phoneNo=" + phoneNo + ", amount=" + amount + ", year=" + year + ", month=" + month + "]";
	}
	
	
	
}
