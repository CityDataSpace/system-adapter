package de.fraunhofer.dataspaces.iese.systemadapter.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import de.fraunhofer.dataspaces.iese.systemadapter.validation.validator.CapitalLetterClassValidator;


@Constraint(validatedBy=CapitalLetterClassValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CapitalLetter {
	
	public String message() default "The name should start with capital letter.";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};

}
