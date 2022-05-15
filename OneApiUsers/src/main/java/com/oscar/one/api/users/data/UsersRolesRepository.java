package com.oscar.one.api.users.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRolesRepository extends JpaRepository<UsersRoles, Long> {
	
	UsersRoles findByRoles(String roles);

}
