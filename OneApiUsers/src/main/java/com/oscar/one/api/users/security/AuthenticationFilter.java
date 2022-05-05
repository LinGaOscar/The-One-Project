package com.oscar.one.api.users.security;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oscar.one.api.users.service.UserService;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private UserService userService;
	private Environment environmernt;
	
	@Autowired
	public AuthenticationFilter(UserService userService, Environment environmernt) {
		this.userService = userService;
		this.environmernt = environmernt;
	}

	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		UsernamePasswordAuthenticationToken authRequest = null;
		try {
			Map<String, String> body = new ObjectMapper().readValue(request.getInputStream(), Map.class);
			authRequest = new UsernamePasswordAuthenticationToken(body.get("userName"), body.get("password"));
		} catch (IOException e) {
			authRequest = new UsernamePasswordAuthenticationToken("", "");
//			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			setDetails(request, authRequest);
			return this.getAuthenticationManager().authenticate(authRequest);
		}
	}

}
