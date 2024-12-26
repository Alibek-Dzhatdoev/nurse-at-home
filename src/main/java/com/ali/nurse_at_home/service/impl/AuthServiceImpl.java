package com.ali.nurse_at_home.service.impl;

import com.ali.nurse_at_home.client.SsoClient;
import com.ali.nurse_at_home.model.request.TokenIntrospectRequest;
import com.ali.nurse_at_home.model.response.TokenIntrospectResponse;
import com.ali.nurse_at_home.service.AuthService;
import com.ali.nurse_at_home.utils.SecurityContextUtils;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static java.lang.String.format;
import static java.util.Base64.getEncoder;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {

    @NonFinal
    @Value("${application.security.service-client.id}")
    String serviceClientId;

    @NonFinal
    @Value("${application.security.service-client.secret}")
    String serviceClientSecret;

    SsoClient ssoClient;
//    SsoLdapClient ssoLdapClient;
    SecurityContextUtils securityContextUtils;

    @Override
    public void introspectSSOServiceClientToken(String token) {
        TokenIntrospectResponse introspectResponse = ssoClient.introspect(
                new TokenIntrospectRequest(securityContextUtils.getAccessToken()), getServiceClientBasicAuthorization());

        if (!introspectResponse.isActive()) {
            throw new ResponseStatusException(FORBIDDEN, "Invalid service-client SSO token");
        }
    }

    @Override
    public void introspectSsoLdapServiceClientToken(String token) {
//        TokenIntrospectResponse introspectResponse = ssoLdapClient.introspect(
//                new TokenIntrospectRequest(securityContextUtils.getAccessToken()), getServiceClientBasicAuthorization());
//
//        if (!introspectResponse.isActive()) {
//            throw new ResponseStatusException(FORBIDDEN, "Invalid service-client SSO-LDAP token");
//        }
    }

    private String getServiceClientBasicAuthorization() {
        byte[] basicAuth = format("%s:%s", serviceClientId, serviceClientSecret).getBytes();
        return "Basic " + getEncoder().encodeToString(basicAuth);
    }
}