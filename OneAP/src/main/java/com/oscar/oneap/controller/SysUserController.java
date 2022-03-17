package com.oscar.oneap.controller;

import com.oscar.oneap.entity.SysUser;
import com.oscar.oneap.service.SysUserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/users")
public class SysUserController {
    private SysUserServiceImp sysUserServiceImp;

    @Autowired
    public void autoWired(SysUserServiceImp sysUserServiceImp) {
        this.sysUserServiceImp = sysUserServiceImp;
    }

    @GetMapping("/getAllUser")
    @ResponseBody
    public List<SysUser> getAllUser() {
        return sysUserServiceImp.findAll();
    }

    @GetMapping("/getUser/{account}")
    @ResponseBody
    public SysUser getUser(@PathVariable("account") String account) {
        return sysUserServiceImp.findByAccount(account);
    }
}
