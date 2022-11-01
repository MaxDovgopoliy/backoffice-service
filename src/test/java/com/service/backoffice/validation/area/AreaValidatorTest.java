package com.service.backoffice.validation.area;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.dto.CoordinatesDto;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Coordinates;
import com.service.backoffice.model.Country;
import com.service.backoffice.repositories.CountryRepo;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AreaValidatorTest {

    @Mock
    CountryRepo countryRepo;

    private static Validator validator;

    private final List<Coordinates> listOfCoordinates =
            List.of(new Coordinates(1L, -1, -1),
                    new Coordinates(2L, 8, 0),
                    new Coordinates(3L, 0, 8));
    private  Country country = new Country("Ukraine","USD","MPH");
    private  City city = new City("Lviv",1.1, listOfCoordinates, country);
    AreaDto validAreaDto = new AreaDto("Ukraine", "Lviv", List.of(new CoordinatesDto( 0, 2),
            new CoordinatesDto( 0, 0),
            new CoordinatesDto( 2, 0)));
    AreaDto invalidAreaDto = new AreaDto("", "Lviv", List.of(new CoordinatesDto( 0, 2),
            new CoordinatesDto( 0, 0)));

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidArea() {


        Set<ConstraintViolation<AreaDto>> violations = validator.validate(validAreaDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidArea() {

        Set<ConstraintViolation<AreaDto>> violations = validator.validate(invalidAreaDto);
        assertFalse(violations.isEmpty());
    }
}