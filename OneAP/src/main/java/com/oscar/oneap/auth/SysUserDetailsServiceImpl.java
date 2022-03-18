package com.oscar.oneap.auth;

import com.oscar.oneap.entity.SysUser;
import com.oscar.oneap.entity.SysUserRoles;
import com.oscar.oneap.repository.SysUserRepository;
import com.oscar.oneap.service.SysUserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class SysUserDetailsServiceImpl implements UserDetailsService {

    private SysUserServiceImp sysUserServiceImp;

    @Autowired
    public void autoWired(SysUserServiceImp sysUserServiceImp) {
        this.sysUserServiceImp = sysUserServiceImp;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserServiceImp.findByUserName(username);
        System.out.println("ssss "+username);
        System.out.println("ssss "+sysUser);
        if (sysUser == null) {
            new UsernameNotFoundException("Not found :" + username);
        }

        return new SysUserDetails(sysUser);
    }

    public UserDetails loadUserByAccount(String account) throws AccountNotFoundException {
        SysUser sysUser = sysUserServiceImp.findByAccount(account);

        if (sysUser == null) {
            new AccountNotFoundException("Not found :" + account);
        }

        return new SysUserDetails(sysUser);
    }
}
