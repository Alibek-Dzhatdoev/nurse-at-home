package com.nurseathome.bid.client;

import com.nurseathome.bid.client.fallback.OauthClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

@FeignClient(name = "notification", path = "/notification", fallbackFactory = OauthClientFallback.class)
public interface NotificationClient {

    @PostMapping("/api/v1/push")
    void sendPushNotification(@RequestHeader(AUTHORIZATION) String authorization);
}