package com.oscar.one.api.users.service;

import java.util.List;

public interface UsersRoles {
	UsersRoles createUser(UsersRoles usersRole);

	UsersRoles updateUser(UsersRoles usersRole);

	List<UsersRoles> readAll();

	void deleteUser(long id);
}
