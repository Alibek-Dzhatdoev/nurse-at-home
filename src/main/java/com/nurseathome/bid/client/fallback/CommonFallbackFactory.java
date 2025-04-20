package com.nurseathome.bid.client.fallback;

import feign.FeignException;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;

import static org.springframework.http.HttpStatusCode.valueOf;
import static org.springframework.util.StringUtils.hasText;

public interface CommonFallbackFactory {

    default ResponseStatusException onFeignException(Throwable ex) {
        return onFeignException(ex, null);
    }

    default ResponseStatusException onFeignException(Throwable ex, String message) {
        int status = 500;

        if (ex instanceof FeignException exc) {
            try {
                Field field = FeignException.class.getDeclaredField("status");
                field.setAccessible(true);
                status = (int) field.get(exc);
            } catch (NoSuchFieldException | IllegalAccessException ignore) {
            }
        }
        status = status < 100 ? 500 : status;

        return new ResponseStatusException(valueOf(status), hasText(message) ? message : ex.getMessage());
    }
}
