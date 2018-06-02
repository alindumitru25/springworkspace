package com.luv2code.spring.demo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator
	implements ConstraintValidator<CourseCode, String> {
	
	private String coursePrefix;

	@Override
	public void initialize(CourseCode theCourseCode) {
		coursePrefix = theCourseCode.value();
	}

	@Override
	public boolean isValid(String theCode,
			ConstraintValidatorContext constraintVlidatorContext) {
		if (theCode == null) {
			return true;
		}

		boolean result = theCode.startsWith(this.coursePrefix);
		
		return result;
	}
	
}
