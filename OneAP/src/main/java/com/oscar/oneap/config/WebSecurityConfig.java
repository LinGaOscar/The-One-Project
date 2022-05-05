package com.oscar.oneap.config;

import java.io.PrintWriter;
import java.util.Map;

import com.oscar.oneap.auth.*;
import com.oscar.oneap.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.sql.DataSource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	

	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);

//        auth.inMemoryAuthentication()
//                .withUser("admin").password("123").roles("ADMIN", "USER")
//                .and()
//                .withUser("user").password("123").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().authenticationEntryPoint(new EntryPointImpl())
				.accessDeniedHandler(new AccessDeniedHandlerImpl()).and()
				.addFilterBefore(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/user/**")
				.hasAnyRole("ADMIN", "USER").antMatchers("/").permitAll().and()
//                .formLogin()
//                .loginProcessingUrl("/login")
//                .usernameParameter("account")
//                .passwordParameter("password")
//                .successHandler( new SuccessHandlerImpl() )
//                .failureHandler( new FailureHandlerImpl() )
//                .and()
				.logout().logoutUrl("/logout").invalidateHttpSession(true).logoutSuccessHandler((req, resp, auth) -> {
					resp.setContentType("application/json;charset=UTF-8");
					PrintWriter out = resp.getWriter();
					resp.setStatus(200);
					Map<String, String> result = Map.of("message", "登出成功");
					ObjectMapper om = new ObjectMapper();
					out.write(om.writeValueAsString(result));
					out.flush();
					out.close();
				}).and().csrf().disable();
	}

	private LoginAuthenticationFilter loginAuthenticationFilter() throws Exception {
		LoginAuthenticationFilter filter = new LoginAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(new SuccessHandlerImpl());
		filter.setAuthenticationFailureHandler(new FailureHandlerImpl());
		filter.setFilterProcessesUrl("/login");
		return filter;
	}

}
