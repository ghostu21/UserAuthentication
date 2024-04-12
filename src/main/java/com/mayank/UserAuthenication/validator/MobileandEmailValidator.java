package com.mayank.UserAuthenication.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mayank.UserAuthenication.Exception.InvalidExecption;

public class MobileandEmailValidator {

	private static final String MOBILE_NUMBER_REGEX = "^\\+91\\d{10}$";

	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

	public static void isValidEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			throw new InvalidExecption("Email is not Valid");
		}
	}

	public static void isValidMobileNumber(String mobileNumber) {
		Pattern pattern = Pattern.compile(MOBILE_NUMBER_REGEX);
		Matcher matcher = pattern.matcher(mobileNumber);
		if (!matcher.matches()) {
			throw new InvalidExecption("Mobile is not Valid");
		}
	}

}
