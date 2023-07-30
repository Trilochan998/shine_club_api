package com.shineclub.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shineclub.api.entity.Transaction;

@Service
public interface PaymentService {
	List<Transaction> findTransactionByMember(String mobileNo);
}
