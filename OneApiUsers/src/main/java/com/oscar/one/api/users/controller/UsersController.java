package com.oscar.one.api.users.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.http.MediaType;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oscar.one.api.users.controller.model.CreateUserRequestModel;
import com.oscar.one.api.users.controller.model.CreateUserResponseModel;
import com.oscar.one.api.users.service.UserService;
import com.oscar.one.api.users.service.UsersService;
import com.oscar.one.api.users.shared.UserDto;
import com.oscar.one.api.users.shared.UsersDto;

@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private Environment environment;

	@Autowired
	private UserService userService;

	@Autowired
	private UsersService usersService;

	@GetMapping("/status/check")
	@ResponseBody
	public String status() {
		return "woorking on Port" + environment.getProperty("local.server.port");
	}

	@GetMapping()
	@ResponseBody
	public List<UsersDto> findAll() {
		return usersService.findAll();
	}

	@PostMapping("/save")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> saveUser(@RequestBody CreateUserRequestModel userDetail) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UsersDto usersDto = modelMapper.map(userDetail, UsersDto.class);
		UsersDto createdUser = usersService.createUser(usersDto);
		CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);
		Map<String, Object> result = new HashMap<>();
		result.put("statusDesc", "新增成功");
		result.put("resultData", returnValue);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@PostMapping()
	@ResponseBody
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetail) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto userDto = modelMapper.map(userDetail, UserDto.class);
		UserDto createdUser = userService.createUser(userDto);

		CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
}
