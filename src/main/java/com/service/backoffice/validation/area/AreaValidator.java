package com.service.backoffice.validation.area;

import com.service.backoffice.dto.AreaDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AreaValidator implements ConstraintValidator<ValidArea, AreaDto> {

    @Override
    public boolean isValid(AreaDto areaDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        if (areaDto == null) {
            return true;
        }
        if (!(areaDto instanceof AreaDto)) {
            throw new IllegalArgumentException("Illegal method signature, "
                    + "expected parameter of type Reservation.");
        }
        if (areaDto.getSquare() <= 0
                || areaDto.getAddress() == null
                || areaDto.getAddress().length() == 0) {
            return false;
        }
        return true;
    }
}
