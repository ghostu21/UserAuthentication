package com.mayank.UserAuthenication.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mayank.UserAuthenication.Dto.AuthResponseTO;
import com.mayank.UserAuthenication.Dto.CreateUserRequestTO;
import com.mayank.UserAuthenication.Dto.GetUserResponseTO;
import com.mayank.UserAuthenication.Dto.LoginRequestTO;
import com.mayank.UserAuthenication.Dto.UpdateUserRequestTO;
import com.mayank.UserAuthenication.Exception.EmailExistsException;
import com.mayank.UserAuthenication.Exception.InvalidExecption;
import com.mayank.UserAuthenication.Exception.MobileExistsException;
import com.mayank.UserAuthenication.Exception.NotFoundExecption;
import com.mayank.UserAuthenication.Exception.UsernameExistsException;
import com.mayank.UserAuthenication.constants.UserRole;
import com.mayank.UserAuthenication.model.Role;
import com.mayank.UserAuthenication.model.User;
import com.mayank.UserAuthenication.repo.UserRepository;
import com.mayank.UserAuthenication.securityfilter.JwtProvider;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder; // Spring Security's PasswordEncoder

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private CustomerUserDetailsServcie customerUserDetailsServcie;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Transactional
	@Override
	public AuthResponseTO registerUser(CreateUserRequestTO userDTO) {
		// Check if a user with the provided username already exists

		if (userRepository.existsByUserName(userDTO.getUserName())) {
			throw new UsernameExistsException("Username already exists");
		}

		// Check if a user with the provided email already exists
		if (userRepository.existsByEmail(userDTO.getEmail())) {
			throw new EmailExistsException("Email already exists");
		}

		// Check if a user with the provided mobile number already exists
		if (userRepository.existsByMobile(userDTO.getMobile())) {

			throw new MobileExistsException("Mobile number already exists");
		}

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		User user = new User();
		user.setUserName(userDTO.getUserName());
		user.setEmail(userDTO.getEmail());
		// Encode password securely
		String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
		user.setPassword(encodedPassword);
		user.setMobile(userDTO.getMobile());

		user.setName(userDTO.getName());
		try {
			user.setDob(formatter.parse(userDTO.getDob()));
		} catch (ParseException e) {
			logger.error(e.getMessage());
			throw new InvalidExecption("Dob is invalid");
		}
		user.setCreatedTime(new Date());
		user.setLastModifiedTime(new Date());

		// Assign default role to user
		Role defaultRole = new Role(UserRole.ROLE_USER.id, UserRole.ROLE_USER.name()); // Assuming you have a method to
																						// find role by name
		user.setRole(defaultRole);

		userRepository.save(user);

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDTO.getEmail(),
				userDTO.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwts = jwtProvider.generateToken(authentication);

//   create authresponse object
		AuthResponseTO authResponse = new AuthResponseTO();
		authResponse.setJwt(jwts);
		authResponse.setRole(defaultRole.getRoleName());

		authResponse.setMessage("Register success");

		return authResponse;

	}

	@Override
	@Transactional
	public AuthResponseTO userSignIn(LoginRequestTO req) {

		String usernanme = req.getEmail();
		String password = req.getPassword();
		Authentication authentication = authenticate(usernanme, password);
		String jwts = jwtProvider.generateToken(authentication);

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

		AuthResponseTO authResponse = new AuthResponseTO();
		authResponse.setJwt(jwts);
		authResponse.setRole(role);

		authResponse.setMessage("Login success");

		return authResponse;

	}

	@Override
	public GetUserResponseTO getUserDetails(String jwt) {

		String email = jwtProvider.getEmailFromJwtToken(jwt);
		User user = this.getUserByEmail(email);

		GetUserResponseTO userResponse = new GetUserResponseTO();

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		userResponse.setEmail(user.getEmail());
		userResponse.setMobile(user.getMobile());
		userResponse.setUserName(user.getUserName());
		userResponse.setName(user.getName());
		userResponse.setDob(formatter.format(user.getDob()));

		return userResponse;

	}

	@Override
	public User getUserByEmail(String email) {
		if (email == null) {
			throw new InvalidExecption("Invalid Email");
		}
		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new NotFoundExecption("User Not Exist");
		}
		return user;

	}

	@Override
	@Transactional
	public void updateUser(String jwt, UpdateUserRequestTO req) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		String email = jwtProvider.getEmailFromJwtToken(jwt);
		User user = this.getUserByEmail(email);

		if (StringUtils.isNotBlank(req.getMobile())) {
			user.setMobile(req.getMobile());

		}

		if (StringUtils.isNotBlank(req.getName())) {
			user.setUserName(req.getName());
		}
		if (StringUtils.isNotBlank(req.getDob())) {
			try {
				user.setDob(formatter.parse(req.getDob()));
			} catch (ParseException e) {
				logger.error(e.getMessage());
				throw new InvalidExecption("Dob is invalid");
			}

		}

		user.setLastModifiedTime(new Date());

		userRepository.save(user);

	}

	private Authentication authenticate(String username, String password) {

		UserDetails userDetails = customerUserDetailsServcie.loadUserByUsername(username);
		if (userDetails == null) {
			throw new BadCredentialsException("Invalid username…");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("invialid password…");
		}

		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

	// Other methods...
}
