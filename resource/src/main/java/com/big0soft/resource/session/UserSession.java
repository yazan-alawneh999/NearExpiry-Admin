package com.big0soft.resource.session;

import com.big0soft.resource.data.FromJson;
import com.big0soft.resource.data.ToJson;
import com.big0soft.resource.gson.FromJsonImpl;
import com.big0soft.resource.gson.ToJsonImpl;

public class UserSession implements FromJson, ToJson {


    private String token;
    private long expireAt;
    private String password;


    public UserSession() {

    }

    public UserSession(String token, long expireAt, String password) {
        this.token = token;
        this.expireAt = expireAt;
        this.password = password;
    }

    public String password() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpireAt() {
        return expireAt;
    }

    public String getPassword() {
        return password;
    }



    public void setExpireAt(long expireAt) {
        this.expireAt = expireAt;
    }

    @Override
    public FromJson fromJson(String data) {
        return new FromJsonImpl<>(getClass()).fromJson(data);
    }

    @Override
    public String toJson() {
        return new ToJsonImpl<>(this).toJson();
    }
}
