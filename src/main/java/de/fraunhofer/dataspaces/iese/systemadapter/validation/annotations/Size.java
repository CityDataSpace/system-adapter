package de.fraunhofer.dataspaces.iese.systemadapter.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import de.fraunhofer.dataspaces.iese.systemadapter.validation.validator.SizeClassValidator;

@Constraint(validatedBy=SizeClassValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Size {
	
	public int min() default 5;
	
	public int max() default 25;
	
	public String message() default "The length should be between specified boundaries";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};

}
