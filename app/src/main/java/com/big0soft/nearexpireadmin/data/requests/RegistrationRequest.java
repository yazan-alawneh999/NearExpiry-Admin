package com.big0soft.nearexpireadmin.data.requests;

import androidx.annotation.NonNull;

import com.big0soft.nearexpireadmin.domain.enums.AuthProvider;

import java.util.Objects;

public class RegistrationRequest {
    private final String provider;
    private final String password;
    private final AuthProvider authProvider;

    public RegistrationRequest(String provider, String password, AuthProvider authProvider) {
        this.provider = provider;
        this.password = password;
        this.authProvider = authProvider;
    }

    public String provider() {
        return provider;
    }

    public String password() {
        return password;
    }

    public AuthProvider authProvider() {
        return authProvider;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        RegistrationRequest that = (RegistrationRequest) obj;
        return Objects.equals(this.provider, that.provider) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.authProvider, that.authProvider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(provider, password, authProvider);
    }

    @NonNull
    @Override
    public String toString() {
        return "RegistrationDto[" +
                "provider=" + provider + ", " +
                "password=" + password + ", " +
                "authProvider=" + authProvider + ']';
    }
}
