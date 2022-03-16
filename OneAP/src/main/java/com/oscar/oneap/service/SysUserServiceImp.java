package com.oscar.oneap.service;

import com.oscar.oneap.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oscar.oneap.repository.SysUserRepository;

import java.util.List;

@Service
public class SysUserServiceImp implements SysUserService {

    private SysUserRepository sysUserRepository;

    @Autowired
    public void autoWired(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    @Override
    public SysUser saveUser(SysUser sysUser) {
        return sysUserRepository.save(sysUser);
    }

    @Override
    public SysUser updateUser(SysUser sysUser) {
        return sysUserRepository.save(sysUser);
    }

    @Override
    public List<SysUser> findAll() {
        return sysUserRepository.findAll();
    }

    @Override
    public void deleteUser(long id) {
        
    }

    @Override
    public SysUser findById(long id) {
        return sysUserRepository.findById(id).orElse(null);
    }

}
