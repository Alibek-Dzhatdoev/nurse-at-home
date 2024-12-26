package com.ali.nurse_at_home.utils;

import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.ali.nurse_at_home.utils.RequestContextUtils.getHeaderValue;
import static com.google.gson.JsonParser.parseString;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Base64.getUrlDecoder;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@Component
public class SecurityContextUtils {

    @NonFinal
    @Value("${application.security.service-client.id}")
    String serviceClientId;

    public String getAccessToken() {
        return getHeaderValue(AUTHORIZATION).split(SPACE)[1];
    }

    public boolean isServiceClient() {
        return getPropertyFromToken("aud")
                .map(aud -> serviceClientId.equals(aud))
                .orElse(false);
    }

    public String getAuthApplicationName() {
        return getPropertyFromToken("appName")
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Нет информации об имени сервиса"));
    }

    public String getCurrentRole(){
        return getPropertyFromToken("role")
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Нет информации о роли пользователя"));
    }
    private static Optional<String> getPropertyFromToken(String property) {
        try {
            String token = getHeaderValue(AUTHORIZATION);
            String payload = new String(getUrlDecoder().decode(token.split("\\.")[1]), UTF_8);
            return ofNullable(parseString(payload).getAsJsonObject().get(property).getAsString());
        } catch (Exception e) {
            log.error("Не удалось достать из JWT токена поле {}. Ошибка: {}", property, e.getMessage(), e);
            return empty();
        }
    }
}
