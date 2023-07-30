package com.shineclub.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shineclub.api.dto.AllMembers;
import com.shineclub.api.dto.ChangePasswordRequest;
import com.shineclub.api.dto.LoginResponse;
import com.shineclub.api.dto.MemberRequest;
import com.shineclub.api.dto.MemberResponse;
import com.shineclub.api.dto.ResponseStructure;
import com.shineclub.api.entity.AuthRequest;
import com.shineclub.api.entity.Member;
import com.shineclub.api.exception.CustomLoginException;
import com.shineclub.api.service.MemberService;
import com.shineclub.api.util.JwtUtil;

@RestController
//@CrossOrigin(origins = "http://localhost:3001")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@PostMapping("/saveMemberDetails")
	public ResponseEntity<Member> saveMemberDetails(@RequestParam("file") MultipartFile file, @RequestParam("memberName") String memberRequest) {
		System.out.println(memberRequest);
		Member saveMemberDetails = memberService.saveMemberDetailsTestWithDBStore(file, memberRequest);
		return ResponseEntity.ok(saveMemberDetails);
	}
	@PutMapping("/updataMemberDetails")
	public ResponseEntity<Member> updataMemberDetails(@RequestBody MemberRequest memberRequest){
		Member updataMemberDetails = memberService.updataMemberDetails(memberRequest);
		return ResponseEntity.ok(updataMemberDetails);
		
	}
	
//	@PostMapping("/saveMemberDetails")
//	public ResponseEntity<Member> saveMemberDetails(@RequestBody MemberRequest memberRequest) {
//		Member saveMemberDetails = memberService.saveMemberDetails(memberRequest);
//		return ResponseEntity.ok(saveMemberDetails);
//	}
	
	@PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		LoginResponse loginUserResponse = null;
  		try {
  			loginUserResponse = memberService.loginUser(authRequest);
		} catch (Exception e) {
			throw new CustomLoginException();
		}
		
		return ResponseEntity.ok(loginUserResponse);
        
    }
	
	@PostMapping("/saveImageOfAMember")
	public ResponseEntity<String> saveImageOfAMember(@RequestParam("file") MultipartFile file, @RequestParam String phoneNumber) {
		System.out.println("before save image");
		String saveMemberDetails = memberService.saveImageOfAMember(file, phoneNumber);
		return ResponseEntity.ok(saveMemberDetails);
	}
	
	@GetMapping("/getAllMember")
    public ResponseEntity<List<AllMembers>> getAllMember() throws Exception {
		List<AllMembers> allMember = memberService.getAllMember();
        return ResponseEntity.ok(allMember);
    }
	
	@PostMapping("/changePassword")
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
		String changedPassword = memberService.changePassword(changePasswordRequest);
		return ResponseEntity.ok(changedPassword);
	}

}
