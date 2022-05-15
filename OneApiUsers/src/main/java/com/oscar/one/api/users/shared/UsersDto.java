package com.oscar.one.api.users.shared;

import java.io.Serializable;

import com.oscar.one.api.users.data.UsersRoles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto implements Serializable {

	private static final long serialVersionUID = 6830065769350978640L;
	private String userName;
	private String password;
	private String email;
	private String userId;
	private String encrptedPassword;
	private String acconut;
	private Boolean active;
	UsersRoles UsersRoles;
}
