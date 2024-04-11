package com.mayank.UserAuthenication.service;



import com.mayank.UserAuthenication.Dto.AuthResponseTO;
import com.mayank.UserAuthenication.Dto.CreateUserRequestTO;
import com.mayank.UserAuthenication.Dto.GetUserResponseTO;
import com.mayank.UserAuthenication.Dto.LoginRequestTO;
import com.mayank.UserAuthenication.Dto.UpdateUserRequestTO;
import com.mayank.UserAuthenication.model.User;


public interface UserService {
	
	
	AuthResponseTO registerUser(CreateUserRequestTO userDTO);
	AuthResponseTO userSignIn(LoginRequestTO req);
	GetUserResponseTO getUserDetails(String jwt);
	User getUserByEmail(String email);
	void updateUser(String jwt, UpdateUserRequestTO req);

}
