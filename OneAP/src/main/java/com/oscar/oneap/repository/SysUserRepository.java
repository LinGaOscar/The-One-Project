package com.oscar.oneap.repository;

import com.oscar.oneap.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    SysUser findByAccount(String account);
}
