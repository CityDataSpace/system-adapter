package de.fraunhofer.dataspaces.iese.systemadapter.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import de.fraunhofer.dataspaces.iese.systemadapter.validation.annotations.NotBlank;

/**
 * This class checks whether a String is blank or not.
 */
public class NotBlankClassValidator implements ConstraintValidator<NotBlank, String> {
	
	/**
	 * @param  value The value to be validated
	 * @param  context Inherited from ConstraintValidator interface
	 * @return Boolean showing whether validation succeeded
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.length() == 0) {
			return false;
		}
		return true;
	}

}
