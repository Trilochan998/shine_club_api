package com.shineclub.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shineclub.api.dto.TransactionDetails;
import com.shineclub.api.entity.Member;
import com.shineclub.api.entity.Transaction;
import com.shineclub.api.repo.MemberRepo;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	MemberRepo memberRepo;
	
	@PostMapping("/saveTransctionDetails")
	public Member saveTransctionDetails(@RequestBody TransactionDetails transactionDetails) {
		Member Updatedmember = null;
		String mobileNo = transactionDetails.getMobileNo();
		Member findByMobileNo = memberRepo.findByMobileNo(mobileNo);
		if (findByMobileNo != null) {
			List<Transaction> transction = findByMobileNo.getTransction();
			Transaction tr = new Transaction();
			tr.setDateOfTransction(transactionDetails.getDateOfTransction());
			transction.add(tr);
			Updatedmember = memberRepo.save(findByMobileNo);
			System.out.println(findByMobileNo);
		}
		
		return Updatedmember;
	}
}
