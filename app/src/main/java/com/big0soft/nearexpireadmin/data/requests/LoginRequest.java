package com.big0soft.nearexpireadmin.data.requests;

import androidx.annotation.NonNull;

import com.big0soft.nearexpireadmin.domain.enums.AuthProvider;
import com.google.gson.annotations.SerializedName;



public class LoginRequest {
    @SerializedName("provider")
    private final String provider;

    @SerializedName("password")
    private final String password;

    @SerializedName("authProvider")
    private final AuthProvider authProvider;

    public LoginRequest(String provider, String password, AuthProvider authProvider) {
        this.provider = provider;
        this.password = password;
        this.authProvider = authProvider;
    }

    @NonNull
    @Override
    public String toString() {
        return "LoginRequest{" +
                "provider='" + provider + '\'' +
                ", password='" + password + '\'' +
                ", authProvider=" + authProvider +
                '}';
    }

    public String getProvider() {
        return provider;
    }

    public String getPassword() {
        return password;
    }

    public AuthProvider getAuthProvider() {
        return authProvider;
    }
}
