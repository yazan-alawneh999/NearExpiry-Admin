package com.big0soft.resource.http;

import java.util.concurrent.TimeoutException;

import retrofit2.Response;

public class HandleHttpState {

//    200 success
//    201 create
//    404 not found
//    401 not authorized
//    405 method not allow
//    408

    private final IHandleHttpStatusView httpStatusView;
    private final NetworkConnectivityChecker networkConnectivityChecker;

    public HandleHttpState(IHandleHttpStatusView httpStatusView, NetworkConnectivityChecker networkConnectivityChecker) {
        this.httpStatusView = httpStatusView;
        this.networkConnectivityChecker = networkConnectivityChecker;
    }

    public HttpHandle handleResponse(Response<?> response) {
        int statusCode = response.code();
        if (statusCode == 200) {
            return httpStatusView.handleSuccess();
        } else if (statusCode == 201) {
            return httpStatusView.handleCreate();
        } else if (statusCode == 404) {
            return httpStatusView.handleNotFound();
        } else if (statusCode == 401) {
            return httpStatusView.handleUnauthorized();
        } else if (statusCode == 405) {
            return httpStatusView.handleMethodNotAllowed();
        }
        return httpStatusView.handleUnknown();
    }

//    public HttpHandle handleExceptionResponse(Throwable throwable) {
//        if (!networkConnectivityChecker.isConnected() || NetworkConnectivityChecker.isNetworkException(throwable)) {
//            return httpStatusView.handleNoNetwork();
//        } else if (throwable instanceof TimeoutException) {
//            return httpStatusView.handleTimeout();
//        }
//        return httpStatusView.handleUnknown();
//    }


}
