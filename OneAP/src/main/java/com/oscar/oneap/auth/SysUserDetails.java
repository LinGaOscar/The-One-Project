package com.oscar.oneap.auth;

import com.oscar.oneap.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SysUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5927749153774824036L;
	private String userName;
	private String password;
	private boolean active;
	private List<GrantedAuthority> authorities;

	public SysUserDetails(SysUser sysUser) {
		//利用account 認證
		this.userName = sysUser.getAccount();
		this.password = sysUser.getPassword();
		this.active = sysUser.isActive();
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
		return active;
	}

	@Override
	public boolean isAccountNonLocked() {
		return active;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return active;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}
}
