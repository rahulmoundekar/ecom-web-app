package com.app.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.dto.CustomerDto;

public class CustomerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(CustomerDto.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CustomerDto customerDto = (CustomerDto) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNo", "mobile.required");

		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(customerDto.getUsername());

		if (!matcher.matches())
			errors.rejectValue("username", "email.required");

		if (isValidMobileNo(customerDto.getMobileNo()))
			errors.rejectValue("mobileNo", "mobile.valid");

	}

	public static boolean isValidMobileNo(String str) {
		// (0/91): number starts with (0/91)
		// [7-9]: starting of the number may contain a digit between 0 to 9
		// [0-9]: then contains digits 0 to 9
		Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{10}");
		// the matcher() method creates a matcher that will match the given input
		// against this pattern
		Matcher match = ptrn.matcher(str);
		// returns a boolean value
		return (match.find() && match.group().equals(str));
	}

}
