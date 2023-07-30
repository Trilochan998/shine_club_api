package com.shineclub.api.controller;

import java.text.DecimalFormat;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shineclub.api.dto.OTPRequest;
import com.shineclub.api.dto.OTPVerificationRequest;
import com.shineclub.api.entity.Member;
import com.shineclub.api.repo.MemberRepo;
import com.shineclub.api.service.TwilioService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import reactor.core.publisher.Mono;

@RestController
public class OTPController {
	
	@Autowired
    private TwilioService twilioService;
	
	@Autowired
	MemberRepo memberRepo;

    @GetMapping("/forgotPassword")
    public String forgotPassword(@RequestParam("userName") String userName) {
    	String sendOTPMsg = null;
    	String mobileNo =userName;
    	System.out.println(mobileNo);
    	Member findByMobileNo = memberRepo.findByMobileNo(mobileNo);
    	if (findByMobileNo != null) {
    		sendOTPMsg = sendOTP(mobileNo);
		}else {
			sendOTPMsg = "mobileNo does not exist";
		}
    	return sendOTPMsg;
    }
    public String sendOTP(String mobileNo) {
        String toPhoneNumber = mobileNo;
        String otp = generateOTP(); // Replace with your OTP generation logic

        twilioService.sendOTP(toPhoneNumber, otp);

        return "OTP sent successfully";
    }
    

    @PostMapping("/verify-otp")
    public Mono<String> verifyOTP(@RequestBody OTPVerificationRequest request) {
        String otpEnteredByUser = request.getOtp();
        
        System.out.println(request);
      Mono<String> validateOTP = twilioService.validateOTP(otpEnteredByUser, request.getUserName());
      
      return validateOTP;

        
    }

    private String generateOTP() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }

	
}
