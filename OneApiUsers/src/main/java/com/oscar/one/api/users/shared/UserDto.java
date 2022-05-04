package com.oscar.one.api.users.shared;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable{

	private static final long serialVersionUID = -7129555910888580381L;
	private String userName;
	private String password;
	private String email;
	private String userId;
	private String encrptedPassword;
}
