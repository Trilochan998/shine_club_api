package com.shineclub.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.shineclub.api.filter.JwtFilter;
import com.shineclub.api.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	 @Autowired
	    private JwtFilter jwtFilter;
	
//	@Autowired
//	CustomPasswordEncoder customPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}	
	
	@Bean
	public PasswordEncoder encodePWD() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	 @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
//	        http.csrf().disable().authorizeRequests().antMatchers("/authenticate")
//	                .permitAll().anyRequest().authenticated()
//	                .and().exceptionHandling().and().sessionManagement()
//	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);;
		 
		 http.cors(cors -> {
			 CorsConfigurationSource cs = resources -> {
				 CorsConfiguration corsConfiguration = new CorsConfiguration();
				 corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:3001"));
				 corsConfiguration.setAllowedMethods(Arrays.asList("POST","GET","PUT","DELETE","OPTIONS"));
				 corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization",
						 "Content-Type",
						 "X-Requested-With",
						 "Accept",
						 "X-XSRF-TOKEN"
						 ));
				 corsConfiguration.setAllowCredentials(true);
				 return corsConfiguration;
			 };
			 cors.configurationSource(cs);
		 });
		 
		 http.csrf().disable().authorizeHttpRequests()
		 			.antMatchers("/saveMemberDetails").permitAll()
		 			.antMatchers("/forgotPassword").permitAll()
		 			.antMatchers("/test").permitAll()
		 			.antMatchers("/verify-otp").permitAll()
		 			.antMatchers("/getAllMember").permitAll() 
		 			.antMatchers("/updataMemberDetails").permitAll() 
		 			.antMatchers("/changePassword").permitAll()
		 			.antMatchers("/student/**").permitAll()
		 			.antMatchers("/transaction/**").permitAll()
		 			.antMatchers("/payment/**").permitAll()
		 			.antMatchers("/savePaymentDetails").permitAll()
		 			.antMatchers("/authenticate")
		 			.permitAll().anyRequest().authenticated()
		 			.and().exceptionHandling().and().sessionManagement()
		 			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

}
