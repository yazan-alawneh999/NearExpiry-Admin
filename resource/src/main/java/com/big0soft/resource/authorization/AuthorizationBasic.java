package com.big0soft.resource.authorization;

import android.util.Base64;

public class AuthorizationBasic implements IAuthorization {
    private final String username;
    private final String password;



    public AuthorizationBasic(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String authorization() {
        String authorization = username + ":" + password;
        String encodeBase64 = Base64.encodeToString(authorization.getBytes(), Base64.NO_WRAP);
        return "Basic " + encodeBase64;
    }

}
