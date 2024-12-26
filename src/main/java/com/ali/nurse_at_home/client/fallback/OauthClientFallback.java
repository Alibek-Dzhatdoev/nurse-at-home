package com.ali.nurse_at_home.client.fallback;

import com.ali.nurse_at_home.client.OauthClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Slf4j
@Component
public class OauthClientFallback implements FallbackFactory<OauthClient> {

    @Override
    public OauthClient create(Throwable cause) {
        return (request, authorizationHeader) -> {
            log.error("FeignException on OauthClient.introspect(TokenIntrospectRequest request, String authorizationHeader)", cause);
            throw new ResponseStatusException(FORBIDDEN, "Не удалось проверить токен в SSO");
        };
    }
}