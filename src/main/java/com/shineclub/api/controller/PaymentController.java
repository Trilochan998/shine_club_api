package com.shineclub.api.controller;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.shineclub.api.dto.PaymentDetails;
import com.shineclub.api.dto.PaymentRequest;
import com.shineclub.api.dto.PaymentResponse;
import com.shineclub.api.entity.Member;
import com.shineclub.api.entity.Transaction;
import com.shineclub.api.repo.MemberRepo;
import com.shineclub.api.service.PaymentService;

@RestController
@RequestMapping("/payment")
//@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin
public class PaymentController {
	
	private RazorpayClient client;
	private static final String key = "rzp_test_J6KLC9nTIQuYCx";
	private static final String secretId = "uptT7TDfYFR5vWlKQxYLHPjD";
	
	    @Autowired
		MemberRepo memberRepo;
	    
	    @Autowired
	    PaymentService paymentService;
	
	@PostMapping("/generatePayment")
	public PaymentResponse createOrder(@RequestBody PaymentRequest orderRequest) {
		PaymentResponse orderResponse = new PaymentResponse();
		System.out.println(orderRequest);
		try {
			client = new RazorpayClient(key, secretId);
			
			Order order = createRazorPayOrder(orderRequest.getAmount());
			
			String orderId =(String) order.get("id");
			orderResponse.setRazorPayOrderId(orderId);
			orderResponse.setSecretId(secretId);
			orderResponse.setSecretKey(key);
			orderResponse.setApplicationFee(" "+ orderRequest.getAmount());
			System.out.println(orderResponse);
			
			return orderResponse;
			
		} catch (RazorpayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderResponse;
	}
	
	@GetMapping("/successOrder")
	public String successOrder() {
		System.out.println("Payment Successfully Done");
		return "Payment Successfully Done";
	}

	private Order createRazorPayOrder(BigInteger amount) throws RazorpayException {
		
		JSONObject options = new JSONObject();
		options.put("amount", amount.multiply(new BigInteger("100")));
		options.put("currency", "INR");
		options.put("receipt", "txn_12345");
		options.put("payment_capture", 1);
		return client.orders.create(options);
	}
	
	@PostMapping("/savePaymentDetails")
	public Member savePaymentDetails(@RequestBody PaymentDetails paymentDetails) {
		Member updatedmember = null;
		Member memberData = memberRepo.findByMobileNo(paymentDetails.getMobileNo());
		System.out.println("paymentDetails: "+paymentDetails);
		if (memberData != null) {
			List<Transaction> transction = memberData.getTransction();
			Transaction tr = new Transaction();
			Date utilDate = new Date();
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(utilDate);
	        int currentYear = calendar.get(Calendar.YEAR);
	        int currentMonth = calendar.get(Calendar.MONTH) + 1;
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//			SimpleDateFormat todaysDate = new SimpleDateFormat("yyyy-MM-dd");
			tr.setDateOfTransction(sqlDate);
			tr.setAmount(paymentDetails.getApplicationFee());
			tr.setCustomerName(paymentDetails.getCustomerName());
			tr.setMonth(currentMonth);
			tr.setPaymentStatus("success");
			tr.setCustomerName(paymentDetails.getCustomerName());
			tr.setRazorpayOrderId(paymentDetails.getRazorpay_order_id());
			tr.setYear(currentYear);
			tr.setRazorpaySignature(paymentDetails.getRazorpay_signature());
			tr.setRozerpayPaymentId(paymentDetails.getRazorpay_payment_id());
			tr.setPhoneNo(paymentDetails.getMobileNo());
			transction.add(tr);
			updatedmember = memberRepo.save(memberData);
			System.out.println(updatedmember);
		}
		
		return updatedmember;
		
	}
	@GetMapping("/findTransactionByMember")
	public List<Transaction> findTransactionByMember(@RequestParam String mobileNo) {
		List<Transaction> findTransactionByMember = paymentService.findTransactionByMember(mobileNo);
		return findTransactionByMember;
	}

}
