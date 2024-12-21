package com.big0soft.nearexpireadmin.data.api;

import com.big0soft.nearexpireadmin.domain.api.ExpiryApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private static volatile RetrofitConfig instance;
    private final ExpiryApiService expiryApiService;

    private RetrofitConfig() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ExpiryApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(getOkHttpClient())
                .build();

        expiryApiService = retrofit.create(ExpiryApiService.class);
    }

    public static RetrofitConfig getInstance() {
        if (instance == null) {
            synchronized (RetrofitConfig.class) {
                if (instance == null) {
                    instance = new RetrofitConfig();
                }
            }
        }
        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        HttpClientBuilder client = new HttpClientBuilder()
                .addStaticHeaders("version", "1")
                .setLogConfig(HttpLoggingInterceptor.Level.BODY);

        return client.buildClient();

    }

    public ExpiryApiService getService() {
        return expiryApiService;
    }

}
