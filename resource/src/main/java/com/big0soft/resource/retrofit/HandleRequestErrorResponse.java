package com.big0soft.resource.retrofit;

import com.big0soft.resource.model.ErrorResponse;

import java.io.IOException;

import retrofit2.HttpException;

public class HandleRequestErrorResponse {
    public static ErrorResponse handleError(Throwable throwable) {
        ErrorResponse errorResponse = new ErrorResponse();
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            try { // 5. Handle potential parsing errors
                int code = httpException.code();
                if (code == 401) {
                    errorResponse.setError("Wrong email or password");
                } else {
                    String jsonError = httpException.response().errorBody().string();
                    errorResponse.setError("Connection error");
//                    errorResponse = new Gson().fromJson(jsonError, ErrorResponse.class);
                }
//                errorResponse = new Gson().fromJson(jsonError, ErrorResponse.class); // 6. Use Gson for parsing
            } catch (IOException e) {
                // Log the parsing error
                errorResponse.setMessage("Error parsing error response");
            }
        } else if (throwable instanceof IOException) {
            errorResponse.setMessage("Connection error");
        } else {
            errorResponse.setMessage("Unknown error occurred"); // 7. Provide a generic message
        }
        return errorResponse;
    }
}

