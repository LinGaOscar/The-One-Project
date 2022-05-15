package com.oscar.one.api.users.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

	UsersEntity findByUserName(String username);

	UsersEntity findByAccount(String account);

	UsersEntity findByEmail(String email);
}
