package com.big0soft.resource.http;

import static com.big0soft.resource.data.model.ApplicationInfoConnection.StateConnectType.Connect;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.big0soft.resource.data.model.ApplicationInfoConnection;

import io.reactivex.Observable;

public class NetworkConnectivityCheckerImpl implements NetworkConnectivityChecker {

    private final Context context;

    public NetworkConnectivityCheckerImpl(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public Observable<ApplicationInfoConnection> isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean connect = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        ApplicationInfoConnection applicationInfoConnection = new ApplicationInfoConnection(connect, Connect);
        return Observable.just(applicationInfoConnection);

    }
}
