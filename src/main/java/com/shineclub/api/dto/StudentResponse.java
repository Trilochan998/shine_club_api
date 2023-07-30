package com.shineclub.api.dto;

import com.shineclub.api.entity.Student;

public class StudentResponse {
	private boolean isValidatedSuccess;
	private Student student;
	public boolean isValidatedSuccess() {
		return isValidatedSuccess;
	}
	public void setValidatedSuccess(boolean isValidatedSuccess) {
		this.isValidatedSuccess = isValidatedSuccess;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
}
