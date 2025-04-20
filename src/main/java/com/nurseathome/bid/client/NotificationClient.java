package com.nurseathome.bid.client;

import com.nurseathome.bid.aspect.Authorized;
import com.nurseathome.bid.client.fallback.NotificationClientFallback;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

@FeignClient(name = "notification",
             path = "/notification/api/v1",
             fallbackFactory = NotificationClientFallback.class)
public interface NotificationClient {

    @Authorized
    @PostMapping("/push")
    @Retry(name = "retryNotification")
    @CircuitBreaker(name = "circuitBreakerConnect")
    void sendPushNotification(@RequestHeader(AUTHORIZATION) String authorization);
}