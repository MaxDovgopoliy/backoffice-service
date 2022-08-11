package com.service.backoffice.validation;

import com.service.backoffice.dto.OrderDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class OrderValidator implements ConstraintValidator<ValidOrder, OrderDTO> {
    @Override
    public boolean isValid(OrderDTO orderDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (orderDTO == null) {
            return true;
        }
        if (!(orderDTO instanceof OrderDTO)) {
            throw new IllegalArgumentException("Illegal method signature, "
                    + "expected parameter of type Reservation.");
        }
        if (orderDTO.getStartDate() == null
                || orderDTO.getStartDate().isAfter(LocalDateTime.now())
                || orderDTO.getEndDate() == null
                || orderDTO.getEndDate().isAfter(LocalDateTime.now())
                || orderDTO.getPrise() < 0
                || orderDTO.getUserId() < 0
                || orderDTO.getCarId() < 0
                || orderDTO.getCarType() == null) {
            return false;
        }
        return (orderDTO.getStartDate().isBefore(orderDTO.getEndDate()))
                && orderDTO.getCarType().length()>0;
    }
}
