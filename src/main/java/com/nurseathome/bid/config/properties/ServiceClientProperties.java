package com.nurseathome.bid.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.security.service-client")
public record ServiceClientProperties(
    String id,
    String secret
) {}
