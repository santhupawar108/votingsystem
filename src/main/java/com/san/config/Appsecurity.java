package com.san.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Appsecurity  {
	
	
	@Autowired
	private CustomDetailsService customDetailsService;
	
	@Autowired
	private LoginSuccessHanler loginSuccess;
	

	

	@Bean
	public UserDetailsService  userDetailsService() {
		
		return new CustomDetailsService();

		
	}
	

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
		
	} 
	
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/voting/homepage").hasAuthority("USER")
		.requestMatchers("/voting/adminlist").hasAuthority("Admin")
		.requestMatchers("/voting/showform","/voting/save").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/voting/loginpage").successHandler(loginSuccess)
		.permitAll()
		.and().logout().permitAll();
		
		
		
		return httpSecurity.build();
		}	
	
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		
		return provider;
		
		
	}
	
	
} 
