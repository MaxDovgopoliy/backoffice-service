package com.service.backoffice.util;

import static org.mockito.Mockito.when;

import com.service.backoffice.dto.CoordinatesDto;
import com.service.backoffice.dto.DtoForTripService;
import com.service.backoffice.model.Area;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Coordinates;
import com.service.backoffice.model.Country;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.AreaRepo;
import com.service.backoffice.repositories.CityRepo;
import com.service.backoffice.repositories.TariffRepo;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AreaUtilTest {
    @InjectMocks
    AreaUtil areaUtil;
    @Mock
    private static CityRepo cityRepo;
    @Mock
    private static AreaRepo areaRepo;
    @Mock
    private static TariffRepo tariffRepo;

    private final List<Coordinates> listOfCoordinates =
            List.of(new Coordinates(1L, 0, 2),
                    new Coordinates(2L, 0, 0),
                    new Coordinates(3L, 2, 0));
    private final Country country = new Country("Ukraine", "UAH", "KPH");
    private final City city = new City("Lviv", 1.1, listOfCoordinates, country);

    private final Area area = new Area(city, listOfCoordinates);
    private final Tariff tariff = new Tariff(1L,"tariff", "good one", "medium", 100,"UAH");

    @Test
    void findCityByCoordinates() {
        when(cityRepo.findAll()).thenReturn(List.of(city));
        var result = AreaUtil.findCityByCoordinates(0.5, 0.5);

        Assertions.assertEquals(city, result);
    }

    @Test
    void isPossibleToLeaveCarByCoordinates() {
        when(areaRepo.findAll()).thenReturn(List.of(area));
        boolean result =
                AreaUtil.isPossibleToLeaveCarByCoordinates(0.5, 0.5);
        Assertions.assertTrue(result);
    }

    @Test
    void coordinatesInArea() {
        CoordinatesDto coordinatesDto = new CoordinatesDto(0.5, 0.5);
        boolean result = AreaUtil.coordinatesInArea(coordinatesDto, listOfCoordinates);
        Assertions.assertTrue(result);
    }

    @Test
    void informationForTrip() {
        tariff.setCities(Set.of(city));
        when(tariffRepo.findByCarTypeIgnoreCase(tariff.getCarType())).thenReturn(tariff);
        when(cityRepo.findAll()).thenReturn(List.of(city));

        DtoForTripService dtoForTripService =
                AreaUtil.informationForTrip(tariff.getCarType(), 0.5, 0.5);

        DecimalFormat df = new DecimalFormat("#.##");

        Assertions.assertEquals(country.getCurrency(), dtoForTripService.getCurrency());
        Assertions.assertEquals(country.getName(), dtoForTripService.getCountryName());
        Assertions.assertEquals(
                Double.parseDouble(df.format(tariff.getRatePerHour() * city.getCoefficientForTariff())),
                Double.valueOf(dtoForTripService.getRatePerHour()));
        Assertions.assertEquals(country.getUnitOfSpeed(), dtoForTripService.getUnitOfSpeed());
    }
}