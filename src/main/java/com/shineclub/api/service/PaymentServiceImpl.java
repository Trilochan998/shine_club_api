package com.shineclub.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shineclub.api.entity.Member;
import com.shineclub.api.entity.Transaction;
import com.shineclub.api.repo.MemberRepo;

@Repository
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	MemberRepo memberrepo;

	@Override
	public List<Transaction> findTransactionByMember(String mobileNo) {
		Member memberdata = memberrepo.findByMobileNo(mobileNo);
		List<Transaction> transction = memberdata.getTransction();
		System.out.println(transction);
		return transction;
	}

}
