package com.service.backoffice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum Exceptions {
    TARIFF_NOT_FOUND(1, "There is no tariff with this data", HttpStatus.NOT_FOUND),
    AREA_NOT_FOUND(2, "There is no area with this data", HttpStatus.NOT_FOUND),
    BAD_TARIFF_CREDENTIALS(3, "Invalid name, car type or rate per hour", HttpStatus.BAD_REQUEST);

    private final Integer id;
    private final String message;
    private final HttpStatus status;
}
