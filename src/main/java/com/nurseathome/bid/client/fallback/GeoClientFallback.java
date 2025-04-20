package com.nurseathome.bid.client.fallback;

import com.nurseathome.bid.client.GeoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GeoClientFallback implements FallbackFactory<GeoClient>, CommonFallbackFactory {

    @Override
    public GeoClient create(Throwable cause) {
        return (geoUrl) -> {
            log.error("FeignException on GeoClient.getLocation(String geoUrl)", cause);
            throw onFeignException(cause, "Не удалось отправить Push");
        };
    }
}