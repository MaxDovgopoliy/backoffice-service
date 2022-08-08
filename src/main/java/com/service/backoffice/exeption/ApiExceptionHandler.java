package com.service.backoffice.exeption;

import com.service.backoffice.model.Area;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> catchApiException(ApiException exception, WebRequest request) {
        return ResponseEntity
                .status(exception.getException().getStatus())
                .body(ApiResponse.of()
                        .errorId(exception.getException().getId())
                        .errorName(exception.getException().name())
                        .message(exception.getException().getMessage())
                        .path(request.getDescription(false).substring(4))
                        .build());
    }
}
