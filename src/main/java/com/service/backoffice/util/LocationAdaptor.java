package com.service.backoffice.util;

import com.service.backoffice.dto.AreaDto;
import com.service.backoffice.dto.CityDto;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import com.service.backoffice.mapper.CityMapper;
import com.service.backoffice.mapper.CoordinatesMapper;
import com.service.backoffice.model.Area;
import com.service.backoffice.model.City;
import com.service.backoffice.model.Coordinates;
import com.service.backoffice.model.Country;
import com.service.backoffice.repositories.CountryRepo;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationAdaptor {
    @Autowired
    private CountryRepo countryRepo;

    public City makeCityFromDto(CityDto cityDto) {
        Country country = countryRepo.findByNameIgnoreCase(cityDto.getCountryName());
        if (country != null) {
            City cityFromDb = country.getCities()
                    .stream()
                    .filter(i -> i.getName().equalsIgnoreCase(cityDto.getName()))
                    .findFirst()
                    .orElse(null);
            if (cityFromDb == null) {
                City city = CityMapper.MAPPER.toCity(cityDto);
                city.setCountry(country);
                List<Coordinates> coordinates = CoordinatesMapper.MAPPER
                        .toListOfCoordinates(cityDto.getCoordinatesDtoList());
                city.setCoordinates(coordinates);
                coordinates.forEach(coordinate -> coordinate.setCity(city));
                return city;
            } else {
                throw new ApiException(Exceptions.CITY_ALREADY_EXIST);
            }
        } else {
            throw new ApiException(Exceptions.COUNTRY_NOT_FOUND);
        }
    }

    public Area makeAreaFromDto(Area area, AreaDto areaDto) {
        Country country = countryRepo.findByNameIgnoreCase(areaDto.getCountryName());
        if (country != null) {
            City city = country
                    .getCities()
                    .stream()
                    .filter(i -> i.getName().equalsIgnoreCase(areaDto.getCityName()))
                    .findFirst()
                    .orElseThrow(() -> new ApiException(Exceptions.CITY_NOT_FOUND));

            area.setCity(city);
            List<Coordinates> coordinates = CoordinatesMapper.MAPPER
                    .toListOfCoordinates(areaDto.getCoordinatesDtoList());
            if (checkIfAreaIsInsideAnother(city.getCoordinates(), coordinates)) {
                coordinates.forEach(coordinate -> coordinate.setArea(area));
                area.setCoordinates(coordinates);
                return area;
            } else {
                throw new ApiException(Exceptions.AREA_OUT_OF_CITY);
            }

        } else {
            throw new ApiException(Exceptions.COUNTRY_NOT_FOUND);
        }
    }

    public boolean checkIfAreaIsInsideAnother(List<Coordinates> listOfOutsideCoordinates,
                                              List<Coordinates> listOfInsideCoordinates) {
        Geometry outsideArea = polygonOfCoordinates(listOfOutsideCoordinates);
        Geometry insideArea = polygonOfCoordinates(listOfInsideCoordinates);

        return insideArea.coveredBy(outsideArea);
    }

    public static Geometry polygonOfCoordinates(List<Coordinates> listOfCoordinates) {

        GeometryFactory gf = new GeometryFactory();

        Coordinate[] outsideCoordinatesArr = new Coordinate[listOfCoordinates.size() + 1];

        for (int i = 0; i < listOfCoordinates.size(); i++) {
            outsideCoordinatesArr[i] = new Coordinate(listOfCoordinates.get(i).getLatitude(),
                    listOfCoordinates.get(i).getLongitude());
        }

        outsideCoordinatesArr[outsideCoordinatesArr.length - 1] = new Coordinate(
                listOfCoordinates.get(0).getLatitude(),
                listOfCoordinates.get(0).getLongitude());

        return gf.createPolygon(outsideCoordinatesArr);
    }
}
