package com.big0soft.nearexpireadmin.domain.api;


import com.big0soft.nearexpireadmin.data.requests.LoginRequest;
import com.big0soft.nearexpireadmin.data.requests.OtpValidationRequest;
import com.big0soft.nearexpireadmin.data.requests.RegistrationRequest;
import com.big0soft.nearexpireadmin.data.responses.LoginResponse;
import com.big0soft.nearexpireadmin.data.responses.OtpVerificationResponse;
import com.big0soft.nearexpireadmin.data.responses.RegistrationResponse;
import com.big0soft.nearexpireadmin.data.responses.RequestOtpRestPasswordResponse;
import com.big0soft.nearexpireadmin.data.requests.ResetPasswordRequest;
import com.big0soft.nearexpireadmin.data.responses.ResetPasswordResponse;
import com.big0soft.nearexpireadmin.data.requests.RestPasswordOtpRequest;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ExpiryApiService {


    String BASE_URL = "https://1fd3-109-107-253-231.ngrok-free.app/";
    String MAIN_PATH = "CLIENT-EXPIRE-SERVICE/api/v1/";
    String KEY_PATH_AUTH = "AUTH-SERVICE/api/v1/";

    @POST(KEY_PATH_AUTH + "login/")
    Single<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST(KEY_PATH_AUTH + "register/")
    Single<RegistrationResponse> register(@Body RegistrationRequest registrationRequest);

    @POST(KEY_PATH_AUTH + "register/verify")
    Single<OtpVerificationResponse> verifyOtp(@Body OtpValidationRequest otpValidationRequest);

    @POST(KEY_PATH_AUTH + "request-otp-reset-password")
    Single<RequestOtpRestPasswordResponse> requestOtpResetPassword(@Body RestPasswordOtpRequest restPasswordOtpRequest);

    @POST(KEY_PATH_AUTH + "auth/reset-password")
    Single<ResetPasswordResponse> resetPassword(@Body ResetPasswordRequest resetPasswordDto);


}