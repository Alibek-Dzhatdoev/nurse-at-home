package com.ali.nurse_at_home.service;

public interface AuthService {

    void introspectSSOServiceClientToken(String token);

    void introspectSsoLdapServiceClientToken(String token);
}