package com.big0soft.resource.http.handle;

import com.big0soft.resource.http.HttpStatusUtils;
import com.big0soft.resource.model.Response;

import okhttp3.ResponseBody;

public final class HandleErrorResponseBody {
    public static Response responseErrorBody(retrofit2.Response<?> response) {
        Response errorResponse = new Response(true);
        if (response == null) {
            return errorResponse;
        }
        errorResponse.setResponseCode(response.code());
        errorResponse.setMessage(HttpStatusUtils.getMessageForStatusCode(response.code()));
        ResponseBody responseBody = response.errorBody();
        if (responseBody == null) {
            return errorResponse;
        }
        try {
            errorResponse = (Response) errorResponse.fromJson(responseBody.string());
            errorResponse.setError(true);
        } catch (Exception ignored) {
        }
        return errorResponse;
    }

}
