package com.service.backoffice.validation;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = OrderValidator.class)
@Target({ METHOD, CONSTRUCTOR })
@Retention(RUNTIME)
@Documented
public @interface ValidOrder {
}
