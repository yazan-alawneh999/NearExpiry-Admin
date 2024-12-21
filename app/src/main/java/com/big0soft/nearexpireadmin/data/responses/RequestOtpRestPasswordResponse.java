package com.big0soft.nearexpireadmin.data.responses;

public class RequestOtpRestPasswordResponse {
    private final String message;

    public RequestOtpRestPasswordResponse(String message) {
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
}
