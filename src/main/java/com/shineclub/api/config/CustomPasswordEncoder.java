package com.shineclub.api.config;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@Configuration
public class CustomPasswordEncoder {
	
//	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

}
