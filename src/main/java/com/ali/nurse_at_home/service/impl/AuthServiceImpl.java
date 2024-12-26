package com.ali.nurse_at_home.service.impl;

import com.ali.nurse_at_home.client.OauthClient;
import com.ali.nurse_at_home.config.properties.ServiceClientProperties;
import com.ali.nurse_at_home.model.request.TokenIntrospectRequest;
import com.ali.nurse_at_home.model.response.TokenIntrospectResponse;
import com.ali.nurse_at_home.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.ali.nurse_at_home.utils.SecurityContextUtils.getAccessToken;
import static java.lang.String.format;
import static java.util.Base64.getEncoder;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {

    OauthClient oauthClient;
    ServiceClientProperties serviceClientProperties;

    @Override
    public void introspectOauthServiceClientToken(String token) {
        TokenIntrospectResponse introspectResponse = oauthClient.introspect(
                new TokenIntrospectRequest(getAccessToken()), getServiceClientBasicAuthorization());

        if (!introspectResponse.isActive()) {
            throw new ResponseStatusException(FORBIDDEN, "Invalid service-client SSO token");
        }
    }

    private String getServiceClientBasicAuthorization() {
        byte[] basicAuth = format("%s:%s", serviceClientProperties.id(), serviceClientProperties.secret()).getBytes();
        return "Basic " + getEncoder().encodeToString(basicAuth);
    }
}