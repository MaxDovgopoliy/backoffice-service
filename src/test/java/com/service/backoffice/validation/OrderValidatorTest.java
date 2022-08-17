package com.service.backoffice.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.service.backoffice.dto.OrderDto;
import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class OrderValidatorTest {

    private static Validator validator;
    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void testInvalidOrder() {

        OrderDto invalidOrder = new OrderDto(LocalDateTime.now(),
                LocalDateTime.of(2020, 1, 1, 0, 0, 0), 250, 1, "", 1);

        Set<ConstraintViolation<OrderDto>> violations = validator.validate(invalidOrder);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testValidOrder() {

        OrderDto validOrder = new OrderDto(LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                LocalDateTime.now() , 250, 1, "moto", 1);

        Set<ConstraintViolation<OrderDto>> violations = validator.validate(validOrder);
        assertTrue(violations.isEmpty());
    }
}