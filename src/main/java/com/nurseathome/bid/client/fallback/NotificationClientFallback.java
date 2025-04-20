package com.nurseathome.bid.client.fallback;

import com.nurseathome.bid.client.NotificationClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationClientFallback implements FallbackFactory<NotificationClient>, CommonFallbackFactory {

    //TODO надо бы реализовать отправку уведомлений
    @Override
    public NotificationClient create(Throwable cause) {
        return (authorizationHeader) -> {
            log.error("FeignException on NotificationClient.sendPushNotification(String authorizationHeader)",
                    cause);
            throw onFeignException(cause, "Не удалось отправить Push");
        };
    }
}