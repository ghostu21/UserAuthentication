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
 @JoinColumn(name = "role_id", nullable = false)
 private Role role;

 // Getters and setters
}

