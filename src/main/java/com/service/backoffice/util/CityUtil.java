package com.service.backoffice.util;

import com.service.backoffice.dto.CoordinatesDto;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import com.service.backoffice.model.City;
import com.service.backoffice.repositories.CityRepo;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class CityUtil {
    private static CityRepo cityRepo;

    public CityUtil(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    public static City findCityByCoordinates(double latitude, double longitude) {
        CoordinatesDto coordinatesDto = new CoordinatesDto(latitude, longitude);
        Optional<City> city = cityRepo.findAll()
                .stream()
                .filter(c -> coordinatesInCity(coordinatesDto, c))
                .findAny();
        if (city.isEmpty()) {
            throw new ApiException(Exceptions.CITY_NOT_FOUND);
        } else {
            return city.get();
        }
    }

    public static boolean coordinatesInCity(CoordinatesDto coordinates, City city) {
        GeometryFactory gf = new GeometryFactory();
        Geometry cityBounds = LocationAdaptor.poligonOfCoordinates(city.getCoordinates());
        Geometry point = gf.createPoint(new Coordinate(coordinates.getLatitude(),
                coordinates.getLongitude()));

        return cityBounds.covers(point);
    }
}
