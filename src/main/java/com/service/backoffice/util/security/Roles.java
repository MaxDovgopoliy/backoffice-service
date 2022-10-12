package com.service.backoffice.util.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Roles {
    ADMIN("admin"),
    CAR_OWNER("car_owner"),
    USER("user");

    private final String role;
}
