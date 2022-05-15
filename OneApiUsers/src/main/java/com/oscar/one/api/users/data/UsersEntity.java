package com.oscar.one.api.users.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "one_users")
public class UsersEntity implements Serializable {
	private static final long serialVersionUID = 6519033879177884069L;

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

	@Column(name = "account", unique = true)
	private String account;

	@Column(name = "active")
	private Boolean active;

	@ManyToOne
	@OrderBy("id")
	UsersRoles UsersRoles;

}
