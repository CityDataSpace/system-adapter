package de.fraunhofer.dataspaces.iese.systemadapter.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import de.fraunhofer.dataspaces.iese.systemadapter.validation.annotations.NotBlank;

public class NotBlankClassValidator implements ConstraintValidator<NotBlank, String> {
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.length() == 0) {
			return false;
		}
		return true;
	}

}
