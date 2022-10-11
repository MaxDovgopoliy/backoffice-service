package com.service.backoffice.util.security;

import com.service.backoffice.dto.TokenDto;
import com.service.backoffice.dto.TokenValidationDto;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import java.util.Set;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

public class SecurityUtil {

    private static final WebClient userServiceClient = WebClient.create("http://localhost:8084");

    public static void tokenCheckForRole(String token, Set<Roles> roles) {
        if (token == null) {
            throw new ApiException(Exceptions.NOT_AUTHORIZED);
        }
        token = token.replace("Bearer ", "");
        Set<String> tokenRoles = getRolesFromToken(token);
        if (tokenRoles.stream().noneMatch(roles.toString()::contains)) {
            throw new ApiException(Exceptions.ACCESS_DENIED);
        }
    }

    public static Set<String> getRolesFromToken(String token) {
        return userServiceClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/validate-token")
                        .build()
                )
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new TokenDto("Bearer " + token)))
                .retrieve()
                .bodyToMono(TokenValidationDto.class)
                .block().getRoles();

    }
}
