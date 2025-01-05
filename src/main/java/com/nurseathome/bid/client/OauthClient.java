package com.nurseathome.bid.client;

import com.nurseathome.bid.client.fallback.OauthClientFallback;
import com.nurseathome.bid.model.request.TokenIntrospectRequest;
import com.nurseathome.bid.model.response.TokenIntrospectResponse;
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