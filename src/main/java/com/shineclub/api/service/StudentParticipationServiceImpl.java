package com.shineclub.api.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.shineclub.api.constant.VariableConstant;
import com.shineclub.api.controller.OTPController;
import com.shineclub.api.dto.OTPVerificationRequest;
import com.shineclub.api.dto.StudentResponse;
import com.shineclub.api.entity.Student;
import com.shineclub.api.repo.StudentParticipatationRepo;

@Service
public class StudentParticipationServiceImpl implements StudentParticipationService {

	@Autowired
	StudentParticipatationRepo studentParticipatationRepo;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	OTPController otpController;

	@Override
	public Student studentParticipate(Student student) throws SQLIntegrityConstraintViolationException {
		Student participatedStudent = studentParticipatationRepo.save(student);
		return participatedStudent;

	}

	@Override
	public StudentResponse getAuthRequest(OTPVerificationRequest request) {
		StudentResponse loginStudent = new StudentResponse();
		
			ResponseEntity<String> response = restTemplate.postForEntity(VariableConstant.STUDENT_VERIFY_OTP_URL, request,String.class);
			String verifyOTP = response.getBody();
			if (verifyOTP != null) {
				Optional<Student> student = loginStudent(request.getUserName());
				student.ifPresent(std -> {
					loginStudent.setStudent(std);
					loginStudent.setValidatedSuccess(true);
				});
			}else {
				loginStudent.setValidatedSuccess(false);
			}
		return loginStudent;
	}

	public Optional<Student> loginStudent(String phoneNo) {
		Optional<Student> student = null;
		student = studentParticipatationRepo.getByPhone(phoneNo);
		return student;
	}

	@Override
	public String sendOTPToStudent(String phoneNo) {
		String sendOTP = null;
		Optional<Student> student = loginStudent(phoneNo);
		if (student.isPresent()) {
			String sendOTP2 = otpController.sendOTP(phoneNo);
			sendOTP ="OTP Sent Successfully";
		}else {
			sendOTP = "Mobile No doesn't exist";
		}
		return sendOTP;
	}

}
