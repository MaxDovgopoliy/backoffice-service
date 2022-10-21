package com.service.backoffice.util.security;

import com.service.backoffice.dto.TokenValidationDto;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import com.service.backoffice.services.DiscoveryUrlService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class SecurityUtil {
    private final DiscoveryUrlService urlService;
    private WebClient webClient = WebClient.create();

    public void tokenCheckForRole(String token, Set<Roles> roles) {
        if (token == null) {
            throw new ApiException(Exceptions.NOT_AUTHORIZED);
        }
        token = token.replace("Bearer ", "");
        Set<String> tokenRoles = getInfoFromToken(token).getRoles();
        if (tokenRoles.stream().noneMatch(roles.toString()::contains)) {
            throw new ApiException(Exceptions.ACCESS_DENIED);
        }
    }

    public TokenValidationDto getInfoFromToken(String token) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(urlService.getUserServiceUrl() + "/validate-auth-token")
                        .build()
                )
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(TokenValidationDto.class)
                .block();

    }

    public void tokenCheckForRoleAndUserId(String token, Set<Roles> roles, long userId) {
        tokenCheckForRole(token, roles);
        if (userId != getInfoFromToken(token).getUserId()) {
            throw new ApiException(Exceptions.ACCESS_DENIED);
        }

    }
}
