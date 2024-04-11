package com.mayank.UserAuthenication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//GlobalExceptionHandler.java
@ControllerAdvice
public class GlobalExceptionHandler {

 @ExceptionHandler({UsernameExistsException.class, EmailExistsException.class, MobileExistsException.class})
 public ResponseEntity<String> handleDuplicateEntry(RuntimeException ex) {
     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
 }

 @ExceptionHandler(Exception.class)
 public ResponseEntity<String> handleInternalError(Exception ex) {
     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
 }
}
