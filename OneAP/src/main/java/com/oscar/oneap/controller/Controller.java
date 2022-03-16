package com.oscar.oneap.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
 public class Controller {
 
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