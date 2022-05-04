package com.oscar.one.api.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@GetMapping("/status/check")
	@ResponseBody
	public String status() {
		return "woorking !";
	}

}
