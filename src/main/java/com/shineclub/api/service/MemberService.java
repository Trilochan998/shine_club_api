package com.shineclub.api.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shineclub.api.dto.AllMembers;
import com.shineclub.api.dto.ChangePasswordRequest;
import com.shineclub.api.dto.LoginResponse;
import com.shineclub.api.dto.MemberRequest;
import com.shineclub.api.entity.AuthRequest;
import com.shineclub.api.entity.Member;

public interface MemberService {

	Member saveMemberDetails(MultipartFile file, String memberRequest);

	LoginResponse loginUser(AuthRequest authRequest) throws Exception;

	Member saveMemberDetails(MemberRequest memberRequest);

	String saveImageOfAMember(MultipartFile file, String phoneNumber);

	List<AllMembers> getAllMember();

	String changePassword(ChangePasswordRequest changePasswordRequest);

	Member updataMemberDetails(MemberRequest memberRequest);

	Member saveMemberDetailsTestWithDBStore(MultipartFile file, String memberRequest);

	
}
