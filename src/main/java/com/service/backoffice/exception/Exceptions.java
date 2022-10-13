package com.service.backoffice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum Exceptions {
    TARIFF_NOT_FOUND(1, "There is no tariff with this data", HttpStatus.NOT_FOUND),
    AREA_NOT_FOUND(2, "There is no area with this data", HttpStatus.NOT_FOUND),
    BAD_TARIFF_CREDENTIALS(3, "Invalid name, car type or rate per hour", HttpStatus.BAD_REQUEST),
    CITY_NOT_FOUND(4, "No such city in country", HttpStatus.BAD_REQUEST),
    COUNTRY_NOT_FOUND(5, "No such country", HttpStatus.BAD_REQUEST),
    AREA_OUT_OF_CITY(6, "Area is not inside city bounds", HttpStatus.BAD_REQUEST),
    CITY_ALREADY_EXIST(7, "City with such name already exist", HttpStatus.BAD_REQUEST),
    COUNTRY_ALREADY_EXIST(7, "Country with such name already exist", HttpStatus.BAD_REQUEST),
    ACCESS_DENIED(8,"You dont have permissions for this action", HttpStatus.FORBIDDEN),
    NOT_AUTHORIZED(9,"You are not logged", HttpStatus.FORBIDDEN),
    TARIFF_NOT_FOUND_FOR_CITY(10, "There is no tariff for this city", HttpStatus.NOT_FOUND)
    ;

    private final Integer id;
    private final String message;
    private final HttpStatus status;
}
