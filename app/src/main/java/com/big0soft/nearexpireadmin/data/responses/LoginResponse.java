package com.big0soft.nearexpireadmin.data.responses;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.security.AuthProvider;
import java.util.Objects;


public class LoginResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("token")
    private String token;
    @SerializedName("authProvider")
    private AuthProvider authProvide;
    private long expireAt;


    @NonNull
    @Override
    public String toString() {
        return "LoginResponse{" +
                "message='" + message + '\'' +
                ", token='" + token + '\'' +
                ", authProvide=" + authProvide +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this )return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        LoginResponse that = (LoginResponse) obj;
        return this.expireAt == that.expireAt &&
                Objects.equals(this.message, that.message) &&
                Objects.equals(this.token, that.token) &&
                Objects.equals(this.authProvide, that.authProvide);
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
