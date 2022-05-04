package com.oscar.one.api.users.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1602380955724977294L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "user_name", nullable = false)
	private String userName;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "userId", nullable = false, unique = true)
	private String userId;

	@Column(name = "encrpte_password", nullable = false)
	private String encrptedPassword;
}
