package com.oscar.one.api.users.controller.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestModel {

	@NotNull(message = "User Name cannot be noll")
	@Size(min = 2, message = "User Name must not be less 2 lenght")
	private String userName;
	
	@NotNull(message = "Password cannot be noll")
	@Size(min = 8, max = 16, message = "Password  must not be less 8 and grater 16")
	private String password;
	
	@Email
	private String email;
	
	private String account;
}
