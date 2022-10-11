package com.service.backoffice.util.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Roles {
    ADMIN("admin"),
    USER("user");

    private final String role;
}
