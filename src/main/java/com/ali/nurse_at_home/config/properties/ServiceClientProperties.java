package com.ali.nurse_at_home.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.security.service-client")
public record ServiceClientProperties(
    String id,
    String secret
) {}
