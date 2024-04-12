package com.mayank.UserAuthenication.validator;

import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mayank.UserAuthenication.Exception.InvalidExecption;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class GenericValidator {

	private static final Logger logger = LoggerFactory.getLogger(GenericValidator.class);

	private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();
	private static final Validator VALIDATOR = FACTORY.getValidator();

	public static Validator getInstance() {
		return VALIDATOR;
	}

	public static final <T> void validate(final T object) throws InvalidExecption {
		final Set<ConstraintViolation<T>> violations = VALIDATOR.validate(object);
		final Iterator<ConstraintViolation<T>> iterator = violations.iterator();
		ConstraintViolation<T> violation;
		if (iterator.hasNext()) {
			violation = iterator.next();
			logger.debug("ConstraintViolation: " + violation.getPropertyPath().toString());
			throw new InvalidExecption("global.error.invalid.request");
		}
	}

}
