package com.service.backoffice.validation;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = OrderValidator.class)
@Target({METHOD, CONSTRUCTOR, TYPE})
@Retention(RUNTIME)
@Documented
public @interface ValidOrder {
    String message() default "Bad order parameters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
