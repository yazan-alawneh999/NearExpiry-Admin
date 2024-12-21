package com.big0soft.nearexpireadmin.data.requests;

import java.security.AuthProvider;

public class RestPasswordOtpRequest {
    private final String provider;
    private final AuthProvider authProvider;

    public RestPasswordOtpRequest(String provider, AuthProvider authProvider) {
        this.provider = provider;
        this.authProvider = authProvider;
    }

    @Override
    public String toString() {
        return "RestPasswordOtpRequest{" +
                "provider='" + provider + '\'' +
                ", authProvider=" + authProvider +
                '}';
    }

    public String getProvider() {
        return provider;
    }

    public AuthProvider getAuthProvider() {
        return authProvider;
    }
}
