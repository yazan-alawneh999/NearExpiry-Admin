package com.big0soft.nearexpireadmin.data.responses;

import java.util.Objects;

public class ResetPasswordResponse {
    private final String message;

    public ResetPasswordResponse(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }

    @Override
    public String toString() {
        return "RequestOtpRestPasswordResponse{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResetPasswordResponse that = (ResetPasswordResponse) o;

        return Objects.equals(message, that.message);

    }
}
