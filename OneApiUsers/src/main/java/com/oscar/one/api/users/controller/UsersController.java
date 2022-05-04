package com.oscar.one.api.users.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.oscar.one.api.users.controller.model.CreateUserRequestModel;
import com.oscar.one.api.users.service.UserService;
import com.oscar.one.api.users.shared.UserDto;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/status/check")
	@ResponseBody
	public String status() {
		return "woorking on Port" + environment.getProperty("local.server.port");
	}

	@PostMapping()
	@ResponseBody
	public String createUser(@Valid @RequestBody CreateUserRequestModel userDetail) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto = modelMapper.map(userDetail, UserDto.class);
		userService.createUser(userDto);
		
		return "Create user method";
	}
}
