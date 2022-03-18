package com.oscar.oneap.service;

import com.oscar.oneap.entity.SysUserRoles;
import com.oscar.oneap.repository.SysUserRepository;
import com.oscar.oneap.repository.SysUserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SysUserRolesServiceImpl implements SysUserRolesService{

    private SysUserRolesRepository sysUserRolesRepository;

    @Autowired
    public void autoWired(SysUserRolesRepository sysUserRolesRepository) {
        this.sysUserRolesRepository = sysUserRolesRepository;
    }

    @Override
    public SysUserRoles saveUser(SysUserRoles sysUserRoles) {
        return sysUserRolesRepository.save(sysUserRoles);
    }
}
