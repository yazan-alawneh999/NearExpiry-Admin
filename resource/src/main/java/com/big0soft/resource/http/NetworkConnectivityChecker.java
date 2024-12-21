package com.big0soft.resource.http;

import com.big0soft.resource.data.model.ApplicationInfoConnection;

import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.nio.channels.ClosedChannelException;

import javax.net.ssl.SSLException;

import io.reactivex.Observable;

public interface NetworkConnectivityChecker {
    Observable<ApplicationInfoConnection> isConnected();

    static boolean isNetworkException(Throwable e) {
        return e instanceof SocketException ||
                e instanceof ClosedChannelException ||
                e instanceof InterruptedIOException ||
                e instanceof ProtocolException ||
                e instanceof SSLException ||
                e instanceof UnknownHostException ||
                e instanceof UnknownServiceException;
    }
}
