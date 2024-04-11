package com.mayank.UserAuthenication.model;

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

 @Column(name = "user_name",nullable = false)
 private String userName;

 @Column(name = "email",nullable = false, unique = true)
 private String email;

 @Column(name = "password",nullable = false)
 private String password;

 @Column(name = "mobile",nullable = false)
 private String mobile;

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



 // Getters and setters
}

