package com.shineclub.api.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shineclub.api.dto.AllMembers;
import com.shineclub.api.dto.ChangePasswordRequest;
import com.shineclub.api.dto.LoginResponse;
import com.shineclub.api.dto.MemberRequest;
import com.shineclub.api.entity.Address;
import com.shineclub.api.entity.AuthRequest;
import com.shineclub.api.entity.Member;
import com.shineclub.api.entity.Transaction;
import com.shineclub.api.repo.MemberRepo;
import com.shineclub.api.util.JwtUtil;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	ObjectMapper objectMapper;

	private final String FOLDER_PATH = "C:\\new_folder_path\\";

	@Autowired
	MemberRepo memberRepo;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public Member saveMemberDetails(MultipartFile file, String memberRequest) {
		MemberRequest response = null;
		Member member = null;
		Address address = null;
		Transaction trans = null;
		Member memberResponse = null;
		String filPath = FOLDER_PATH + file.getOriginalFilename();
		try {
			response = objectMapper.readValue(memberRequest, MemberRequest.class);
			address = new Address();
			address.setCity(response.getCity());
			address.setDistrict(response.getDistrict());
			address.setVillage(response.getVillage());
			address.setPin(response.getPin());

			trans = new Transaction();
//			trans.setDateOfTransction(response.getDateOfTransction());
//			trans.setTranctionDetails(response.getTranctionDetails());
			List<Transaction> al = new ArrayList<Transaction>();
			al.add(trans);
			member = new Member();
			member.setMemberName(response.getMemberName());
			member.setEmail(response.getEmail());
			member.setGender(response.getGender());
			member.setImagePath("");
			member.setDob(response.getDob());
			member.setMobileNo(response.getMobileNo());
			member.setPassword(response.getPassword());
			member.setAddress(address);
			member.setTransction(al);
			member.setImagePath(filPath);

			file.transferTo(new File(filPath));

//			System.out.println(response);

			System.out.println(member);
			memberResponse = memberRepo.save(member);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberResponse;
	}
	
	@Override
	public Member saveMemberDetailsTestWithDBStore(MultipartFile file, String memberRequest) {
		MemberRequest response = null;
		Member member = null;
		Address address = null;
		Transaction trans = null;
		Member memberResponse = null;
		String filPath = FOLDER_PATH + file.getOriginalFilename();
//		String originalFilename = file.getOriginalFilename();
		try {
			response = objectMapper.readValue(memberRequest, MemberRequest.class);
			address = new Address();
			address.setCity(response.getCity());
			address.setDistrict(response.getDistrict());
			address.setVillage(response.getVillage());
			address.setPin(response.getPin());

			trans = new Transaction();
//			trans.setDateOfTransction(response.getDateOfTransction());
//			trans.setTranctionDetails(response.getTranctionDetails());
			List<Transaction> al = new ArrayList<Transaction>();
			al.add(trans);
			member = new Member();
			member.setMemberName(response.getMemberName());
			member.setEmail(response.getEmail());
			member.setGender(response.getGender());
			member.setImagePath("");
			member.setDob(response.getDob());
			member.setMobileNo(response.getMobileNo());
			member.setPassword(response.getPassword());
			member.setAddress(address);
			member.setTransction(al);
			member.setImagePath(filPath);
			
			//save image into database
			byte[] imageData = file.getBytes();
			member.setData(imageData);
			
			
			file.transferTo(new File(filPath));

//			System.out.println(response);

			System.out.println(member);
			memberResponse = memberRepo.save(member);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberResponse;
	}

	@Override
	public Member saveMemberDetails(MemberRequest response) {
		Member member = null;
		Address address = null;
		Transaction trans = null;
		Member memberResponse = null;
		try {
			address = new Address();
			address.setCity(response.getCity());
			address.setDistrict(response.getDistrict());
			address.setVillage(response.getVillage());
			address.setPin(response.getPin());

			trans = new Transaction();
//			trans.setDateOfTransction(response.getDateOfTransction());
//			trans.setTranctionDetails(response.getTranctionDetails());
			List<Transaction> al = new ArrayList<Transaction>();
			al.add(trans);
			member = new Member();
			member.setMemberName(response.getMemberName());
			member.setEmail(response.getEmail());
			member.setGender(response.getGender());
//			member.setDob(response.getDob());
			member.setMobileNo(response.getMobileNo());
			member.setPassword(response.getPassword());
			member.setAddress(address);
			member.setTransction(al);

//			System.out.println(response);

			System.out.println(member);
			memberResponse = memberRepo.save(member);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberResponse;
	}

	@Override
	public LoginResponse loginUser(AuthRequest authRequest) throws Exception {
		LoginResponse loginResponse = null;
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("inavalid username/password");
		}
		String generateToken = jwtUtil.generateToken(authRequest.getUserName());
		if (generateToken != null) {
			Member member = memberRepo.findByMobileNo(authRequest.getUserName());
			loginResponse = new LoginResponse();
			loginResponse.setToken(generateToken);
			loginResponse.setMember(member);
		}
		return loginResponse;
	}

	@Override
	public String saveImageOfAMember(MultipartFile file, String phoneNumber) {
		System.out.println(phoneNumber);
		String filePath = FOLDER_PATH + file.getOriginalFilename();
		String responseMsg = null;
		Member updatedMember = null;
		Member exeistingMember = memberRepo.findByMobileNo(phoneNumber);
		try {
			if (exeistingMember != null) {
				System.out.println("inside exeistingMember if condition");
				exeistingMember.setImagePath(filePath);
				updatedMember = memberRepo.save(exeistingMember);
				file.transferTo(new File(filePath));

				responseMsg = "Image uploaded successfully," + filePath;
			} else {
				responseMsg = "Member does not exist";
			}
		} catch (IllegalStateException e) {
		} catch (IOException e) {
			// TODO: handle exception
		} catch (Exception e) {
		}
		return responseMsg;
	}

	@Override
	public List<AllMembers> getAllMember() {
		List<AllMembers> allMembers = new ArrayList<AllMembers>();
		List<Member> findAll = memberRepo.findAll();
		findAll.stream().forEach(member -> {
			AllMembers memberResponse = new AllMembers();
			memberResponse.setName(member.getMemberName());
			memberResponse.setImagePath(member.getImagePath());
			memberResponse.setPhoneNo(member.getMobileNo());
			memberResponse.setEmail(member.getEmail());
			memberResponse.setImage(member.getData());
			allMembers.add(memberResponse);
		});
		return allMembers;
	}

	@Override
	public String changePassword(ChangePasswordRequest changePasswordRequest) {
		if (changePasswordRequest.getPassword().equals(changePasswordRequest.getConfirmPassword())) {
			Member findByMobileNo = memberRepo.findByMobileNo(changePasswordRequest.getMobileNo());
			findByMobileNo.setPassword(changePasswordRequest.getPassword());
			memberRepo.save(findByMobileNo);
		} else {
			return "Incorrect Password";
		}
		return "Password Changed Successfully";
	}

	@Override
	public Member updataMemberDetails(MemberRequest memberRequest) {
		System.out.println(memberRequest);
		Member existingMember = memberRepo.findByMobileNo(memberRequest.getMobileNo());
		existingMember.setMemberName(memberRequest.getMemberName());
//		existingMember.setDob(memberRequest.getDob());
		existingMember.setEmail(memberRequest.getEmail());
		existingMember.setMobileNo(memberRequest.getMobileNo());
		Address address = existingMember.getAddress();
		address.setCity(memberRequest.getCity());
		address.setDistrict(memberRequest.getDistrict());
		address.setPin(memberRequest.getPin());
		address.setVillage(memberRequest.getVillage());
		existingMember.setAddress(address);
		Member updataedMember = memberRepo.save(existingMember);
		return updataedMember;
	}

}
