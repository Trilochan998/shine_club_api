package com.shineclub.api.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shineclub.api.dto.OTPVerificationRequest;
import com.shineclub.api.dto.StudentResponse;
import com.shineclub.api.entity.Student;
import com.shineclub.api.service.StudentParticipationService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/student")
public class StudentsParticipationController {

	@Autowired
	StudentParticipationService studentParticipationService;
	

	@PostMapping("/studentParticipate")
	public ResponseEntity<Student> studentParticipate(@RequestBody Student student)
			throws SQLIntegrityConstraintViolationException {
		Student studentParticipate = studentParticipationService.studentParticipate(student);
		return ResponseEntity.ok(studentParticipate);
	}
	@GetMapping("/sendOTP")
	public String sendOTPToStudent(@RequestParam String phoneNo) {
		
		String sendOTP = studentParticipationService.sendOTPToStudent(phoneNo);

		return sendOTP;
	}
	@PostMapping("/studentLogin")
	public StudentResponse loginStudent(@RequestBody OTPVerificationRequest request) {
		return studentParticipationService.getAuthRequest(request);
	}

}
