package com.nurseathome.bid.service;

public interface AuthService {

    void introspectToken();

    String getServiceAccessToken();
}