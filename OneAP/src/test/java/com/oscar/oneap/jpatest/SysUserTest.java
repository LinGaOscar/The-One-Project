package com.oscar.oneap.jpatest;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.oscar.oneap.entity.SysUser;
import com.oscar.oneap.entity.SysUserRoles;
import com.oscar.oneap.repository.SysUserRepository;
import com.oscar.oneap.repository.SysUserRolesRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SysUserTest {

	private SysUserRepository sysUserRepository;
	private SysUserRolesRepository sysUserRolesRepository;

	@Autowired
	public void autoWired(SysUserRepository sysUserRepository, SysUserRolesRepository sysUserRolesRepository) {
		this.sysUserRepository = sysUserRepository;
		this.sysUserRolesRepository = sysUserRolesRepository;
	}

	@Test
	public void add() {

//		SysUserRoles sysUserRoles = new SysUserRoles();
//		SysUser sysUser = new SysUser();
//		sysUser.setAccount("21053060");
//		sysUser.setPassword("0000");
//		sysUser.setUserName("Luka");
//		sysUser.setCreatedDatetime(new Date());
//		sysUserRepository.save(sysUser);
	}

	@Test
	public void show() {

//		System.out.println(sysUserRepository.findAll());
		System.out.println(sysUserRepository.findByAccount("21053064").getUserName());
	}
}
