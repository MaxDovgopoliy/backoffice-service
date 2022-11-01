package com.service.backoffice.util.security;

import com.service.backoffice.dto.TokenValidationDto;
import com.service.backoffice.exception.ApiException;
import com.service.backoffice.exception.Exceptions;
import com.service.backoffice.services.DiscoveryUrlService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class SecurityUtil {
    private final DiscoveryUrlService urlService;
    private final WebClient webClient;

    public void tokenCheckForRole(String token, Set<Roles> roles) {
        if (token == null) {
            throw new ApiException(Exceptions.NOT_AUTHORIZED);
        }
        Set<String> tokenRoles = getInfoFromToken(token).getRoles();
        if (tokenRoles.stream().noneMatch(roles.toString()::contains)) {
            throw new ApiException(Exceptions.ACCESS_DENIED);
        }
    }

    public TokenValidationDto getInfoFromToken(String token) {
        return webClient
                .get()
                .uri(urlService.getUserServiceUrl() + "validate-auth-token")
                .header(HttpHeaders.AUTHORIZATION, token)
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
