package com.service.backoffice.validation.area;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Country;
import com.service.backoffice.repositories.CountryRepo;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AreaValidatorTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CountryRepo countryRepo;

    private static Validator validator;

    private static Country country = new Country("Ukraine");
    private static City city = new City("Lviv", 500, country);

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidArea() {
        AreaDto areaDto = new AreaDto( new AddressDto("Shevchenka",22), 200, "Ukraine", "Lviv");

        when(countryRepo.findByNameIgnoreCase(areaDto.getCountryName())).thenReturn(country);

        Set<ConstraintViolation<AreaDto>> violations = validator.validate(areaDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidArea() {
        AreaDto areaDto = new AreaDto(null, 0, "Ukraine", "Lviv");

        Set<ConstraintViolation<AreaDto>> violations = validator.validate(areaDto);
        assertFalse(violations.isEmpty());
    }
}