package com.oscar.oneap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @GetMapping("/getMenu")
    @ResponseBody
    public Map<String, String> getMenu(HttpServletRequest request) {
        System.out.println(request.getUserPrincipal());
        System.out.println(request.isUserInRole("ADMIN"));

        Map<String, String> result = new HashMap<>();
        return result;

    }
}
