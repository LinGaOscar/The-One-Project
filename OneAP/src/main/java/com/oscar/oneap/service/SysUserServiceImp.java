package com.oscar.oneap.service;

import com.oscar.oneap.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oscar.oneap.repository.SysUserRepository;

import java.util.List;
import java.util.Optional;

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
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setActive(false);
        sysUserRepository.save(sysUser);
    }

    @Override
    public SysUser findById(long id) {
        return sysUserRepository.findById(id).orElse(null);
    }

    @Override
    public SysUser findByUserName(String username) {
        return sysUserRepository.findByUserName(username);
    }

    @Override
    public SysUser findByAccount(String account) {
        return sysUserRepository.findByAccount(account);
    }
}
