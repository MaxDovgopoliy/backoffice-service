package com.service.backoffice.services.implementation;

import com.service.backoffice.model.Area;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Coordinates;
import com.service.backoffice.model.Country;
import com.service.backoffice.repositories.AreaRepo;
import com.service.backoffice.services.AreaService;
import com.service.backoffice.util.LocationAdaptor;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AreaServiceImplTest {
    @InjectMocks
    AreaService areaService;
    @Mock
    AreaRepo areaRepo;
    @Mock
    LocationAdaptor locationAdaptor;

    private static final List<Coordinates> areaCoordinates =
            List.of(new Coordinates(1L, 0, 2),
                    new Coordinates(2L, 0, 0),
                    new Coordinates(3L, 2, 0));

    private static final List<Coordinates> cityCoordinates =
            List.of(new Coordinates(1L, 10, 10),
                    new Coordinates(2L, 0, 0),
                    new Coordinates(3L, 10, 0),
                    new Coordinates(4L, 2, 10));
        private static Country country = new Country("Ukraine","UAH","KPH");
    private static City city =new City("Lviv",1.1,cityCoordinates,country);
    private static List<Area> areas =
            List.of(new Area( city,areaCoordinates));
    {
        city.setAreas(List.of(areas.get(0)));
        country.setCities(List.of(city));
    }

    @Test
    void getAllAreas() {
    }

    @Test
    void deleteArea() {
    }

    @Test
    void saveArea() {
    }

    @Test
    void updateArea() {
    }

    @Test
    void getAreaById() {
    }
}