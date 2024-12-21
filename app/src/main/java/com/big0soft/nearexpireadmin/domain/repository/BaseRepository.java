package com.big0soft.nearexpireadmin.domain.repository;

import com.big0soft.nearexpireadmin.data.api.RetrofitConfig;
import com.big0soft.nearexpireadmin.domain.api.ExpiryApiService;

public class BaseRepository {
    private final ExpiryApiService expiryApiService;


    public BaseRepository(ExpiryApiService expiryApiService) {
        this.expiryApiService = expiryApiService;
    }

    public BaseRepository() {
        this.expiryApiService = RetrofitConfig.getInstance().getService();
    }

    public ExpiryApiService apiService() {
        return this.expiryApiService;
    }
}
