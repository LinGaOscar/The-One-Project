package com.oscar.one.api.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.oscar.one.api.users.shared.UserDto;

public interface UserService extends UserDetailsService{
	
	UserDto createUser(UserDto userDetails);

}
