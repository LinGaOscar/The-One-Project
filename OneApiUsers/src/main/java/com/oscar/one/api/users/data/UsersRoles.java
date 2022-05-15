package com.oscar.one.api.users.data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "sys_user_roles")
public class UsersRoles implements Serializable {
	private static final long serialVersionUID = 5471013861821497868L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "rank")
	private String rank;

	@Column(name = "functions")
	private String functions;

	@Column(name = "roles")
	private String roles;
}
