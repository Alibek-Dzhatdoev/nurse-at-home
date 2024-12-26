package com.ali.nurse_at_home.client;

import com.ali.nurse_at_home.client.fallback.OauthClientFallback;
import com.ali.nurse_at_home.model.request.TokenIntrospectRequest;
import com.ali.nurse_at_home.model.response.TokenIntrospectResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@FeignClient(name = "oauth", path = "/oauth-login", fallbackFactory = OauthClientFallback.class)
public interface OauthClient {

    @PostMapping(value = "/oauth2/introspect", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    TokenIntrospectResponse introspect(
            @RequestBody TokenIntrospectRequest request,
            @RequestHeader(value = AUTHORIZATION) String authorizationHeader
    );
}