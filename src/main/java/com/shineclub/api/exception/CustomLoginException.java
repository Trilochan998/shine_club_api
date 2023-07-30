package com.shineclub.api.exception;

public class CustomLoginException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Invalid user and password";
	}
}
