package com.oscar.oneap.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Entity
@Table(name = "sys_user")
public class SysUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private boolean active;

    @ManyToOne
    @OrderBy("id")
    SysUserRoles sysUserRoles;

//    @CreatedBy
//    @Column(name = "created_user")
//    private String createdUser;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8", locale = "zh_TW")
//    @CreatedDate
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "created_datetime")
//    private Date createdDatetime;
//
//    @LastModifiedBy
//    @Column(name = "update_user")
//    private String updateUser;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8", locale = "zh_TW")
//    @LastModifiedDate
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "updated_datetime")
//    private Date updatedDatetime;
}
