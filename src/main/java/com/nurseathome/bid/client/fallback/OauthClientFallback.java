package com.nurseathome.bid.client.fallback;

import com.nurseathome.bid.client.OauthClient;
import com.nurseathome.bid.model.enums.Roles;
import com.nurseathome.bid.model.request.TokenIntrospectRequest;
import com.nurseathome.bid.model.request.TokenRequest;
import com.nurseathome.bid.model.response.TokenIntrospectResponse;
import com.nurseathome.bid.model.response.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OauthClientFallback implements FallbackFactory<OauthClient>, CommonFallbackFactory {

    @Override
    public OauthClient create(Throwable cause) {
        return new OauthClient() {
            @Override
            public TokenIntrospectResponse introspect(TokenIntrospectRequest request,
                                                      String authorizationHeader) {
                log.error(
                        "FeignException on OauthClient.introspect(TokenIntrospectRequest request, String authorizationHeader)",
                        cause);
                throw onFeignException(cause);
            }

            @Override
            public TokenResponse getToken(TokenRequest request, String authorizationHeader) {
                log.error(
                        "FeignException on OauthClient.getToken(TokenRequest request, String authorizationHeader)",
                        cause);
                throw onFeignException(cause);
            }

            @Override
            public void endRegistration(Roles role) {
                log.error("FeignException on OauthClient.endRegistration(Roles role)", cause);
                throw onFeignException(cause);
            }
        };
    }
}