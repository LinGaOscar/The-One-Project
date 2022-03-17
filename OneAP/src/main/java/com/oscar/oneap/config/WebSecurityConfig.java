package com.oscar.oneap.config;

import java.io.PrintWriter;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oscar.oneap.auth.AccessDeniedHandlerImpl;
import com.oscar.oneap.auth.EntryPointImpl;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("admin").password("123").roles("ADMIN", "USER")
		.and()
		.withUser("user").password("123").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().authenticationEntryPoint(new EntryPointImpl())
				.accessDeniedHandler(new AccessDeniedHandlerImpl())
				.and()
				.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
				.antMatchers("/").permitAll()
				.and()
				.logout()
				.logoutUrl("/logout")
				.invalidateHttpSession(true)
				.logoutSuccessHandler((req, resp, auth) -> {
					resp.setContentType("application/json;charset=UTF-8");
					PrintWriter out = resp.getWriter();
					resp.setStatus(200);
					Map<String, String> result = Map.of("message", "登出成功");
					ObjectMapper om = new ObjectMapper();
					out.write(om.writeValueAsString(result));
					out.flush();
					out.close();
				})
				.and()
				.csrf().disable();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
