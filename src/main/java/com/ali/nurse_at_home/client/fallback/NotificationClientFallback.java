package com.ali.nurse_at_home.client.fallback;

import com.ali.nurse_at_home.client.NotificationClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Slf4j
@Component
public class NotificationClientFallback implements FallbackFactory<NotificationClient> {

    @Override
    public NotificationClient create(Throwable cause) {
        return (authorizationHeader) -> {
            log.error("FeignException on NotificationClient.sendPushNotification(String authorizationHeader)", cause);
            throw new ResponseStatusException(FORBIDDEN, "Не удалось отправить Push");
        };
    }
}