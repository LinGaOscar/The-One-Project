package com.oscar.oneap.auth;

import com.oscar.oneap.entity.SysUser;
import com.oscar.oneap.entity.SysUserRoles;
import com.oscar.oneap.repository.SysUserRepository;
import com.oscar.oneap.service.SysUserService;
import com.oscar.oneap.service.SysUserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;

@Service
public class SysUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.findByAccount(username);
		if (sysUser == null) {
            throw new UsernameNotFoundException("Not found :" + username);
        }

        return new SysUserDetails(sysUser);
//    	return new User("Oscar","0000",new ArrayList<>());
    }

}
