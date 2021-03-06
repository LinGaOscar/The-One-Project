package com.oscar.one.api.users.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByUserName(String username);

	UserEntity findByEmail(String email);

}
