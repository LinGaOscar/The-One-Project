package com.oscar.oneap.controller;

import com.oscar.oneap.entity.SysUser;
import com.oscar.oneap.service.SysUserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

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
    public List<SysUser> getAllUser(HttpServletRequest request) {
//        System.out.println(request.getCookies());
//        System.out.println(request.getSession());
        System.out.println();
        System.out.println(request.getUserPrincipal());
        System.out.println(request.isUserInRole("ADMIN"));
        return sysUserServiceImp.findAll();


    }
}
