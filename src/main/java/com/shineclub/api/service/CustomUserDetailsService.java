package com.shineclub.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shineclub.api.entity.Member;
import com.shineclub.api.repo.MemberRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberRepo repositary;
	
	//It will load the userName from database. so to connect database we need to call repositary
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member user = repositary.findByMobileNo(username);
		
		return new org.springframework.security.core.userdetails.User(user.getMobileNo(), user.getPassword(), new ArrayList<>());
	}
}
