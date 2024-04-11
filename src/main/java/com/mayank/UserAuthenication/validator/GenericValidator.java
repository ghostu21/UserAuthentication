package com.mayank.UserAuthenication.validator;
//
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//import com.mayank.UserAuthenication.Exception.ValidationException;
//
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Set;

import com.mayank.UserAuthenication.Exception.InvalidExecption;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

//@Component
public class GenericValidator{
	
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
//            LOG.debug("ConstraintViolation: " + violation.getPropertyPath().toString());
            throw new InvalidExecption("global.error.invalid.request");
        }
    }
	
}
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return true; // Support all classes
//    }
//
//    @SuppressWarnings("unchecked")
//	@Override
//    public void validate(Object target, Errors errors) {
//        Class<?> clazz = target.getClass();
//        Field[] fields = clazz.getDeclaredFields();
//
//        for (Field field : fields) {
//            if (field.isAnnotationPresent((Class<? extends Annotation>) NotNull.class)) {
//                field.setAccessible(true);
//                try {
//                    Object value = field.get(target);
//                    if (value == null) {
//                        throw new ValidationException(field.getName() + " cannot be null");
//                    }
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace(); // Handle error
//                }
//            }
//
//            if (field.isAnnotationPresent((Class<? extends Annotation>) NotBlank.class)) {
//                field.setAccessible(true);
//                try {
//                    Object value = field.get(target);
//                    if (value == null || (value instanceof String && ((String) value).trim().isEmpty())) {
//                        throw new ValidationException(field.getName() + " cannot be blank");
//                    }
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace(); // Handle error
//                }
//            }
//        }
//    }
//}
//
//
