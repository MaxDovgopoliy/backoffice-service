package com.service.backoffice.validation.area;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.repositories.CountryRepo;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class AreaValidator implements ConstraintValidator<ValidArea, AreaDto> {

    @Autowired
    private CountryRepo countryRepo;

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
        if (countryRepo.findByNameIgnoreCase(areaDto.getCountryName()) == null
                || areaDto.getSquare() <= 0
                || areaDto.getAddress() == null
                || areaDto.getAddress().length() == 0) {
            return false;
        }
        return (countryRepo
                .findByNameIgnoreCase(areaDto.getCountryName())
                .getCities()
                .stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(areaDto.getCityName())));
    }
}
