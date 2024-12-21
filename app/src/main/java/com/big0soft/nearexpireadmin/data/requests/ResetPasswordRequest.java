package com.big0soft.nearexpireadmin.data.requests;

import java.security.AuthProvider;

public class ResetPasswordRequest {

    private final String provider;
    private final String newPassword;
    private final String oldPassword;
    private final AuthProvider authProvider;
    private final int otp;

    public ResetPasswordRequest(String provider, String newPassword, String oldPassword, AuthProvider authProvider, int ot) {
        this.provider = provider;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
        this.authProvider = authProvider;
        this.otp = ot;
    }

    public String provider() {
        return provider;
    }

    public String newPassword() {
        return newPassword;
    }

    public String oldPassword() {
        return oldPassword;
    }

    public AuthProvider authProvider() {
        return authProvider;
    }

    public int otp() {
        return otp;
    }

    @Override
    public String toString() {
        return "ResetPasswordRequest{" +
                "provider='" + provider + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", authProvider=" + authProvider +
                ", ot=" + otp +
                '}';
    }
}
