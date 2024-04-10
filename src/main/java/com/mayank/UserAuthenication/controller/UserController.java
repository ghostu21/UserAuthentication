package com.mayank.UserAuthenication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mayank.UserAuthenication.Dto.UserDTO;
import com.mayank.UserAuthenication.Exception.EmailExistsException;
import com.mayank.UserAuthenication.Exception.MobileExistsException;
import com.mayank.UserAuthenication.Exception.UsernameExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
public class UserController {
 
 private static final Logger logger = LoggerFactory.getLogger(UserController.class);

// @Autowired
// private UserService userService;

 @PostMapping("/register")
 public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
     try {
//         userService.registerUser(userDTO);
//         logger.info("User registered successfully: {}", userDTO.getUserName());
         return ResponseEntity.ok("User registered successfully");
     } catch (UsernameExistsException | EmailExistsException | MobileExistsException ex) {
         logger.error("Error registering user: {}", ex.getMessage());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
     } catch (Exception ex) {
         logger.error("Internal server error: {}", ex.getMessage());
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
     }
 }

 // Other endpoints...
}
