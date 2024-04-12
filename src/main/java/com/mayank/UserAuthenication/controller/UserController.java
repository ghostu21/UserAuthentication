package com.mayank.UserAuthenication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mayank.UserAuthenication.Dto.AuthResponseTO;
import com.mayank.UserAuthenication.Dto.CreateUserRequestTO;
import com.mayank.UserAuthenication.Dto.GetUserResponseTO;
import com.mayank.UserAuthenication.Dto.LoginRequestTO;
import com.mayank.UserAuthenication.Dto.UpdateUserRequestTO;
import com.mayank.UserAuthenication.service.UserService;
import com.mayank.UserAuthenication.validator.MobileandEmailValidator;

@RestController
//@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// Public API's

	@PostMapping("/register")
	public ResponseEntity<AuthResponseTO> registerUser(@RequestBody CreateUserRequestTO userDTO) {

//	 GenericValidator.validate(userDTO);

		// Validating mobile and email
		MobileandEmailValidator.isValidEmail(userDTO.getEmail());
		MobileandEmailValidator.isValidMobileNumber(userDTO.getMobile());

//	 	logger.info("Registartion Start");
		AuthResponseTO authResponse = userService.registerUser(userDTO);
		logger.info("User registered successfully: {}", userDTO.getUserName());

//    	 logger.info("Registartion End");
		return new ResponseEntity<>(authResponse, HttpStatus.CREATED);

	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponseTO> singnin(@RequestBody LoginRequestTO req) {

		logger.info("Login Start: {}", req.getEmail());
		AuthResponseTO authResponse = userService.userSignIn(req);
		logger.info("Login End: {}", req.getEmail());
		return new ResponseEntity<>(authResponse, HttpStatus.OK);

	}

	// Private API's

	@GetMapping("/user")
	public ResponseEntity<?> getUserDetail(@RequestHeader("Authorization") String jwt) {
		logger.info("Get User Profile Start");

		GetUserResponseTO userResponse = userService.getUserDetails(jwt);
		logger.info("Get User Profile:{}", userResponse.getEmail());

		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}

	@PostMapping("/user")
	public ResponseEntity<String> updateUserDetail(@RequestHeader("Authorization") String jwt,
			@RequestBody UpdateUserRequestTO req) {

		userService.updateUser(jwt, req);

		return new ResponseEntity<>("User Updated...", HttpStatus.OK);
	}

}
