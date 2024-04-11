package com.mayank.UserAuthenication.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//User.java
@Entity
@Table(name = "users")
@Data

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @Column(name = "user_name",nullable = false,unique = true)
 private String userName;

 @Column(name = "email",nullable = false, unique = true)
 private String email;

 @Column(name = "password",nullable = false)
 private String password;

 @Column(name = "mobile",nullable = false,unique = true)
 private String mobile;
 
 @Column(name = "name",nullable = false)
 private String name;
 
 @Column(name = "dob",nullable = false)
 private Date dob;
 
 @Column(name = "created_time",nullable = false)
 private Date createdTime;
 
 @Column(name = "last_modified_time",nullable = false)
 private Date lastModifiedTime;
 

 @ManyToOne(fetch = FetchType.EAGER)
 @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
 private Role role;
 
 

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getMobile() {
	return mobile;
}

public void setMobile(String mobile) {
	this.mobile = mobile;
}

public Role getRole() {
	return role;
}

public void setRole(Role role) {
	this.role = role;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Date getDob() {
	return dob;
}

public void setDob(Date dob) {
	this.dob = dob;
}

public Date getCreatedTime() {
	return createdTime;
}

public void setCreatedTime(Date createdTime) {
	this.createdTime = createdTime;
}

public Date getLastModifiedTime() {
	return lastModifiedTime;
}

public void setLastModifiedTime(Date lastModifiedTime) {
	this.lastModifiedTime = lastModifiedTime;
}



 // Getters and setters
}

