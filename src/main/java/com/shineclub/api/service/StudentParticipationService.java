package com.shineclub.api.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import com.shineclub.api.dto.OTPVerificationRequest;
import com.shineclub.api.dto.StudentResponse;
import com.shineclub.api.entity.Student;

public interface StudentParticipationService {

	Student studentParticipate(Student student) throws SQLIntegrityConstraintViolationException;

	StudentResponse getAuthRequest(OTPVerificationRequest request);

	String sendOTPToStudent(String phoneNo);

}
