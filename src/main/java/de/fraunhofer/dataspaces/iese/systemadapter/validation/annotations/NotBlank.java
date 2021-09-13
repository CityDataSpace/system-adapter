package de.fraunhofer.dataspaces.iese.systemadapter.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import de.fraunhofer.dataspaces.iese.systemadapter.validation.validator.NotBlankClassValidator;

@Constraint(validatedBy=NotBlankClassValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlank {
	
	public String message() default "This field must not be empty";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};

}
