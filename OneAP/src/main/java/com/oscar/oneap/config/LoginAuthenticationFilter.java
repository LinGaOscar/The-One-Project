package com.oscar.oneap.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
            if (request.getContentType().equals("application/json;charset=UTF-8") ||
                    request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
                ObjectMapper mapper = new ObjectMapper();
                UsernamePasswordAuthenticationToken authRequest = null;
                try (InputStream stream = request.getInputStream()) {
                    Map<Object,String> body = mapper.readValue(stream, Map.class);
                    authRequest = new UsernamePasswordAuthenticationToken(
                            body.get("account"), body.get("password")
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                    authRequest = new UsernamePasswordAuthenticationToken("", "");
                } finally {
                    setDetails(request, authRequest);
                    return this.getAuthenticationManager().authenticate(authRequest);
                }
            }
            else {
                return super.attemptAuthentication(request, response);
            }
        }
    }