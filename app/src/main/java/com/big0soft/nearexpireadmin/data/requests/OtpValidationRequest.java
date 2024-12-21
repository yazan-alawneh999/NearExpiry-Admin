package com.big0soft.nearexpireadmin.data.requests;

import java.security.AuthProvider;
import java.util.Objects;

public class OtpValidationRequest {
    private final String provider;
    private final int otp;
    private final AuthProvider authProvider;

    public OtpValidationRequest(String provider, int otp, AuthProvider authProvider) {
        this.provider = provider;
        this.otp = otp;
        this.authProvider = authProvider;
    }

    public String provider() {
        return provider;
    }

    public int otp() {
        return otp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        OtpValidationRequest that = (OtpValidationRequest) obj;
        return Objects.equals(this.provider, that.provider) &&
                this.otp == that.otp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(provider, otp);
    }

    @Override
    public String toString() {
        return "OtpValidationDto[" +
                "provider=" + provider + ", " +
                "otp=" + otp + ']';
    }
}
