package com.service.backoffice.services;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscoveryUrlService {

    private final EurekaClient discoveryClient;

    @Value("${user.service.app.name}")
    private String userServiceAppName;

    public String getUserServiceUrl() {
        InstanceInfo instance = discoveryClient
                .getNextServerFromEureka(userServiceAppName, false);
        return instance.getHomePageUrl();
    }

}
