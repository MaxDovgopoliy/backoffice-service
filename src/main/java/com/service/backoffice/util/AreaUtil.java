package com.service.backoffice.util;

import com.service.backoffice.dto.CoordinatesDto;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import com.service.backoffice.model.Area;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Coordinates;
import com.service.backoffice.repositories.AreaRepo;
import com.service.backoffice.repositories.CityRepo;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class AreaUtil {
    private static CityRepo cityRepo;
    private static AreaRepo areaRepo;

    public AreaUtil(CityRepo cityRepo, AreaRepo areaRepo) {
        AreaUtil.cityRepo = cityRepo;
        AreaUtil.areaRepo = areaRepo;

    }

    public static City findCityByCoordinates(double latitude, double longitude) {
        CoordinatesDto coordinatesDto = new CoordinatesDto(latitude, longitude);
        Optional<City> city = cityRepo.findAll()
                .stream()
                .filter(c -> coordinatesInArea(coordinatesDto, c.getCoordinates()))
                .findAny();
        if (city.isEmpty()) {
            throw new ApiException(Exceptions.CITY_NOT_FOUND);
        } else {
            return city.get();
        }
    }

    public static boolean isPossibleToLeaveCarByCoordinates(double latitude, double longitude) {
        List<Area> areas = areaRepo.findAll();
        CoordinatesDto coordinatesDto = new CoordinatesDto(latitude, longitude);
        for (Area area : areas) {
            if (coordinatesInArea(coordinatesDto, area.getCoordinates())) {
                return true;
            }
        }
        throw new ApiException(Exceptions.BAD_AREA);
    }

    public static boolean coordinatesInArea(CoordinatesDto coordinates,
                                            List<Coordinates> areaCoordinates) {
        GeometryFactory gf = new GeometryFactory();
        Geometry cityBounds = LocationAdaptor.poligonOfCoordinates(areaCoordinates);
        Geometry point = gf.createPoint(new Coordinate(coordinates.getLatitude(),
                coordinates.getLongitude()));

        return cityBounds.covers(point);
    }
}
