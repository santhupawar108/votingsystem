package com.san.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHanler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Userprincipal customDetailsService = (Userprincipal) authentication.getPrincipal();
		System.out.println("UserName "+customDetailsService.getUsername());
		Collection<? extends GrantedAuthority>authorities =customDetailsService.getAuthorities();
		authorities.forEach(auth ->System.out.println(auth.getAuthority()));
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
