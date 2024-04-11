package com.mayank.UserAuthenication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.mayank.UserAuthenication.Dto.AuthResponseTO;
import com.mayank.UserAuthenication.Dto.CreateUserRequestTO;
import com.mayank.UserAuthenication.Dto.GetUserResponseTO;
import com.mayank.UserAuthenication.Dto.LoginRequestTO;

import com.mayank.UserAuthenication.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
//@RequestMapping("/user")
public class UserController {
 
 private static final Logger logger = LoggerFactory.getLogger(UserController.class);

 @Autowired
 private UserService userService;

 


 @PostMapping("/register")
 public ResponseEntity<AuthResponseTO> registerUser(@RequestBody CreateUserRequestTO userDTO) {
	 
	 
	 logger.info("Registartion Start");
    	 AuthResponseTO authResponse =userService.registerUser(userDTO);
    	 logger.info("User registered successfully: {}", userDTO.getUserName());
    	 
    	 logger.info("Registartion End");
    	 return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
     
     
 }
 
 @PostMapping("/signin")
 public ResponseEntity<AuthResponseTO> singnin(@RequestBody LoginRequestTO req){
	 
	 AuthResponseTO authResponse =userService.userSignIn(req);
	 return new ResponseEntity<>(authResponse, HttpStatus.OK);
	
	 
 }
 
 
 
 
 @GetMapping("/user")
 public ResponseEntity<?> getUserDetail(@RequestHeader ("Authorization") String jwt){
	 
	GetUserResponseTO userResponse= userService.getUserDetails(jwt);
	
	return new ResponseEntity<>(userResponse, HttpStatus.OK);
 }
 

}
