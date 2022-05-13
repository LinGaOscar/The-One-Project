package com.oscar.oneap.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.oscar.oneap.auth.SysUserDetailsServiceImpl;
import com.oscar.oneap.auth.model.AuthenticationRequest;
import com.oscar.oneap.auth.model.AuthenticationResponse;
import com.oscar.oneap.auth.util.JwtUtil;

@RestController
public class Controller {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private SysUserDetailsServiceImpl sysUserDetailsService;
	@Autowired
	private JwtUtil jwtTokenUtil;

	@GetMapping("/")
	public Map<String, String> SayHello() {
		Map<String, String> result = Map.of("message", "say hello");
		return result;
	}
	
	@GetMapping("/user/hello")
	public Map<String, String> userSayHello(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = String.valueOf(session.getAttribute("logged_in"));
		System.out.print(name);
		Map<String, String> result = new HashMap<>();
		result.put("message", "user say hello");
		result.put("message2", name);
		return result;
	}

	@GetMapping("/admin/hello")
	public Map<String, String> adminSayHello() {
		Map<String, String> result = Map.of("message", "admin say hello");
		return result;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));

		} catch (BadCredentialsException e) {
			throw new Exception("incorrect user name", e);
		}

		final UserDetails userDetails = sysUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}