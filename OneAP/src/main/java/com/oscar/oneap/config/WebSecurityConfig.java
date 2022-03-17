package com.oscar.oneap.config;

import java.io.PrintWriter;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oscar.oneap.auth.AccessDeniedHandlerImpl;
import com.oscar.oneap.auth.EntryPointImpl;
import com.oscar.oneap.auth.FailureHandlerImpl;
import com.oscar.oneap.auth.LoginAuthenticationFilter;
import com.oscar.oneap.auth.SuccessHandlerImpl;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().
		
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
				.addFilterAt(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
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
	 @Bean
     LoginAuthenticationFilter loginAuthenticationFilter() throws Exception {
         LoginAuthenticationFilter filter = new LoginAuthenticationFilter();
         filter.setAuthenticationManager(authenticationManagerBean());
         filter.setAuthenticationSuccessHandler(new SuccessHandlerImpl());
         filter.setAuthenticationFailureHandler(new FailureHandlerImpl());
         filter.setFilterProcessesUrl("/api/login");
         return filter;
     }
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
