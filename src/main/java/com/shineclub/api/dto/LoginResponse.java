package com.shineclub.api.dto;

import com.shineclub.api.entity.Member;

public class LoginResponse {
	private String token;
	private Member member;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	@Override
	public String toString() {
		return "LoginResponse [token=" + token + ", member=" + member + "]";
	}
	
	
}
