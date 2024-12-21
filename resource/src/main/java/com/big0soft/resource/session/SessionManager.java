package com.big0soft.resource.session;


public interface SessionManager {
    void saveSession(String session);

    boolean hasSession();

    String userSession();

    void removeSession();

    boolean sessionExpired(long currentTime);

    void sessionExpireAt(long expireAt);

}
