package com.oscar.oneap.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
 public class Controller {

    @GetMapping("/login")
    public Map<String, String> login() {
        Map<String, String> result = Map.of("message", "login say hello");
        return result;
    }
     @GetMapping("/admin/hello")
     public Map<String, String> adminSayHello() {
         Map<String, String> result = Map.of("message", "admin say hello");
         return result;
     }
 
     @GetMapping("/user/hello")
     public Map<String, String> userSayHello() {
         Map<String, String> result = Map.of("message", "user say hello");
         return result;
     }
 }