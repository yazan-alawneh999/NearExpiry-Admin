package com.big0soft.nearexpireadmin.data.responses;

import androidx.annotation.NonNull;

import java.security.AuthProvider;

public class OtpVerificationResponse {
    private String message;
    private String token;
    private long expireAt;
    private AuthProvider authProvide;


    @NonNull
    @Override
    public String toString() {
        return "RegistrationResponse{" +
                "message='" + message + '\'' +
                ", token='" + token + '\'' +
                ", authProvide=" + authProvide +
                '}';
    }

    public void setExpireAt(long expireAt) {
        this.expireAt = expireAt;
    }

    public String getMessage() {
        return message;
    }

    public long getExpireAt() {
        return expireAt;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthProvider getAuthProvide() {
        return authProvide;
    }

    public void setAuthProvide(AuthProvider authProvide) {
        this.authProvide = authProvide;
    }
}
