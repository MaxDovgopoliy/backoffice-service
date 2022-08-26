package com.service.backoffice.validation.area;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Payload;

//@Constraint(validatedBy = OrderValidator.class)
@Target({ TYPE})
@Retention(RUNTIME)
@Documented
public @interface ValidArea {
    String message() default "Bad order parameters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
