package com.service.backoffice.validation;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;

@Constraint(validatedBy = OrderValidator.class)
@Target({METHOD, CONSTRUCTOR})
@Retention(RUNTIME)
@Documented
public @interface ValidOrder {
}
