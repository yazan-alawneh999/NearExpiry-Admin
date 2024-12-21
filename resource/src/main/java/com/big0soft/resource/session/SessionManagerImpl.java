package com.big0soft.resource.session;

import com.big0soft.resource.data.SharedPreferenceDao;
import com.big0soft.resource.utils.StrUtils;

public class SessionManagerImpl implements SessionManager {
    public static final String KEY_SESSION = "SESSION";
    public static final String KEY_SESSION_EXPIRE_AT = "SESSION_EXPIRE_AT";
    private final SharedPreferenceDao sharedPreference;

    public SessionManagerImpl(SharedPreferenceDao sharedPreference) {
        this.sharedPreference = sharedPreference;
    }

    @Override
    public void saveSession(String user) {
        sharedPreference.putString(KEY_SESSION, user)
                .apply();
    }


    @Override
    public boolean hasSession() {
        String string = sharedPreference.getString(KEY_SESSION);
        return !StrUtils.isEmpty(string);
    }


    /**
     * @throws com.big0soft.resource.session.exception.EmptySessionException if user doesn't have session
     */
    @Override
    public String userSession() {
        return sharedPreference.getString(KEY_SESSION);
    }

    @Override
    public void removeSession() {
        sharedPreference.remove(KEY_SESSION).apply();
    }


    @Override
    public boolean sessionExpired(long currentTime) {
        if (!hasSession()) {
            return true;
        }
        long expireAt = sharedPreference.getLong(KEY_SESSION_EXPIRE_AT, 0);
        return expireAt <= currentTime;
    }

    @Override
    public void sessionExpireAt(long expireAt) {
        sharedPreference.putLong(KEY_SESSION_EXPIRE_AT, expireAt)
                .apply();
    }
}
