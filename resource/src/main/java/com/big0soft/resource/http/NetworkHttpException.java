package com.big0soft.resource.http;

import static com.big0soft.resource.data.model.ApplicationInfoConnection.StateConnectType.NETWORK_NOT_STABLE;
import static com.big0soft.resource.http.NetworkConnectivityChecker.isNetworkException;

import com.big0soft.resource.data.model.ApplicationInfoConnection;

import io.reactivex.Observable;

public class NetworkHttpException implements NetworkConnectivityChecker {
    private final Throwable throwable;

    public NetworkHttpException(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public Observable<ApplicationInfoConnection> isConnected() {
        return Observable.just(new ApplicationInfoConnection(isNetworkException(throwable)
                , NETWORK_NOT_STABLE));
    }

}
