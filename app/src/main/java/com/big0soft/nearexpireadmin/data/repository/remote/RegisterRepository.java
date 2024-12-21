package com.big0soft.nearexpireadmin.data.repository.remote;

import com.big0soft.nearexpireadmin.data.requests.RegistrationRequest;
import com.big0soft.nearexpireadmin.data.responses.RegistrationResponse;
import com.big0soft.nearexpireadmin.domain.api.ExpiryApiService;
import com.big0soft.nearexpireadmin.domain.repository.BaseRepository;

import io.reactivex.rxjava3.core.Single;

public class RegisterRepository extends BaseRepository {

    public RegisterRepository(ExpiryApiService expiryApiService) {
        super(expiryApiService);
    }

    public Single<RegistrationResponse> register(RegistrationRequest registrationRequest) {
        return apiService().register(registrationRequest);
    }

}
