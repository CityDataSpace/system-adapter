package de.fraunhofer.dataspaces.iese.systemadapter.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import de.fraunhofer.dataspaces.iese.systemadapter.validation.annotations.Size;

public class SizeClassValidator implements ConstraintValidator<Size, String> {
	
	private int minSize;
	
	private int maxSize;
	
	@Override
	public void initialize(Size size) {
		this.minSize = size.min();
		this.maxSize = size.max();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.length() > this.maxSize || value.length() < this.minSize) {
			return false;
		}
		return true;
	}

}
