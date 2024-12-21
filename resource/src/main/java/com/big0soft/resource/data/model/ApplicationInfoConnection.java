package com.big0soft.resource.data.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public final class ApplicationInfoConnection {
    //   app version
//   the api app use version
//   is connect
    private final boolean isConnect;
    private final StateConnectType stateConnectType;

    public enum StateConnectType {
        Connect, NETWORK_OF, NETWORK_NOT_STABLE, NOT_CONNECT_TO_API
    }

    public ApplicationInfoConnection(boolean isConnect) {
        this(isConnect, StateConnectType.Connect);
    }

    public ApplicationInfoConnection(boolean isConnect, StateConnectType stateConnectType) {
        this.isConnect = isConnect;
        this.stateConnectType = stateConnectType;
    }

    public boolean isConnect() {
        return isConnect;
    }

    public StateConnectType stateConnectType() {
        return stateConnectType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        ApplicationInfoConnection that = (ApplicationInfoConnection) obj;
        return this.isConnect == that.isConnect;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isConnect);
    }

    @NonNull
    @Override
    public String toString() {
        return "ApplicationInfoConnection{" +
                "isConnect=" + isConnect +
                ", stateConnectType=" + stateConnectType +
                '}';
    }


}
