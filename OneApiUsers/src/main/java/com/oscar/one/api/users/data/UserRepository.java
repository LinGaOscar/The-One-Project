package com.oscar.one.api.users.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscar.one.api.users.shared.UserDto;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByUserName(String username);

	UserEntity findByEmail(String email);

}
