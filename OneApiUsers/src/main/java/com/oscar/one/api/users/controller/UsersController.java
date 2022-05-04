package com.oscar.one.api.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UsersController {

	@GetMapping("/status/check")
	@ResponseBody
	public String status() {
		return "woorking";
	}

}
