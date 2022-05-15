package com.oscar.oneap.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class SuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Autowired
	private Environment env;

	private final String LOGGED_IN = "logged_in";
	private final String USER_ROLE = "user_type";

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String account = authentication.getName();
		Collection<?> collection = authentication.getAuthorities();
		String authority = collection.iterator().next().toString();
		HttpSession session = request.getSession();
		session.setAttribute(LOGGED_IN, account);
		session.setAttribute(USER_ROLE, authority);

		String token = Jwts.builder().setSubject(account).setExpiration(new Date(System.currentTimeMillis() + 86400000))
				.signWith(SignatureAlgorithm.HS512, "Serect Sign").compact();
		System.out.print(token);
		response.addHeader("token", token);

		Map<String, Object> resultData = new HashMap<>();
		resultData.put("name", account);
		resultData.put("authority", authority);

		Map<String, Object> result = new HashMap<>();
		result.put("statusCode", "200");
		result.put("statusDesc", "登入成功");
		result.put("resultData", resultData);

		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);
		PrintWriter out = response.getWriter();
		ObjectMapper om = new ObjectMapper();
		out.write(om.writeValueAsString(result));
		out.flush();
		out.close();
	}
}