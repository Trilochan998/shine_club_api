package com.shineclub.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import reactor.core.publisher.Mono;

@Service
public class TwilioService {
	@Value("${twilio.account.sid}")
    private String twilioAccountSid;

    @Value("${twilio.auth.token}")
    private String twilioAuthToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;
    
//    Map<String, String> otpMap = new HashMap<>();
    int otp_val = 0;

    public void sendOTP(String toPhoneNumber, String otp) {
        Twilio.init(twilioAccountSid, twilioAuthToken);
        
        PhoneNumber to = new PhoneNumber("+91"+toPhoneNumber);
        PhoneNumber from = new PhoneNumber(twilioPhoneNumber);

        String otpMessage = "Dear Customer , Your OTP is ##" + otp + "##. Use this Passcode to complete your transaction. Thank You.";
        Message message = Message
                .creator(to, from,otpMessage)
                .create();
//        otpMap.put(userName, otp);
        otp_val = Integer.parseInt(otp);
       
    }

    public Mono<String> validateOTP(String otpEnteredByUser, String userName) {
//        if (otpEnteredByUser.equals(otpMap.get(userName))) {
//            otpMap.remove(userName,otpEnteredByUser);
//            return Mono.just("Valid OTP please proceed with your transaction !");
//        } else {
//            return Mono.error(new IllegalArgumentException("Invalid otp please retry !"));
//        }
    	if (Integer.parseInt(otpEnteredByUser) == otp_val) {
            otp_val = 0;
            return Mono.just("Valid OTP please proceed with your transaction !");
        } else {
            return Mono.error(new IllegalArgumentException("Invalid otp please retry !"));
        }
        
    }
}
