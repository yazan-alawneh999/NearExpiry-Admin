package com.big0soft.nearexpireadmin.data.repository.remote;

import com.big0soft.nearexpireadmin.data.api.RetrofitConfig;
import com.big0soft.nearexpireadmin.data.requests.LoginRequest;
import com.big0soft.nearexpireadmin.data.responses.LoginResponse;
import com.big0soft.nearexpireadmin.domain.api.ExpiryApiService;
import com.big0soft.nearexpireadmin.domain.repository.BaseRepository;

import io.reactivex.rxjava3.core.Single;

public class LoginRepository extends BaseRepository {
    public LoginRepository(ExpiryApiService expiryApiService) {
        super(expiryApiService);
    }

    public LoginRepository() {
        super(RetrofitConfig.getInstance().getService());
    }

    public Single<LoginResponse> login(LoginRequest loginRequest) {
        return apiService().login(loginRequest);
    }

}