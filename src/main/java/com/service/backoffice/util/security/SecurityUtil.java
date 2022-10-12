package com.service.backoffice.util.security;

import com.service.backoffice.dto.TokenValidationDto;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class SecurityUtil {
    private final WebClient userWebClient;

    public void tokenCheckForRole(String token, Set<Roles> roles) {
        if (token == null) {
            throw new ApiException(Exceptions.NOT_AUTHORIZED);
        }
        token = token.replace("Bearer ", "");
        Set<String> tokenRoles = getRolesFromToken(token);
        if (tokenRoles.stream().noneMatch(roles.toString()::contains)) {
            throw new ApiException(Exceptions.ACCESS_DENIED);
        }
    }

    public Set<String> getRolesFromToken(String token) {
        return userWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/validate-auth-token")
                        .build()
                )
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(TokenValidationDto.class)
                .block().getRoles();

    }
}
