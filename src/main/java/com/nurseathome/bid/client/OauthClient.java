package com.nurseathome.bid.client;

import com.nurseathome.bid.aspect.Authorized;
import com.nurseathome.bid.client.fallback.OauthClientFallback;
import com.nurseathome.bid.model.enums.Roles;
import com.nurseathome.bid.model.request.TokenIntrospectRequest;
import com.nurseathome.bid.model.request.TokenRequest;
import com.nurseathome.bid.model.response.TokenIntrospectResponse;
import com.nurseathome.bid.model.response.TokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@FeignClient(name = "oauth", path = "/oauth", fallbackFactory = OauthClientFallback.class)
public interface OauthClient {

    @PostMapping(value = "/oauth2/introspect", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    TokenIntrospectResponse introspect(
            @RequestBody TokenIntrospectRequest request,
            @RequestHeader(value = AUTHORIZATION) String authorizationHeader
    );

    @PostMapping(value = "/oauth2/token", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    TokenResponse getToken(
            @RequestBody TokenRequest request,
            @RequestHeader(value = AUTHORIZATION) String authorizationHeader
    );

    @Authorized
    @PatchMapping("/api/v1/authorization/registration/end")
    void endRegistration(@RequestParam Roles role);
}