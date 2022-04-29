package com.oscar.oneweb.controller;

import com.oscar.oneweb.bean.UserBean;
import com.oscar.oneweb.outbound.ResponseObject;
import org.apache.catalina.User;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping("/login")
    public void login(UserBean userBean, Model model) {
        System.out.println(userBean);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity<>(userBean, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseObject responseObject = restTemplate.postForObject("http://localhost:8080/api/login",request,ResponseObject.class);
        System.out.println(responseObject);
    }
}
