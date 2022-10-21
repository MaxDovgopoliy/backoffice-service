package com.service.backoffice.util;

import com.service.backoffice.dto.CoordinatesDto;
import com.service.backoffice.dto.DtoForTripService;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import com.service.backoffice.model.Area;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Coordinates;
import com.service.backoffice.model.Country;
import com.service.backoffice.model.Tariff;
import com.service.backoffice.repositories.AreaRepo;
import com.service.backoffice.repositories.CityRepo;
import com.service.backoffice.repositories.TariffRepo;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class AreaUtil {
    private static CityRepo cityRepo;
    private static AreaRepo areaRepo;
    private static TariffRepo tariffRepo;

    public AreaUtil(CityRepo cityRepo, AreaRepo areaRepo, TariffRepo tariffRepo) {
        AreaUtil.cityRepo = cityRepo;
        AreaUtil.areaRepo = areaRepo;
        AreaUtil.tariffRepo = tariffRepo;
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
        Geometry cityBounds = LocationAdaptor.polygonOfCoordinates(areaCoordinates);
        Geometry point = gf.createPoint(new Coordinate(coordinates.getLatitude(),
                coordinates.getLongitude()));

        return cityBounds.covers(point);
    }

    public static DtoForTripService informationForTrip(String carType, double latitude,
                                                       double longitude) {
        DtoForTripService dtoForTripService = new DtoForTripService();

        Tariff tariff = tariffRepo.findByCarTypeIgnoreCase(carType);
        if (tariff == null) {
            throw new ApiException(Exceptions.TARIFF_NOT_FOUND);
        }

        City cityByCoordinates = AreaUtil.findCityByCoordinates(latitude, longitude);
        if (tariff.getCities().contains(cityByCoordinates)) {
            Country country = cityByCoordinates.getCountry();

            double ratePerHour = CurrencyUtil.convertCurrencyRate(tariff.getCurrency(),
                    country.getCurrency(), tariff.getRatePerHour())
                    * cityByCoordinates.getCoefficientForTariff();

            DecimalFormat df = new DecimalFormat("#.##");
            ratePerHour = Double.parseDouble(df.format(ratePerHour));

            dtoForTripService.setTariffId(tariff.getId());
            dtoForTripService.setCityName(cityByCoordinates.getName());
            dtoForTripService.setCountryName(country.getName());
            dtoForTripService.setRatePerHour(ratePerHour);
            dtoForTripService.setCurrency(country.getCurrency());
            dtoForTripService.setUnitOfSpeed(country.getUnitOfSpeed());

            return dtoForTripService;
        } else {
            throw new ApiException(Exceptions.TARIFF_NOT_FOUND_FOR_CITY);
        }
    }
}
