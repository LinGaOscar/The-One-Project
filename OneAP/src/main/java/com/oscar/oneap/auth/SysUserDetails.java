package com.oscar.oneap.auth;

import com.oscar.oneap.entity.SysUser;
import com.oscar.oneap.entity.SysUserRoles;
import com.oscar.oneap.service.SysUserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SysUserDetails implements UserDetails {

    private String userName;
    private String password;
    private String account;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public SysUserDetails(SysUser sysUser) {
        this.userName = sysUser.getUserName();
        this.password = sysUser.getPassword();
        this.active = sysUser.isActive();
        this.account = sysUser.getAccount();
        this.authorities = Arrays.stream(sysUser.getSysUserRoles().getRoles().split(","))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//        System.out.println(authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
