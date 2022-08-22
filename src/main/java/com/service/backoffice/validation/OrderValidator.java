package com.service.backoffice.validation;

import com.service.backoffice.dto.OrderDto;
import java.time.LocalDateTime;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OrderValidator implements ConstraintValidator<ValidOrder, OrderDto> {

    @Override
    public boolean isValid(OrderDto orderDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        if (orderDto == null) {
            return true;
        }
        if (!(orderDto instanceof OrderDto)) {
            throw new IllegalArgumentException("Illegal method signature, "
                    + "expected parameter of type Reservation.");
        }
        if (orderDto.getStartDate() == null
                || orderDto.getStartDate().isAfter(LocalDateTime.now())
                || orderDto.getEndDate() == null
                || orderDto.getEndDate().isAfter(LocalDateTime.now())
                || orderDto.getPrise() < 0
                || orderDto.getUserId() <= 0
                || orderDto.getCarId() <= 0
                || orderDto.getCarType() == null) {
            return false;
        }
        return (orderDto.getStartDate().isBefore(orderDto.getEndDate()))
                && orderDto.getCarType().length() > 0;
    }
}
