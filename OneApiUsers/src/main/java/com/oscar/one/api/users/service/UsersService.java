package com.oscar.one.api.users.service;

import java.util.List;

import com.oscar.one.api.users.shared.UsersDto;

public interface UsersService {
	UsersDto createUser(UsersDto Users);

	UsersDto updateUser(UsersDto Users);

	List<UsersDto> findAll();

	void deleteUser(long id);
	
	UsersDto findByUserName(String username);

	UsersDto findByAccount(String account);

	UsersDto findByEmail(String email);
}
