package de.fraunhofer.dataspaces.iese.systemadapter.validation.validator;

import java.lang.Character;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import de.fraunhofer.dataspaces.iese.systemadapter.validation.annotations.CapitalLetter;

/**
 * This class checks whether a String starts with a capital letter
 */
public class CapitalLetterClassValidator implements ConstraintValidator<CapitalLetter, String> {

	/**
	 * @param  value The value to be validated
	 * @param  context Inherited from ConstraintValidator interface
	 * @return Boolean showing whether validation succeeded
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(Character.isUpperCase(value.charAt(0))) {
			return true;
		}
		return false;
	}

}
