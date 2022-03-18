package com.oscar.oneap;

import com.oscar.oneap.entity.SysUser;
import com.oscar.oneap.repository.SysUserRepository;
import com.oscar.oneap.repository.SysUserRolesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JPATest {


    private SysUserRepository sysUserRepository;
    private SysUserRolesRepository sysUserRolesRepository;

    @Autowired
    public void autoWired(SysUserRepository sysUserRepository, SysUserRolesRepository sysUserRolesRepository) {
        this.sysUserRepository = sysUserRepository;
        this.sysUserRolesRepository = sysUserRolesRepository;
    }

    @Test
    public void add() {

        SysUser sysUser = new SysUser();
        sysUser.setActive(true);
        sysUser.setUserName("Oscar");
        sysUser.setAccount("21053064");
        sysUser.setPassword("0000");

        sysUser.setId(1L);
        sysUserRepository.save(sysUser);
    }

    @Test
    public void show() {
        System.out.println(sysUserRolesRepository.findAll());
    }
}
