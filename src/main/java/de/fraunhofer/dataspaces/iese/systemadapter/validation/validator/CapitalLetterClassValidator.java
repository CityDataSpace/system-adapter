package de.fraunhofer.dataspaces.iese.systemadapter.validation.validator;

import java.lang.Character;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import de.fraunhofer.dataspaces.iese.systemadapter.validation.annotations.CapitalLetter;

public class CapitalLetterClassValidator implements ConstraintValidator<CapitalLetter, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(Character.isUpperCase(value.charAt(0))) {
			return true;
		}
		return false;
	}

}
