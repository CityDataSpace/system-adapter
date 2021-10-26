package de.fraunhofer.dataspaces.iese.systemadapter.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import de.fraunhofer.dataspaces.iese.systemadapter.validation.annotations.Size;

/**
 * This class checks whether a String's length is between specified min and max length.
 * @author ajdari
 */
public class SizeClassValidator implements ConstraintValidator<Size, String> {
	
	private int minSize;
	
	private int maxSize;
	
	/**
	 * @param size
	 * @return 
	 */
	@Override
	public void initialize(Size size) {
		this.minSize = size.min();
		this.maxSize = size.max();
	}
	
	/**
	 * @param  value The value to be validated
	 * @param  context Inherited from ConstraintValidator interface
	 * @return Boolean showing whether validation succeeded
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.length() > this.maxSize || value.length() < this.minSize) {
			return false;
		}
		return true;
	}
}
