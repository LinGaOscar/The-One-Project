package com.oscar.oneap.service;

import com.oscar.oneap.entity.SysUser;

import java.util.List;
import java.util.Optional;

public interface SysUserService {

    SysUser saveUser(SysUser sysUser);

    SysUser updateUser(SysUser sysUser);

    List<SysUser> findAll();

    void deleteUser(long id);

    SysUser findById(long id);

    SysUser findByUserName(String username);

    SysUser findByAccount(String account);
}
