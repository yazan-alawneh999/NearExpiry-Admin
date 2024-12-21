package com.big0soft.nearexpireadmin.data.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpClientBuilder {
    private OkHttpClient.Builder httpClient;

    public HttpClientBuilder() {
        httpClient = new OkHttpClient
                .Builder();
    }

    public static OkHttpClient logHttpRequest(HttpLoggingInterceptor.Level level) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
                .setLevel(level);


        OkHttpClient.Builder httpClient = new OkHttpClient
                .Builder()
                .addInterceptor(logging);
        return httpClient.build();
    }

    public HttpClientBuilder setLogConfig(HttpLoggingInterceptor.Level level) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
                .setLevel(level);
        httpClient.addInterceptor(logging);
        return this;
    }

    public HttpClientBuilder addStaticHeaders(String name, String headers) {
        httpClient.addInterceptor(chain -> {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder()
                    .header(name, headers);

            Request newRequest = requestBuilder.build();
            return chain.proceed(newRequest);
        });
        return this;
    }

    public HttpClientBuilder setAuthorizationBasicConfig() {
        // Add the Basic Authentication header to the requests
//        httpClient.addInterceptor(chain -> {
//            String credentials = "username:password";
//            String base64Credentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
//
//            Request originalRequest = chain.request();
//            Request.Builder requestBuilder = originalRequest.newBuilder()
//                    .header("Authorization", "Basic "+base64Credentials);
//
//            Request newRequest = requestBuilder.build();
//            return chain.proceed(newRequest);
//        });
        return this;
    }

    public OkHttpClient buildClient() {
        return httpClient.build();
    }

}
