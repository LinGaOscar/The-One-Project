package com.oscar.oneweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class pageController {
    @GetMapping({"/", "/index"})
    public String indexPage() {
        return "index";
    }
}
