package com.service.backoffice.exeption;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true, builderMethodName = "of")
public class ApiResponse {

    public static final String RESPONSE_JSON_TEMPLATE = "{ "
            + "\"errorId\": %d,"
            + "\"errorName\": \"%s\","
            + "\"message\": \"%s\","
            + "\"path\": \"%s\" "
            + "}";

    private Integer errorId;
    private String errorName;
    private String message;
    private String path;
}
